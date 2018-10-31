package com.wp.DTL645_Listener.dataUtils;
// default package

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 	* A data access object (DAO) providing persistence and search support for Electric entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Electric
  * @author MyEclipse Persistence Tools 
 */
public class ElectricDAO extends BaseHibernateDAO  {
	     private static final Logger log = Logger.getLogger(ElectricDAO.class);
		//property constants
	public static final String TIME = "time";
	public static final String SSZSZGL = "sszszgl";
	public static final String SSZYGGL = "sszyggl";
	public static final String AV = "av";
	public static final String BV = "bv";
	public static final String CV = "cv";
	public static final String AA = "aa";
	public static final String BA = "ba";
	public static final String CA = "ca";
	public static final String ATTR1 = "attr1";
	public static final String ATTR2 = "attr2";
	public static final String ATTR3 = "attr3";
	public static final String ATTR4 = "attr4";
	public static final String ATTR5 = "attr5";
	public static final String ATTR6 = "attr6";
	public static final String ATTR7 = "attr7";
	public static final String ATTR8 = "attr8";



    
    public void save(Electric transientInstance) {
        log.debug("saving Electric instance");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
        	session.save(transientInstance);
        	tx.commit();
            log.debug("save successful");
        } catch (RuntimeException re) {
        	tx.rollback();
            log.error("save failed", re);
            throw re;
        } finally {
        	session.close();
        }
    }
    
	public void delete(Electric persistentInstance) {
        log.debug("deleting Electric instance");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
        	session.delete(persistentInstance);
        	tx.commit();
            log.debug("delete successful");
        } catch (RuntimeException re) {
        	tx.rollback();
            log.error("delete failed", re);
            throw re;
        } finally {
        	session.close();
        }
    }
    
    public Electric findById( java.lang.Integer id) {
        log.debug("getting Electric instance with id: " + id);
        try {
            Electric instance = (Electric) getSession()
                    .get(Electric.class, id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Electric instance) {
        log.debug("finding Electric instance by example");
        try {
            List results = getSession()
                    .createCriteria(Electric.class)
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Electric instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Electric as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTime(Object time
	) {
		return findByProperty(TIME, time
		);
	}
	
	public List findBySszszgl(Object sszszgl
	) {
		return findByProperty(SSZSZGL, sszszgl
		);
	}
	
	public List findBySszyggl(Object sszyggl
	) {
		return findByProperty(SSZYGGL, sszyggl
		);
	}
	
	public List findByAv(Object av
	) {
		return findByProperty(AV, av
		);
	}
	
	public List findByBv(Object bv
	) {
		return findByProperty(BV, bv
		);
	}
	
	public List findByCv(Object cv
	) {
		return findByProperty(CV, cv
		);
	}
	
	public List findByAa(Object aa
	) {
		return findByProperty(AA, aa
		);
	}
	
	public List findByBa(Object ba
	) {
		return findByProperty(BA, ba
		);
	}
	
	public List findByCa(Object ca
	) {
		return findByProperty(CA, ca
		);
	}
	
	public List findByAttr1(Object attr1
	) {
		return findByProperty(ATTR1, attr1
		);
	}
	
	public List findByAttr2(Object attr2
	) {
		return findByProperty(ATTR2, attr2
		);
	}
	
	public List findByAttr3(Object attr3
	) {
		return findByProperty(ATTR3, attr3
		);
	}
	
	public List findByAttr4(Object attr4
	) {
		return findByProperty(ATTR4, attr4
		);
	}
	
	public List findByAttr5(Object attr5
	) {
		return findByProperty(ATTR5, attr5
		);
	}
	
	public List findByAttr6(Object attr6
	) {
		return findByProperty(ATTR6, attr6
		);
	}
	
	public List findByAttr7(Object attr7
	) {
		return findByProperty(ATTR7, attr7
		);
	}
	
	public List findByAttr8(Object attr8
	) {
		return findByProperty(ATTR8, attr8
		);
	}
	

	public List findAll() {
		log.debug("finding all Electric instances");
		try {
			String queryString = "from Electric";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Electric merge(Electric detachedInstance) {
        log.debug("merging Electric instance");
        try {
            Electric result = (Electric) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Electric instance) {
        log.debug("attaching dirty Electric instance");
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
        	session.saveOrUpdate(instance);
        	tx.commit();
            log.debug("attach successful");
        } catch (RuntimeException re) {
        	tx.rollback();
            log.error("attach failed", re);
            throw re;
        } finally {
        	session.close();
        }
    }
    
    public void attachClean(Electric instance) {
        log.debug("attaching clean Electric instance");
        try {
                      	getSession().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}