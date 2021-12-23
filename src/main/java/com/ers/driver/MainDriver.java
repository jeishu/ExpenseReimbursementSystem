package com.ers.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ers.controller.EmployeeController;
import com.ers.controller.LoginController;
import com.ers.controller.ReimbursementController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class MainDriver {

	private static final String LOG_FILE = "log4j.properties";
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(MainDriver.class);
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(LOG_FILE));
			PropertyConfigurator.configure(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logger.setLevel(Level.ALL);
		logger.warn("LOG: Warning");
		logger.error("LOG: ERROR");
		logger.fatal("LOG: Fatal");
		logger.info("--------------");
		
		Javalin app = Javalin.create(ctx -> {
			logger.info("Start: http://localhost:8081/");
			ctx.addStaticFiles("web", Location.CLASSPATH);
			ctx.enableCorsForAllOrigins();
		}).start(8081);
		//http://localhost:8081/
		
		app.post("/login/login", LoginController.login);
		app.get("/login/login", ctx -> {
			ctx.json(ctx.cookieStore("user"));
		});
		
		app.get("/employee", EmployeeController.getAllEmployee);
		
		app.post("/update", EmployeeController.updateEmployeeProfile);
		
		//app.post("/employee", EmployeeController.addEmployee); get back to this
		app.get("/reimbursement", ReimbursementController.getAllReimbursement);
		app.post("/reimbursement", ReimbursementController.submitNewReimbursement);

		
	}
	

}
