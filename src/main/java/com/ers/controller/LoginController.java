package com.ers.controller;

import org.apache.log4j.Logger;

import com.ers.dao.EmployeeDao;
import com.ers.model.Employee;

import io.javalin.http.Handler;

public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);

	static Employee employee;
	
	public static Handler login = ctx -> {
		logger.info("Handler login is initialized.");
		EmployeeDao employeeDao = new EmployeeDao();
		
		String username = ctx.formParam("username");
		System.out.println(username);
		String password = ctx.formParam("password");
		System.out.println(password);
		
		System.out.println(employeeDao.getEmployeeByUsernameAndPassword(username, password));
		
		if(employeeDao.getEmployeeByUsernameAndPassword(username, password) != null) {
			employee = employeeDao.getEmployeeByUsernameAndPassword(username, password);
			System.out.println("Logged in");
			ctx.cookieStore("user", employee);
			ctx.cookieStore("userRole", employee.getUserRole());
			ctx.cookieStore("userId", employee.getUserId());
		}
		if(employee.getUserRole().equals("employee")) {
			ctx.redirect("../../html/employee/employeeHome.html");
		}
		if(employee.getUserRole().equals("manager")) {
			ctx.redirect("../../html/manager/managerHome.html");
		}
//		ctx.json(employee);
	};
}
