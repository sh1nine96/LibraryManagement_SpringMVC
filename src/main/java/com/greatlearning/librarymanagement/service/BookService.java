package com.greatlearning.librarymanagement.service;

import java.util.List;

import com.greatlearning.librarymanagement.entity.Book;


public interface BookService {
	
	List<Book> listAllBooks();
	
Book findById(int bookId);
	
	void update(Book book, String name, String author, String category);
	
	void deleteById(int bookId);
	
	void add(Book newBook);
	
	List<Book> searchBy(String name, String author);

}
