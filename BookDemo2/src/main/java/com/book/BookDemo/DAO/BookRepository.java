package com.book.BookDemo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.BookDemo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> 
{
	//No need to write any interface methods bcz DATA Jpa
}
