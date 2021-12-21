package com.ers.dao;

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
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public Employee getLoginByUsernamePassword(String username, String password) {
		
		Employee employee = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
			Root<Employee> root = query.from(Employee.class);
			query.select(root);
			Query<Employee> q = session.createQuery(query);
			employee = q.getSingleResult();
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		
		return null;
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
