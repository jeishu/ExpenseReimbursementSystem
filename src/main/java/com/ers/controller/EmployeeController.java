package com.ers.controller;

import com.ers.dao.EmployeeDao;
import com.ers.model.Employee;

import io.javalin.http.Handler;

public class EmployeeController {
	static Employee employee;
	
	//gets all the employee and returns to the client side
	public static Handler getAllEmployee = ctx -> {
		EmployeeDao employeeDao = new EmployeeDao();
		
		ctx.json(employeeDao.getAllEmployee());
	};
	
	//adding new employee
	public static Handler addEmployee = ctx -> {
		EmployeeDao employeeDao = new EmployeeDao();
		
		ctx.json(employeeDao.addEmployee(null));
	};
	
	//viewing an employee profile - come back to this
	public static Handler viewEmployeeProfile = ctx -> {
		EmployeeDao employeeDao = new EmployeeDao();
		
		Employee employee = ctx.bodyAsClass(Employee.class);
		
		String username = employee.getUsername();
		String password = employee.getPassword();
		ctx.json(employeeDao.getEmployeeByUsernameAndPassword(username, password));
	};
	
	//updating employeeinfo
	public static Handler updateEmployeeProfile = ctx -> {
		EmployeeDao employeeDao = new EmployeeDao();
		int userId = ctx.cookieStore("userId");
	};
}
