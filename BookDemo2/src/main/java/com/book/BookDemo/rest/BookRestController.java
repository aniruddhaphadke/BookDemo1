package com.book.BookDemo.rest;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.BookDemo.entity.Book;
import com.book.BookDemo.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class BookRestController 
{
	private BookService bookService;
	
	@Autowired
	public BookRestController(BookService theBookService)
	{
		bookService = theBookService;
	}
	
	@GetMapping("/filter")
	public Page<Book> filterBooks(@ParameterObject Pageable pageable)
	{
	     return (Page<Book>) bookService.getBooks(pageable);
	}
	
	@Operation(summary = "Get the All Books")
	@GetMapping("/books")
	public List<Book> findAllBook()
	{
		return bookService.findAll();
	}
	
	@Operation(summary = "Get a book with ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "500", description = "Server error"),
			@ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
	         @ApiResponse(responseCode = "404", description = "Book not found"),
	        @ApiResponse(responseCode = "200", description = "Found the Book")
	})
	@GetMapping("/books/{theId}")
	public Book getBook(@PathVariable int theId)
	{
		Book theBook = bookService.getBook(theId);
		if(theBook == null)
		{
			throw new RuntimeException("Book not found");
			
		}
		
		return theBook;
	}
	
	@Operation(summary = "Add new Book")
	@PostMapping("/books")
	public Book addBook(@RequestBody Book theBook)
	{
		bookService.save(theBook);
		
		return theBook;
	}
	
	@Operation(summary = "Update the existing book")
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book theBook)
	{
		bookService.save(theBook);
		return theBook;
	}
	
	@Operation(summary = "Delete the Bookk with book ID")
	@DeleteMapping("/books/{theId}")
	public String deleteById(@PathVariable int theId)
	{
		Book theBook = bookService.getBook(theId);
		
		if(theBook == null)
		{
			throw new RuntimeException("Book not found");
		}
		
		bookService.delete(theId);
		
		return "Book deleted successfully"+theBook;
	}
	

}
