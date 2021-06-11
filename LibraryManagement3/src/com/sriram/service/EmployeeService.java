package com.sriram.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sriram.bean.Book;
import com.sriram.bean.Employee;

public interface EmployeeService {
	ArrayList<Book> showDataAnalytics() throws ClassNotFoundException, SQLException;
	ArrayList<Book> showTechnology() throws ClassNotFoundException, SQLException;
	ArrayList<Book> showManagement() throws ClassNotFoundException, SQLException;
	boolean selectDataAnalytics(int id) throws ClassNotFoundException, SQLException;
	boolean selectTechnology(int id) throws ClassNotFoundException, SQLException;
	boolean selectManagement(int id) throws ClassNotFoundException, SQLException;
	boolean updateTransaction(int empId,int bookId) throws ClassNotFoundException, SQLException;
	boolean authenticate(String username, String password) throws ClassNotFoundException, SQLException;
	boolean isValidEmployee(int empId) throws ClassNotFoundException, SQLException;
	ArrayList<Book> viewAllBooks() throws ClassNotFoundException, SQLException;
	int returnBook(int empId, int bookId) throws ClassNotFoundException, SQLException;
	String getBookType(int bookId) throws ClassNotFoundException, SQLException;
	boolean removeTransaction(int empId, int bookId) throws ClassNotFoundException, SQLException;
	boolean increaseQuantity(int bookId) throws ClassNotFoundException, SQLException;
	Employee printDetails(int empId, int bookId)throws ClassNotFoundException, SQLException;
	Book printBookDetails(int bookId)throws ClassNotFoundException, SQLException;
}
