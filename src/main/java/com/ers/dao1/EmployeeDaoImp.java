package com.ers.dao1;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.model.Employee;
import com.ers.model.Reimbursement;
import com.ers.util.HibernateUtil;

public class EmployeeDaoImp implements EmployeeDao{
	
	
	//finds user by user id
	@Override
	public Employee getEmployeeById(int id) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = new Employee();
			Transaction transaction = session.beginTransaction();
			
			employee = session.get(Employee.class, id);
			
			transaction.commit();
			employee = employee;
			session.close();
			return employee;
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return null;
	}

	//grabs all the current user from the database
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Employee> getAllEmployee(int id) {
		List<Employee> employeeList = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			String hql = "FROM employee WHERE user_id = ? ORDER BY ASC";
			
			Query query = session.createQuery(hql);
			query.setParameter(1, id);
			employeeList = query.list();
			
			transaction.commit();
			session.close();
			return employeeList;
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		
		return employeeList;
	}

	//finds user by username and password
	@SuppressWarnings("rawtypes")
	@Override
	public Employee getLoginByUsernamePassword(String username, String password) {
		
		Employee employee = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			String nql = "SELECT * FROM employee where username = "+username+" AND password = "+password+";";
			Query query = session.createNativeQuery(nql, Employee.class);
			
			employee = (Employee) query.getSingleResult();
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		
		return employee;
	}

	@Override
	public int addEmployee(Employee newEmp) {

		return 0;
	}

	@Override
	public int deleteEmployee(Employee deleteEmp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reimbursement> pendingList(int id) {
		
		return null;
	}

	@Override
	public List<Reimbursement> resolvedList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
