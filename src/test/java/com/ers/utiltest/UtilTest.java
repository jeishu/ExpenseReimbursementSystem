package com.ers.utiltest;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ers.util.ConnectionUtils;
import com.ers.util.HibernateUtil;

public class UtilTest {


	@Test
	public void testHibernateUtil() {
		SessionFactory result = HibernateUtil.getSessionFactory();
		assertNotEquals(null, result);
	}
	
	@Test
	public void testConnectionUtil() {
		Connection result = ConnectionUtils.getConnection();
		assertNotEquals(null, result);
	}

}
