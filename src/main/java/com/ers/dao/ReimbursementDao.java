package com.ers.dao;

import java.util.List;

import com.ers.model.Reimbursement;

public interface ReimbursementDao {
	
	//find reimbursement by id
	Reimbursement getReimbursementByID(int id);
	
	//grabs all the reimbursement from the database
	List<Reimbursement> getAllReimbursement();
	
	//grabs all the reimbursement tickets by a specific user
	List<Reimbursement> getReimbursementByUserId(int id);
	
	//adds a new reimbursemnt to the database
	int addReimbursement(Reimbursement newTicket);
	
	//reimbursement approval request by ID and who resolved it.
	boolean requestApproval(String choice, int id, int resolverId);
		
}
