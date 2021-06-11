package com.sriram.persistent;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sriram.bean.Book;
import com.sriram.bean.Employee;

public interface EmployeeDao {
	ArrayList<Book> showAvailableDataAnalytics() throws SQLException, ClassNotFoundException;
	ArrayList<Book> showAvailableTechnology() throws SQLException, ClassNotFoundException;
	ArrayList<Book> showAvailableManagement() throws SQLException, ClassNotFoundException;
	boolean selectDataAnalytics(int id) throws SQLException, ClassNotFoundException;
	boolean selectTechnology(int id) throws SQLException, ClassNotFoundException;
	boolean selectManagement(int id) throws SQLException, ClassNotFoundException;
	//void returnBook(int id);
	boolean updateTransaction(int empId,int bookId) throws SQLException,ClassNotFoundException;
	boolean authenticate(String username, String password) throws ClassNotFoundException, SQLException;
	boolean isValidEmployee(int empId) throws ClassNotFoundException,SQLException;
	ArrayList<Book> viewAllBooks() throws ClassNotFoundException,SQLException;
	int returnBook(int empId, int bookId) throws ClassNotFoundException,SQLException;
	String getBookType(int bookId) throws ClassNotFoundException, SQLException;
	boolean removeTransaction(int empId, int bookId) throws ClassNotFoundException, SQLException;
	boolean increaseQuantity(int bookId) throws ClassNotFoundException, SQLException;
	Employee printDetails(int empId, int bookId)throws ClassNotFoundException, SQLException;
	Book printBookDetails(int bookId)throws ClassNotFoundException, SQLException;


}
