package com.book.BookDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.book.BookDemo.DAO.BookRepository;
import com.book.BookDemo.entity.Book;

@Service
public class BookServiceImpl implements BookService 
{
	private BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository theBookRepository)
	{
		bookRepository = theBookRepository;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Book getBook(int id) {
		Optional<Book> result = bookRepository.findById(id);
		
		Book tempBook = null;
		
		if(result.isPresent())
		{
			tempBook = result.get();
		}
		else
		{
			throw new RuntimeException("Book not found");
		}
		
		return tempBook;
	}

	@Override
	public void save(Book theBook) {
		// TODO Auto-generated method stub
		bookRepository.save(theBook);

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);

	}

	@Override
	public Book getBooks(Pageable pageable) {
		// TODO Auto-generated method stub
		return (Book) bookRepository.findAll(pageable);
	}

}
