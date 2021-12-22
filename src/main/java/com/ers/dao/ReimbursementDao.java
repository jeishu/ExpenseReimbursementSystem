package com.ers.dao;

import java.sql.Connection;

import org.apache.log4j.Logger;

public class ReimbursementDao {
	Logger logger = Logger.getLogger(ReimbursementDao.class);
	
	private Connection connection;
	
	public ReimbursementDao() {
		super();
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
}
