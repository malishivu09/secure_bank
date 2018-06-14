package com.asu.edu.daos;

// Generated Oct 21, 2014 9:01:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Account.
 * @see com.asu.models.Account
 * @author Hibernate Tools
 */

public class AccountHome {

	private static final Log log = LogFactory.getLog(AccountHome.class);
	
//	private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
	
	//@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }
	
	
	//private SessionFactory sessionFactory = getSessionFactory();

	public AccountHome() {
	}

//	protected SessionFactory getSessionFactory() {
//		try {
//			SessionFactory sessionFactory = new Configuration().configure(
//					              "hibernate.cfg.xml")
//				                  .buildSessionFactory();
//			return sessionFactory;
//		} catch (Exception e) {
//			log.error("Could not locate SessionFactory in JNDI", e);
//			throw new IllegalStateException(
//					"Could not locate SessionFactory in JNDI");
//		}
//	}
	
	
	public void persist(Account transientInstance) {
		log.debug("persisting Account instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Account instance) {
		log.debug("attaching dirty Account instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Account instance) {
		log.debug("attaching clean Account instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Account persistentInstance) {
		log.debug("deleting Account instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Account merge(Account detachedInstance) {
		log.debug("merging Account instance");
		try {
			Account result = (Account) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Account findById(java.lang.String id) {
		log.debug("getting Account instance with id: " + id);
		try {
			
			Account instance = (Account) sessionFactory.getCurrentSession()
					.get(Account.class, "123456");
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public Account findByUsername(java.lang.String id) {
		log.debug("getting Account instance with id: " + id);
		try {
			Account instance = null;
			
			String hql = "select * from account s where s.username = :username";
			@SuppressWarnings("unchecked")
			List<Account> result = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Account.class)
			.setParameter("username", id)
			.list();
			
			//List<Account> listAccounts =  sessionFactory.getCurrentSession().createSQLQuery("from account where username = \"tejasvi\"").list();
			//List<Account> listAccounts =  (List<Account>) sessionFactory.getCurrentSession().get(Account.class, id);
			if(result.size() >0)
			{
				instance = (Account) result.get(0);
			}
			
//			for(Account in : listAccounts){
//				log.info("Person List::"+in);
//				
//				instance = in;
//			}
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Account findByAccountNo(java.lang.String accNo) {
		log.debug("getting Account instance with id: " + accNo);
		try {
			Account instance = null;
			
			String hql = "select * from account s where s.accountNumber = :accountNumber";
			@SuppressWarnings("unchecked")
			List<Account> result = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Account.class)
			.setParameter("accountNumber", accNo)
			.list();
			
			if(result.size() >0)
			{
				instance = (Account) result.get(0);
			}
			
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public List<Account> findByExample(Account instance) {
		log.debug("finding Account instance by example");
		try {
			List<Account> results = (List<Account>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.asu.edu.daos.Account")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
}
