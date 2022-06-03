package com.incapp.beanDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.incapp.bean.Book;

@Component
public class BookDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	// no need here for Getter Setter in SpringBoot  
//	public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	
	//Add  the Book ...
	
	public String addBook(Book b) {
		
		try {
			final String query ="insert into book (name, aname , pname , price) values(?,?,?,?)";
			jdbcTemplate.update(query , new Object[] {b.getName() , b.getAname(),b.getPname() , b.getPrice()});
			
			return "Successfully Book Added";
			
		} catch (Exception e) {
			return "Book Already Exits ";
		}	
	}
	
	
	   
	
	
	//readAllBook 
    public List<Book> readAllBook(){
    	
    	class DataMapper implements RowMapper {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Book b = new Book();
				b.setAname(rs.getString("aname"));
				b.setName(rs.getString("name"));
				b.setPname(rs.getString("pname"));
				b.setPrice(rs.getInt("price"));
				
		       return b;
			}
    	}
    	
    	final String query = "select * from book";
    	List<Book> b = jdbcTemplate.query(query, new DataMapper());
    	return b;	
    }
    
    
    
    
    
    
    //readBookByName
    public Book readBookByName(String name) {
    	
    	class DataMapper implements RowMapper{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book b = new Book();
				
				b.setAname(rs.getString("aname"));
				b.setName(rs.getString("name"));
				b.setPname(rs.getString("pname"));
				b.setPrice(rs.getInt("price"));
				
				return b;
			}	
    	}
    	try {
    		final String query = "select * from book where name = ?";
       	 Book b = (Book) jdbcTemplate.queryForObject(query, new DataMapper() , new Object[] {name} );
       	     
       	 return b;
			
		} catch (EmptyResultDataAccessException e) {
    	 return null;
    }   }
	
    
    
    
    
    
    //readBookNameLike  like query ...
    
     public  List<Book>  readBookNameLike(String name){
    	 
    	class DataMapper implements RowMapper{

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Book b = new Book();
				
				b.setAname(rs.getString("aname"));
				b.setName(rs.getString("name"));
				b.setPname(rs.getString("pname"));
				b.setPrice(rs.getInt("price"));
				
				return b;
			}	
    	}   
    	final String query = "select * from book where name like ?";
    	List<Book> b = jdbcTemplate.query(query, new DataMapper() , new Object[]  {"%"+name+"%"});
    	return b;
     }
     
     
     
     
     
     
     
   //deleteBook
 	public String deleteBook(String name) {
 		
 		final String query = "delete from book where name = ? ";
 		int x = jdbcTemplate.update(query, new Object[] {name});
 		
 		if(x !=0) {
 			return "Deleted Book";
 		}
 		else {
 			return "No Book Found To Delete";
 		}
 	}
 	
 	
 	
 	
 	
 	//updateBook
     
 	public String updateBook(Book b,String name) {
 		
 		final String query = "update book set name =? , aname=? , pname=? , price=? where name=?";
 		int x = jdbcTemplate.update(query , new Object[] {b.getName(),b.getAname(),b.getPname(),b.getPrice() , name});
 		
 		if(x !=0) {
 			return"Book Updaated";
 		}
 		
 		else {
 			return"Book Not Found To Update";
 		}
 	}
	
	
	
	

	
	
	
	

	
	
	
	
	
	
}
