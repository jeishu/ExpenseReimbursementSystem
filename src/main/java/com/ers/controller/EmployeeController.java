package com.ers.controller;

import com.ers.dao.EmployeeDao;
import com.ers.model.Employee;

import io.javalin.http.Handler;

public class EmployeeController {
	static Employee employee;
	
	public static Handler getAllEmployee = ctx -> {
		EmployeeDao employeeDao = new EmployeeDao();
		
		ctx.json(employeeDao.getAllEmployee());
	};
}
