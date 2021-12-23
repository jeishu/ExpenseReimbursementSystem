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

import com.ers.model.Reimbursement;
import com.ers.util.ConnectionUtils;
import com.ers.util.HibernateUtil;

public class ReimbursementDao {
	Logger logger = Logger.getLogger(ReimbursementDao.class);
	
	private Connection connection;
	
	public ReimbursementDao() {
		super();
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public Reimbursement allReimbursement(ResultSet rs) throws SQLException {
		Reimbursement reimbursement = new Reimbursement();
		
		reimbursement.setReimbursementId(rs.getInt("reimbursement_id"));
		reimbursement.setAccepted(rs.getBoolean("accepted"));
		reimbursement.setAmount(rs.getFloat("amount"));
		reimbursement.setDescription(rs.getString("description"));
		reimbursement.setReimbursementType(rs.getString("reimbursement_type"));
		reimbursement.setResolveTime(rs.getString("resolve_time"));
		reimbursement.setResolved(rs.getBoolean("resolved"));
		reimbursement.setSubmitTime(rs.getString("submit_time"));
		reimbursement.setAuthorId(rs.getInt("author_id"));
		reimbursement.setResolvedId(rs.getInt("resolved_id"));
		
		return reimbursement;
	}
	
	//Getting all Reimbursement Tickets
	public Set<Reimbursement> getAllReimbursement(){
		try(Connection connection = ConnectionUtils.getConnection()) {
			String sql = "SELECT * FROM services";
			PreparedStatement ps = connection.prepareStatement(sql);
			System.out.println(ps);
			
			Set<Reimbursement> reimbursementList = new HashSet<Reimbursement>();
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursement reimbursement = allReimbursement(rs);
				reimbursementList.add(reimbursement);
			}
			return reimbursementList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean submitReimbursements(Reimbursement reimbursement) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			session.beginTransaction();
//			reimbursement.setReimbursementId(reimbursement.getReimbursementId());
			reimbursement.setAccepted(reimbursement.isAccepted());
			reimbursement.setAmount(reimbursement.getAmount());
			reimbursement.setDescription(reimbursement.getDescription());
			reimbursement.setReimbursementType(reimbursement.getReimbursementType());
			reimbursement.setResolveTime(reimbursement.getResolveTime());
			reimbursement.setResolved(reimbursement.isResolved());
			reimbursement.setSubmitTime(reimbursement.getSubmitTime());
			reimbursement.setAuthorId(reimbursement.getAuthorId());
			session.save(reimbursement);
			session.getTransaction().commit();
		}
		return true;
	}
	
	//updating reimbursements for managers
	public boolean updateReimbursements(Reimbursement reimbursement) {
		logger.info("Updating reimbursements for approval.");
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			
			Reimbursement updatedReimbursement = session.load(Reimbursement.class, reimbursement.getReimbursementId());
			updatedReimbursement.setAccepted(reimbursement.isAccepted());
			updatedReimbursement.getResolveTime();
			updatedReimbursement.setResolved(true);
			updatedReimbursement.setResolvedId(reimbursement.getReimbursementId());
			
			session.update(updatedReimbursement);
			
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
