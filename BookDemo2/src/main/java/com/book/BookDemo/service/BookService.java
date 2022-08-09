package com.book.BookDemo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.book.BookDemo.entity.Book;

public interface BookService 
{
	public List<Book> findAll();
	
	public Book getBook(int id);
	
	public Book getBooks(Pageable pageable);
	
	public void save(Book theBook);
	
	public void delete(int id);

}
