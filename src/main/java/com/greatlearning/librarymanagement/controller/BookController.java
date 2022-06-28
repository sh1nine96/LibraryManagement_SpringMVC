package com.greatlearning.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.librarymanagement.entity.Book;
import com.greatlearning.librarymanagement.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookservice;
	
	@RequestMapping("/listing")
	public String handleListingBooks(Model model) {
		
		
		List<Book> books = bookservice.listAllBooks();
		
		model.addAttribute("BooksCollection", books);
		
		return "book-lister";
	}
	
	@PostMapping("/addorupdate")
	public String handleAddorUpdate(
		@RequestParam("bookId") int bookId,
		@RequestParam("name") String name,
		@RequestParam("author") String author,
		@RequestParam("category") String category) {
		
		if(bookId == 0) {
			
			//Add operation
			Book newBook = new Book();
			newBook.setName(name);
			newBook.setAuthor(author);
			newBook.setCategory(category);
			
			bookservice.add(newBook);
			
			return "redirect:/books/listing";
		} else {
			//update operation

		Book book = bookservice.findById(bookId);
			
			bookservice.update(book, name, author, category);
			
		
						
			return "redirect:/books/listing";			
		}
	}
	

	@RequestMapping("/listing/begin-update")
	public String handleBeginUpdate(
		@RequestParam("bookId") int bookId, Model model){
		
		Book book = bookservice.findById(bookId);
		
		model.addAttribute("tempBook", book);
	
		return "book-details-page";
	}
	

	@RequestMapping("/listing/delete")
	public String handleDelete(
		@RequestParam("bookId") int bookId){
				
		bookservice.deleteById(bookId);
		
		return "redirect:/books/listing";				
	}
	
	@RequestMapping("/begin-add")	
	public String handleBeginAdd(Model model) {
		
		Book newBook = new Book();
		
		model.addAttribute("tempBook", newBook);
		
		return "book-details-page";
	}
	
	
	@RequestMapping("/listing/search")	
	public String handleSearch(
		
		@RequestParam("name") String name,
		@RequestParam("author") String author,
		Model model){
		
		// "   name1   " -> "name1"
		if (name.trim().isEmpty() && author.trim().isEmpty()) {
			
			return "redirect:/books/listing";
		}else {
			
			List<Book> books = bookservice.searchBy(name, author);
			
			model.addAttribute("BooksCollection", books);
			
			return "book-lister";
		}		

	}
}
