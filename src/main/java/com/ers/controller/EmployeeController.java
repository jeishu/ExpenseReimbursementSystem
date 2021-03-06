package com.ers.controller;

import org.apache.log4j.Logger;

import com.ers.dao.EmployeeDao;
import com.ers.model.Employee;

import io.javalin.http.Handler;

public class EmployeeController {
	
	private static final Logger logger = Logger.getLogger(EmployeeController.class);

	
	static Employee employee;
	
	//gets all the employee and returns to the client side
	public static Handler getAllEmployee = ctx -> {
		logger.info("Handler getAllEmployee is initialized");
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
		Employee employee = new Employee();
		
		employee.setUserId(ctx.cookieStore("userId"));
		employee.setUserRole(ctx.cookieStore("userRole"));
		employee.setEmail(ctx.formParam("p-email"));
		employee.setFirstName(ctx.formParam("p-first-name"));
		employee.setLastName(ctx.formParam("p-last-name"));
		employee.setPassword(ctx.formParam("p-password"));
		employee.setUserName(ctx.formParam("p-username"));
		
		employeeDao.updateEmployeeInfo(employee);
		
		ctx.redirect("../../html/employee/employeeHome.html");
	};
}
