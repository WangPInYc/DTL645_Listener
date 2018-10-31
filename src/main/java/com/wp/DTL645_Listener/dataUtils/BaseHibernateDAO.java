package com.wp.DTL645_Listener.dataUtils;

import org.hibernate.Session;

/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		//FIXME: Implement this method
		return HibernateSessionFactory.getSession();
	}
	
}