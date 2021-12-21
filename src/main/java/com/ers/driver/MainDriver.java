package com.ers.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ers.controller.LoginController;

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
			ctx.enableCorsForAllOrigins();
			ctx.addStaticFiles("web", Location.CLASSPATH);
		}).start(8081);
		
		//login
		app.get("/login/login-employee", LoginController.loginEmployee);
		app.get("/login/login-manager", LoginController.loginManager);
		
		//app.get("/", EmployeeController.);
		//app.get("/login", EmployeeController.getLogin);
	}

}
