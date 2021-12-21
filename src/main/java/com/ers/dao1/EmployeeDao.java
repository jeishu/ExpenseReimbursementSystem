package com.ers.dao1;

import java.util.List;

import com.ers.model.Employee;
import com.ers.model.Reimbursement;

public interface EmployeeDao {
	
	//finds user by user id
	Employee getEmployeeById(int id);
	
	//grabs all the current user from the database
	List<Employee> getAllEmployee(int id);
	
	//finds user by username and password
	Employee getLoginByUsernamePassword(String username, String password);
	
	//add new user to the database
	int addEmployee(Employee newEmp);
	
	//delete existing user from the database
	int deleteEmployee(Employee deleteEmp);
	
	//view list of pending reimbursement
	List<Reimbursement> pendingList(int id);
	
	//view list of resolved reimbursement
	List<Reimbursement> resolvedList(int id);
	
	//checks for login info
	boolean login(String username, String password);
	
}
