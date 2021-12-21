package com.ers.controller;

import com.ers.dao.EmployeeDaoImp;

import io.javalin.http.Handler;

public class LoginController {
	
	public static Handler loginEmployee = (ctx) -> {
		EmployeeDaoImp employeeDao = new EmployeeDaoImp(); 
		boolean login;
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		employeeDao.getLoginByUsernamePassword(username, password);
		if(login = true) {
			ctx.redirect("/html/employee/employeeHome.html");
		} else {
			ctx.status(404);
		}
		
	};
	
	public static Handler loginManager = ctx -> {
		EmployeeDaoImp employeeDao = new EmployeeDaoImp(); 
		boolean login;
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		employeeDao.getLoginByUsernamePassword(username, password);
		if(login = true) {
			ctx.redirect("/html/manager/managerHome.html");
		} else {
			ctx.status(404);

		}
	};
}
