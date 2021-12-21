package com.ers.util;

import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.Properties;
	
	
public class ConnectionUtils {

	public static Connection conn;

	public static Connection getConnection(){
		System.out.println("Attempting to connect...");
		try {
			Class.forName("org.postgresql.Driver");
			FileInputStream propInput = new FileInputStream(
					"C://Users//Jeremy//Desktop//ExpenseReimbursementSystem//src//main//resources//config.properties");
			Properties props = new Properties();
			props.load(propInput);
			
			String username = (String) props.getProperty("username");
			String url = (String)props.getProperty("url");
			String password = (String) props.getProperty("password");
			
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;

		} catch (IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
		getConnection();
	}

}
