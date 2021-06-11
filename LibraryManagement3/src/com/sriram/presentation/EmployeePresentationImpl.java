package com.sriram.presentation;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import com.sriram.bean.Book;
import com.sriram.bean.Employee;
import com.sriram.service.EmployeeService;
import com.sriram.service.EmployeeServiceImpl;


public class EmployeePresentationImpl implements EmployeePresentation{
	
	Scanner sc=new Scanner(System.in);
	private EmployeeService employeeService=new EmployeeServiceImpl();
//	LocalDate today=LocalDate.now(ZoneId.of("Asia/Kolkata"));
//	LocalDate date;
	@Override
	public void showMenuEmp() {
		System.out.println("=============================================================================");
		System.out.println("1. Lend a book");
		System.out.println("2. Return a book");
		System.out.println("3. View all books");
		System.out.println("4. Exit");
		System.out.println("=============================================================================");
	}
	
	@Override
	public boolean authenticate(String username, String password) {
		
		try {
			if(employeeService.authenticate(username,password)) {
				System.out.println("=============================================================================");
				System.out.println("Login successfull");
				System.out.println("=============================================================================");
				return true;
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void performMenuEmp(int choice) {
		System.out.println("Enter you ID");
		int empId=sc.nextInt();sc.nextLine();
		try {
			if(employeeService.isValidEmployee(empId)) {
				switch(choice) {
				case 1:
					System.out.println("Please enter the type of book you want:\na. Data Analytics\nb. Technology\nc. Management");
					String s=sc.next();
					if(s.equals("a")) {
						ArrayList<Book>books=null;
						try {
							books = employeeService.showDataAnalytics();
						} catch (ClassNotFoundException | SQLException e) {
							
							e.printStackTrace();
						}
						
						for(Book b:books) {
							System.out.println(b);
						}
						System.out.println("=============================================================================");
						System.out.println("Please select the book id from the above available books");
						System.out.println("=============================================================================");
						
						int id=sc.nextInt();
						
						
						boolean success=true;
						try {
							
//							String date1=sc.nextLine();
//							date=LocalDate.parse(date1);
							success=employeeService.selectDataAnalytics(id);
							
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
						if(success) {
							System.out.println("=============================================================================");
							System.out.println("Book issued successfully!!");
							System.out.println("=============================================================================");
							try {
								employeeService.updateTransaction(empId,id);
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
						}
						else {
							System.out.println("=============================================================================");
							System.out.println("Book issue failed!!");
							System.out.println("=============================================================================");
						}
					}
					else if(s.equals("b")) {
						ArrayList<Book>books=null;
						try {
							books = employeeService.showTechnology();
						} catch (ClassNotFoundException | SQLException e) {
							
							e.printStackTrace();
						}
						
						for(Book b:books) {
							System.out.println(b);
						}
						System.out.println("=============================================================================");
						System.out.println("Please select the book id from the above available books");
						System.out.println("=============================================================================");
						int id=sc.nextInt();
				
						boolean success=true;
						try {
							success=employeeService.selectDataAnalytics(id);
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
						if(success) {
							System.out.println("=============================================================================");
							System.out.println("Book issued successfully!!");
							System.out.println("=============================================================================");
							try {
								employeeService.updateTransaction(empId,id);
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
						}
						else {
							System.out.println("Book issue failed!!");
						}
					}
					else if(s.equals("c")) {
						ArrayList<Book>books=null;
						try {
							books = employeeService.showManagement();
						} catch (ClassNotFoundException | SQLException e) {
							
							e.printStackTrace();
						}
						
						for(Book b:books) {
							System.out.println(b);
						}
						System.out.println("=============================================================================");
						System.out.println("Please select the book id from the above available books");
						System.out.println("=============================================================================");
						int id=sc.nextInt();
				
						boolean success=true;
						try {
							success=employeeService.selectDataAnalytics(id);
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
						if(success) {
							System.out.println("=============================================================================");
							System.out.println("Book issued successfully!!");
							System.out.println("=============================================================================");
							try {
								employeeService.updateTransaction(empId,id);
							} catch (ClassNotFoundException | SQLException e) {
								e.printStackTrace();
							}
						}
						else {
							System.out.println("=============================================================================");
							System.out.println("Book issue failed!!");
							System.out.println("=============================================================================");
						}
					}	
						break;
				case 2:
					System.out.println("=============================================================================");
					System.out.println("Please enter the bookId you have lent");
					System.out.println("=============================================================================");
					int bookId=sc.nextInt();
					int days=employeeService.returnBook(empId,bookId);
					employeeService.removeTransaction(empId,bookId);
					employeeService.increaseQuantity(bookId);
					Employee e=employeeService.printDetails(empId,bookId);
					Book b=employeeService.printBookDetails(bookId);
					System.out.println(e+" "+b);
					String bookType=employeeService.getBookType(bookId);
					if(bookType.equalsIgnoreCase("data analytics")) {
						if(days>7) {
							days-=7;
							System.out.println("=============================================================================");
							System.out.println("You are late by "+days+" days so,");
							System.out.println("Please pay the fine of Rs. "+days*5);
							System.out.println("=============================================================================");
						}
						else {
							System.out.println("=============================================================================");
							System.out.println("Returned successfully with no fine");
							System.out.println("=============================================================================");
						}
					}
					if(bookType.equalsIgnoreCase("technology")) {
						if(days>7) {
							
							days-=7;
							System.out.println("=============================================================================");
							System.out.println("You are late by "+days+" days so,");
							System.out.println("Please pay the fine of Rs. "+days*6);
							System.out.println("=============================================================================");
						}
						else {
							System.out.println("Returned successfully with no fine");
						}
					}
					if(bookType.equalsIgnoreCase("management")) {
						if(days>7) {
							
							days-=7;
							System.out.println("=============================================================================");
							System.out.println("You are late by "+days+" days so,");
							System.out.println("Please pay the fine of Rs. "+days*7);
							System.out.println("=============================================================================");
						}
						else {
							System.out.println("=============================================================================");
							System.out.println("Returned successfully with no fine");
							System.out.println("=============================================================================");
						}
					}
					break;
				case 3:
					ArrayList<Book>books = employeeService.viewAllBooks();
					for(Book b1:books) {
						System.out.println(b1);
					}
					break;
				case 4:
					System.exit(0);
			
				}
			}
			else {
				System.out.println("Please enter valid ID");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
