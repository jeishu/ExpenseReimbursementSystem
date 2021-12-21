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
		login = employeeDao.getLogin();
		if(login = true) {
			ctx.redirect("");
		} else {
			
		}
		
	};
	
	public static Handler loginManager = ctx -> {
		
	};
}
