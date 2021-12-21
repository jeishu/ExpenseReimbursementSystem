package com.ers.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ers.model.Employee;
import com.ers.model.Reimbursement;
import com.ers.util.HibernateUtil;

public class EmployeeDaoImp implements EmployeeDao{
	private boolean login;
	private boolean update;
	private boolean submit;
	private String username;
	private String password;
	private String email;
	private String name;
	private int id;
	private float amount;
	private Employee employee;
	private List<Reimbursement> pendingList;
	private List<Reimbursement> resolvedList;
	
	public boolean getLogin() {
		return login;
	}
	
	//finds user by user id
	@Override
	public Employee getEmployeeById(int id) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			this.id = id;
			Employee employee = new Employee();
			Transaction transaction = session.beginTransaction();
			
			employee = session.get(Employee.class, id);
			
			transaction.commit();
			this.employee = employee;
			session.close();
			return this.employee;
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return this.employee;
	}

	//grabs all the current user from the database
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Employee> getAllEmployee(int id) {
		this.id = id;
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
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public boolean getLoginByUsernamePassword(String username, String password) {
		this.username = username;
		this.password = password;
		
		Employee employee = null;
		boolean login = false;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			String hql = "FROM employee WHERE username = ? AND password = ?;";
			Query query = session.createQuery(hql);
			query.setParameter(1, username);
			query.setParameter(2, password);
			
			employee = (Employee) query.uniqueResult();
			
			login = true;
			this.login = login;
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		
		return this.login;
	}

	@Override
	public int addEmployee(Employee newEmp) {
//		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
//			
//			Transaction transaction = session.beginTransaction();
//			transaction.commit();
//			
//			
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} 
		return 0;
	}

	@Override
	public int deleteEmployee(Employee deleteEmp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reimbursement> pendingList(int id) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			this.id = id;
			List<Reimbursement> pendingList;
			Transaction transaction = session.beginTransaction();
			String hql = "FROM services WHERE ";
			
			Query query = session.createQuery(hql);
			
			
			transaction.commit();
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
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
