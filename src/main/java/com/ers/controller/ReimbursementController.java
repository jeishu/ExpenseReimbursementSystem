package com.ers.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.ers.dao.ReimbursementDao;
import com.ers.model.Reimbursement;

import io.javalin.http.Handler;


public class ReimbursementController  {
	
	//get all the reimbursement tickets and returns to the client side
	public static Handler getAllReimbursement = ctx -> {
		ReimbursementDao reimbursementDao = new ReimbursementDao();
		
		ctx.json(reimbursementDao.getAllReimbursement());
	};
	
	
	//submit new tickets
	public static Handler submitNewReimbursement = ctx -> {
		int id = ctx.cookieStore("userId");
		
		Reimbursement reimbursement = new Reimbursement();
		
		ReimbursementDao reimbursementDao = new ReimbursementDao();
		
		DateFormat df = new SimpleDateFormat("yyy/MM/dd HH:mm");
		Date date = new Date();
		
		String reimbursementType = ctx.formParam("reim-type");
		
		String description = ctx.formParam("description");
		int amount = Integer.parseInt(ctx.formParam("amount"));
		
		Random random = new Random();
		int randomId = random.nextInt(999);
		
		reimbursement.setReimbursementId(randomId);
		reimbursement.setAccepted(false);
		reimbursement.setAmount(amount);
		reimbursement.setDescription(description);
		reimbursement.setReimbursementType(reimbursementType);
		reimbursement.setResolveTime(null);
		reimbursement.setResolved(false);
		reimbursement.setSubmitTime(df.format(date));
		reimbursement.setAuthorId(id);
		
		reimbursementDao.insertReimbursements(reimbursement);
		ctx.redirect("../../html/employee/employeeHome.html");
		
	};

}
