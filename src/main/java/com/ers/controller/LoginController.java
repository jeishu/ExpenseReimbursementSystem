package com.ers.controller;

import com.ers.dao.EmployeeDao;
import com.ers.model.Employee;

import io.javalin.http.Handler;

public class LoginController {
	static Employee employee;
	
	public static Handler login = ctx -> {
		EmployeeDao employeeDao = new EmployeeDao();
		String username = ctx.formParam("username");
		System.out.println(username);
		String password = ctx.formParam("password");
		System.out.println(password);

		if(employeeDao.getEmployeeByUsernameAndPassword(username, password) != null) {
			employee = employeeDao.getEmployeeByUsernameAndPassword(username, password);
			System.out.println("Logged in");
//			ctx.cookieStore("User", employee);
//			ctx.cookieStore("User Role", employee.getUserRole());
		}
		if(employee.getUserRole().equals("employee")) {
			ctx.redirect("../../html/employee/employeeHome.html");
		}
		if(employee.getUserRole().equals("manager")) {
			ctx.redirect("../../html/manager/managerHome.html");
		}
		ctx.json(employee);
	};
	
}
