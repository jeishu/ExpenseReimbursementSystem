package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ers.model.Employee;
import com.ers.util.ConnectionUtils;
import com.ers.util.HibernateUtil;

public class EmployeeDao {
	Logger logger = Logger.getLogger(EmployeeDao.class);
	
	private Connection connection;
	
	public EmployeeDao() {
		super();
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public Employee allEmployee(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		
		employee.setUserId(rs.getInt("user_id"));
		employee.setUserRole(rs.getString("user_role"));
		employee.setEmail(rs.getString("email"));
		employee.setFirstName(rs.getString("first_name"));
		employee.setLastName(rs.getString("last_name"));
		employee.setPassword(rs.getString("emp_password"));
		employee.setUserName(rs.getString("username"));
		
		return employee;
	}
	
	//getting login info
	public Employee getEmployeeByUsernameAndPassword(String username, String password) {
		
		Employee employee = new Employee();
		
		try(Connection connection = ConnectionUtils.getConnection()){
			String sql = "SELECT * FROM employee WHERE username =? AND emp_password =?";
			
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, username);
			ps.setString(2, password);
			
			
			System.out.println("getEMployeeByUsernameAndPassword: "+ ps);
			
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
	
	//Getting all Employees
	public Set<Employee> getAllEmployee() {
		
		try(Connection connection = ConnectionUtils.getConnection()){
			String sql = "SELECT * FROM employee ORDER BY user_id ASC";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			System.out.println(ps);
			
			Set<Employee> employeeList = new HashSet<Employee>();
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Employee employee = allEmployee(rs);
				
				employeeList.add(employee);
			}
			return employeeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateEmployeeInfo(Employee employee) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			Employee employeeUpdated = session.load(Employee.class, employee.getUserId());
			employeeUpdated.setUserId(employee.getUserId());
			employeeUpdated.setUserRole(employee.getUserRole());
			employeeUpdated.setEmail(employee.getEmail());
			employeeUpdated.setFirstName(employee.getFirstName());
			employeeUpdated.setLastName(employee.getLastName());
			employeeUpdated.setPassword(employee.getPassword());
			employeeUpdated.setUserName(employee.getUsername());
			
			session.update(employeeUpdated);
			transaction.commit();
			session.close();
		}
		return false;
	}
	// NEED TO GET THIS TO WORK	
	public boolean addEmployee(Employee employee) {
		try(Connection connection = ConnectionUtils.getConnection()){
			String sql = "INSERT INTO employee WHERE VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, employee.getUserId());
			ps.setString(2, employee.getUserRole());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getFirstName());
			ps.setString(5, employee.getLastName());
			ps.setString(6, employee.getPassword());
			ps.setString(7, employee.getUsername());
			
			int update = ps.executeUpdate();
			if(update == 1) {
				return true;
			}
			
			System.out.println(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		EmployeeDao employee = new EmployeeDao();
//		employee.getEmployeeByUsernameAndPassword("johndoe","password").toString();
//	} 
	
}
