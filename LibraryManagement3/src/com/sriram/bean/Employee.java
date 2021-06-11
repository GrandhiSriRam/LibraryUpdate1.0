package com.sriram.bean;

public class Employee {
	
	private int empId;
	private String employeeFirstName;
	//private Address address;
	private String employeeLastName;
	private String department;
	private String email;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", employeeFirstName=" + employeeFirstName + ", employeeLastName="
				+ employeeLastName + ", department=" + department + ", email=" + email + "]";
	}
	public Employee(int empId, String employeeFirstName, String employeeLastName, String department, String email) {
		super();
		this.empId = empId;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.department = department;
		this.email = email;
	}
	public Employee() {
		
	}
	
	
}
