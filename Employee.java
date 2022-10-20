package com.example.demo;

import java.math.BigDecimal;

public class Employee {

	private Integer EmployeeId;
	private String EmployeeName;
	private  BigDecimal EmployeeSalary;
	private String EmployeeStatus;
	public Integer getEmployeeId() {
		return EmployeeId;
	}
	@Override
	public String toString() {
		return "Employee [EmployeeId=" + EmployeeId + ", EmployeeName=" + EmployeeName + ", EmployeeSalary="
				+ EmployeeSalary + ", EmployeeStatus=" + EmployeeStatus + "]";
	}
	public void setEmployeeId(Integer employeeId) {
		EmployeeId = employeeId;
	}
	public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	public BigDecimal getEmployeeSalary() {
		return EmployeeSalary;
	}
	public void setEmployeeSalary(BigDecimal employeeSalary) {
		EmployeeSalary = employeeSalary;
	}
	public String getEmployeeStatus() {
		return EmployeeStatus;
	}
	public void setEmployeeStatus(String employeeStatus) {
		EmployeeStatus = employeeStatus;
	}
	
	
}
