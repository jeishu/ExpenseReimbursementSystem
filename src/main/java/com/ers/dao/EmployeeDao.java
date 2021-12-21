package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ers.model.Employee;
import com.ers.util.ConnectionUtils;

public class EmployeeDao {
	Logger logger = Logger.getLogger(EmployeeDao.class);
	private Connection connection;
	
	public EmployeeDao() {
		super();
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public Employee getEmployeeByUsernameAndPassword(String username, String password) {
		
		Employee employee = new Employee();
		
		try(Connection connection = ConnectionUtils.getConnection()){
			String sql = "SELECT * FROM employee WHERE username =? AND emp_password =?";
			
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password);
			
			
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String userRole = rs.getString("user_role");
				String email = rs.getString("email");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String password1 = rs.getString("emp_password");
				String username1 = rs.getString("username");
				
				
				employee = new Employee(id, userRole, email, firstName, lastName, password1, username1);
				
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
//	public static void main(String[] args) {
//		EmployeeDao employee = new EmployeeDao();
//		employee.getEmployeeByUsernameAndPassword("johndoe","password").toString();
//	} 
	
}
