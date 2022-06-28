package com.greatlearning.librarymanagement.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

import com.greatlearning.librarymanagement.entity.Book;

@Repository
public class BookServiceImpl implements BookService{
	
	private SessionFactory sessionFactory;
	private Session session;
	
	@Autowired
	public BookServiceImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
			
		}
		
	}
	
	@Transactional
	@Override
	public List<Book> listAllBooks() {
		Transaction transaction = session.beginTransaction();
		
		List<Book> books = session.createQuery("from Book").list();
		
		transaction.commit();
		return books;
	}
	
	@Transactional
	@Override
	public Book findById(int bookId) {
		
		Transaction transaction = session.beginTransaction();

		Book book = session.get(Book.class, bookId);
		
		transaction.commit();
		
		return book;
	}
	
	@Transactional
	
	public void update(Book book, String name, String author, String category) {
		
		Transaction transaction = session.beginTransaction();

		book.setName(name);
		book.setAuthor(author);
		book.setCategory(category);
		
		session.saveOrUpdate(book);
		
		transaction.commit();
		
	}
	
	@Transactional
	public void deleteById(int bookId) {
		Transaction transaction = session.beginTransaction();

		Book book = session.get(Book.class, bookId);
		
		session.delete(book);

		transaction.commit();
		
	}
	
	@Transactional
	
	public void add(Book newBook) {
		Transaction transaction = session.beginTransaction();

		session.saveOrUpdate(newBook);

		transaction.commit();
		
	}
	
	@Transactional
	
	public List<Book> searchBy(String name, String author) {
		Transaction transaction = session.beginTransaction();

		String hiberateQuery = "";
		
		
		if (name.length() != 0 && author.length() != 0) {
			
			// name like '%A%'
			hiberateQuery = "from Book where name like '%" + name + "%'" +
					" or author like '%" + author + "%'";				
		}else if (name.length() != 0) {
			hiberateQuery = "from Book where name like '%" + name + "%'";
		}else if (author.length() != 0) {
			hiberateQuery = "from Book where author like '%" + author + "%'";
		}else {
			System.out.println("Control wont come here...");
		}
		
		List<Book> books = session.createQuery(hiberateQuery).list();
		
		transaction.commit();				

		return books;
	}
	
	

}
