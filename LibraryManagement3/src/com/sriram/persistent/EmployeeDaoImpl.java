package com.sriram.persistent;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.time.*;
import com.sriram.bean.Book;

import com.sriram.bean.Employee;
import com.sriram.helper.MySQLConnection;

public class EmployeeDaoImpl implements EmployeeDao{	

	LocalDate today=LocalDate.now(ZoneId.of("Asia/Kolkata"));
	
	@Override
	public ArrayList<Book> showAvailableDataAnalytics() throws SQLException, ClassNotFoundException{
		ArrayList<Book> books = null;
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from BOOKS where category=? and quantity>?");
		statement.setString(1, "DATA ANALYTICS");
		statement.setInt(2, 0);
		ResultSet resultset = statement.executeQuery();

		Book book = null;
		books = new ArrayList<Book>();
		
		while(resultset.next()) {
			book=new Book();
			book.setBookID(resultset.getInt("id"));
			book.setBookCategory(resultset.getString("category"));
			book.setTitle(resultset.getString("title"));
			book.setAuthor(resultset.getString("author"));
			book.setQuantity(resultset.getInt("quantity"));
			
			books.add(book);
		}
		connection.close();
		return books;
	}


	@Override
	public ArrayList<Book> showAvailableTechnology() throws SQLException, ClassNotFoundException {
		ArrayList<Book> books = null;
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from BOOKS where category=? and quantity>?");
		statement.setString(1, "TECHNOLOGY");
		statement.setInt(2, 0);
		ResultSet resultset = statement.executeQuery();

		Book book = null;
		books = new ArrayList<Book>();
		
		while(resultset.next()) {
			book=new Book();
			book.setBookID(resultset.getInt("id"));
			book.setBookCategory(resultset.getString("category"));
			book.setTitle(resultset.getString("title"));
			book.setAuthor(resultset.getString("author"));
			book.setQuantity(resultset.getInt("quantity"));
			
			books.add(book);
		}
		connection.close();
		return books;
	}
	
	@Override
	public ArrayList<Book> showAvailableManagement() throws SQLException, ClassNotFoundException {
		ArrayList<Book> books = null;
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from BOOKS where category=? and quantity>?");
		statement.setString(1, "MANAGEMENT");
		statement.setInt(2, 0);
		ResultSet resultset = statement.executeQuery();

		Book book = null;
		books = new ArrayList<Book>();
		
		while(resultset.next()) {
			book=new Book();
			book.setBookID(resultset.getInt("id"));
			book.setBookCategory(resultset.getString("category"));
			book.setTitle(resultset.getString("title"));
			book.setAuthor(resultset.getString("author"));
			book.setQuantity(resultset.getInt("quantity"));
			books.add(book);
		}
		connection.close();
		return books;
	}


	@Override
	public boolean selectDataAnalytics(int id) throws SQLException, ClassNotFoundException {
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("update books set quantity=quantity-? where id=?");
		statement.setInt(1, 1);
		statement.setInt(2, id);
		
		int rows = statement.executeUpdate();

		if (rows > 0)
			return true;

		connection.close();
		return false;
	}


	@Override
	public boolean selectTechnology(int id) throws SQLException, ClassNotFoundException {
		
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("update books set quantity=quantity-? where id=?");
		statement.setInt(1, 1);
		statement.setInt(2, id);
		
		int rows = statement.executeUpdate();

		if (rows > 0)
			return true;

		connection.close();
		return false;
	}


	@Override
	public boolean selectManagement(int id) throws SQLException, ClassNotFoundException {
		
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("update books set quantity=quantity-? where id=?");
		statement.setInt(1, 1);
		statement.setInt(2, id);
		
		int rows = statement.executeUpdate();

		if (rows > 0)
			return true;

		connection.close();
		return false;
	}


	@Override
	public boolean updateTransaction(int empId,int bookId) throws SQLException,ClassNotFoundException {
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("insert into transaction values(?,?,?)");
		statement.setInt(1, empId);
		statement.setInt(2, bookId);
		statement.setObject(3, today);
		
		int rows=statement.executeUpdate();
		if (rows > 0)
			return true;

		connection.close();

		return false;
		
	}


	@Override
	public boolean authenticate(String username, String password) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select * from auth");
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()){
			if(username.equals(resultSet.getString(1)) && password.equals(resultSet.getString(2))) {
				return true;
			}
		}
		
		return false;
	}


	@Override
	public boolean isValidEmployee(int empId) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select id from employee");
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()){
			if(empId == Integer.parseInt(resultSet.getString(1))) {
				return true;
			}
		}
		return false;
	}


	@Override
	public ArrayList<Book> viewAllBooks() throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select * from books");
		
		ResultSet resultSet = statement.executeQuery();
		
		Book book = null;
		
		ArrayList<Book> books = null;
		
		books = new ArrayList<Book>();
		while(resultSet.next()) {
			book=new Book();
			book.setBookID(resultSet.getInt("id"));
			book.setBookCategory(resultSet.getString("category"));
			book.setTitle(resultSet.getString("title"));
			book.setAuthor(resultSet.getString("author"));
			book.setQuantity(resultSet.getInt("quantity"));
			
			books.add(book);
		}
		connection.close();
		return books;
		
	}


	@Override
	public int returnBook(int empId, int bookId) throws ClassNotFoundException, SQLException {
		
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select date_lent from transaction where empid=? and bookid=?");
		
		statement.setInt(1,empId);
		
		statement.setInt(2, bookId);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		LocalDate lent=resultSet.getObject(1,LocalDate.class);
		
		Period period=Period.between(lent, today);
		
		return period.getDays();
		
		
	}


	@Override
	public String getBookType(int bookId) throws ClassNotFoundException, SQLException {
		
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("select category from books where id=?");
		
		statement.setInt(1, bookId);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		String cat=resultSet.getString("category");
		
		return cat;
	}


	@Override
	public boolean removeTransaction(int empId, int bookId) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("delete from transaction where empid=? and bookid=?");
		
		statement.setInt(1, empId);
		statement.setInt(2, bookId);
		int resultSet = statement.executeUpdate();	
		if(resultSet>0) return true;
		return false;
		
	}


	@Override
	public boolean increaseQuantity(int bookId) throws ClassNotFoundException, SQLException {
		
		Connection connection = MySQLConnection.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("update books set quantity=quantity+? where id=?");
		statement.setInt(1, 1);
		statement.setInt(2, bookId);
		
		int rows = statement.executeUpdate();

		if (rows > 0)
			return true;

		connection.close();
		return false;
	}


	@Override
	public Employee printDetails(int empId, int bookId) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from employee where id=?");
		statement.setInt(1, empId);
		
		ResultSet resultset = statement.executeQuery();
		
		Employee emp=null;
		emp=new Employee();
		while(resultset.next()) {
			emp.setEmpId(resultset.getInt("ID"));
			emp.setEmployeeFirstName(resultset.getString("FIRST_NAME"));
			emp.setEmployeeLastName(resultset.getString("LAST_NAME"));
			emp.setDepartment(resultset.getString("department"));
			emp.setEmail(resultset.getString("email"));
		}
		return emp;
	}


	@Override
	public Book printBookDetails(int bookId) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from books where id=?");
		statement.setInt(1, bookId);
		
		ResultSet resultset = statement.executeQuery();
		
		Book book=null;
		book=new Book();
		while(resultset.next()) {
			book.setBookID(resultset.getInt("id"));
			book.setBookCategory(resultset.getString("category"));
			book.setTitle(resultset.getString("title"));
			book.setAuthor(resultset.getString("author"));
		}
		return book;
	}


	
	

	
}
