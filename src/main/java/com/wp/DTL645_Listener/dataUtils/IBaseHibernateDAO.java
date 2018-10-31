package com.wp.DTL645_Listener.dataUtils;

import org.hibernate.Session;

/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAO {
	public Session getSession();
}