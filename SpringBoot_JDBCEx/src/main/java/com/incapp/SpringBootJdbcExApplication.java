package com.incapp;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.incapp.bean.Book;
import com.incapp.beanDAO.BookDAO;

@SpringBootApplication
public class SpringBootJdbcExApplication {

	public static void main(String[] args) {
	ApplicationContext context = SpringApplication.run(SpringBootJdbcExApplication.class, args);
	
	BookDAO dao = context.getBean(BookDAO.class); // get here BookDAO Object here 
	
	
	//Add the Book here 
	Book b1 = context.getBean(Book.class);
	
	b1.setAname("Rahul Sir");
	b1.setName("COREEE");
	b1.setPname("Publisher Chatur");
	b1.setPrice(340);
	
	String add = dao.addBook(b1);
	
	System.out.println(add);
	System.out.println("___________");
	
   
	
	
	//readAllBooks 
	List<Book> b = dao.readAllBook();
    
	if (b.isEmpty()) {
		System.out.println("No Data Found ");
	}
	
	else {
		for(Book book :b) {
			System.out.println(book.getAname());
			System.out.println(book.getName());
			System.out.println(book.getPname());
			System.out.println(book.getPrice());
			System.out.println(":::::::::::");
			
		}
	}
	
	
	
	
	//readBookByName
	Book book2 = dao.readBookByName("main Pra");
	
	if(book2==null) {
		System.out.println("No Book Found To this Name ");
	}
	else {
		System.out.println("============");
		System.out.println(book2.getAname());
		System.out.println(book2.getName());
		System.out.println(book2.getPname());
		System.out.println(book2.getPrice());
		
		System.out.println("==============");
	}
	
	
	
	
	
	//readBookNameLike 
	
	List<Book> like = dao.readBookNameLike("a");
	
	if(like.isEmpty()) {
		System.out.println("No Book Found here");
	}
	else {
		for(Book book : like) {
			
			System.out.println("************");
			System.out.println(book.getAname());
			System.out.println(book.getName());
			System.out.println(book.getPname());
			System.out.println(book.getPrice());
			System.out.println("************");
			
		}
	}
	
	
	
	
	//deletedBook 
	
	System.out.println("@@@@@@@@@@@@@@@");
	String delete = dao.deleteBook("main Pra");
	System.out.println(delete);
	System.out.println("@@@@@@@@@@@@@@");
	
	
	
	
	//update Book
	Book update = context.getBean(Book.class);
	
	update.setAname("Arti");
	update.setPrice(90);
	update.setName("Bharti 4");
	update.setPname("Arti Publisher");
	
	String status = dao.updateBook(update, "knuna");
	System.out.println(status);
	
	
	
	
	
	
	
	
	
	
	
	}

}
