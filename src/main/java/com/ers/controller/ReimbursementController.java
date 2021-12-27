package com.ers.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import com.ers.dao.ReimbursementDao;
import com.ers.model.Reimbursement;

import io.javalin.http.Handler;


public class ReimbursementController  {
	
	private static final Logger logger = Logger.getLogger(ReimbursementController.class);
	
	//get all the reimbursement tickets and returns to the client side
	public static Handler getAllReimbursement = ctx -> {
		logger.info("Handler getAllReimbursement is initialized.");
		ReimbursementDao reimbursementDao = new ReimbursementDao();
		
		ctx.json(reimbursementDao.getAllReimbursement());
	};
	
	
	//submit new tickets
	public static Handler submitNewReimbursement = ctx -> {
		logger.info("Submitting new reimbursement ticket.");

		int id = ctx.cookieStore("userId");
		
		Reimbursement reimbursement = new Reimbursement();
		
		ReimbursementDao reimbursementDao = new ReimbursementDao();
		
		DateFormat df = new SimpleDateFormat("yyy/MM/dd");
		Date date = new Date();
		
		String reimbursementType = ctx.formParam("reim-type");
		
		String description = ctx.formParam("description");
		int amount = Integer.parseInt(ctx.formParam("amount"));
		
//		reimbursement.setReimbursementId(randomId); //this was serialized in DB so we dont need to generate a random value
		reimbursement.setAccepted(false);
		reimbursement.setAmount(amount);
		reimbursement.setDescription(description);
		reimbursement.setReimbursementType(reimbursementType);
		reimbursement.setResolveTime(null);
		reimbursement.setResolved(false);
		reimbursement.setSubmitTime(df.format(date));
		reimbursement.setAuthorId(id);
		
		reimbursementDao.submitReimbursements(reimbursement);
		ctx.redirect("../../html/employee/employeeHome.html");
	};
	
	//Approval for new tickets
	public static Handler reimbursementApproval = ctx -> {
		logger.info("New reimbursement ticket waiting for approval.");

		int id = ctx.cookieStore("userId");

		Reimbursement reimbursement = new Reimbursement();
		ReimbursementDao reimbursementDao = new ReimbursementDao();
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
		String reimbursementId = ctx.formParam("reimId");
		
		String reimbursementOption = ctx.formParam("reim-options");
		
		boolean accepted;
		if (reimbursementOption.equals("true")) {
			logger.info("Reimbursement approved");
			accepted = true;
		} else {
			logger.info("Reimbursement denied");
			accepted = false;
		}
		reimbursement.setResolvedId(id);
		reimbursement.setReimbursementId(Integer.parseInt(reimbursementId));
		reimbursement.setResolveTime(df.format(date));
		reimbursement.setAccepted(accepted);
		
		reimbursementDao.updateReimbursements(reimbursement);
		
		ctx.redirect("../../html/manager/managerResolved.html");
	};
}
