package com.asu.edu.daos;

// Generated Oct 21, 2014 9:01:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class User.
 * @see com.asu.models.User
 * @author Hibernate Tools
 */
public class UserHome {

	private static final Log log = LogFactory.getLog(UserHome.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
	}
	
	public UserHome() {
	}


	/*protected SessionFactory getSessionFactory() {
		try {
			SessionFactory sessionFactory = new Configuration().configure(
		              "hibernate.cfg.xml")
	                  .buildSessionFactory();
return sessionFactory;
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}
*/
	
	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		Transaction transaction = null;
		try {			
			//transaction = sessionFactory.getCurrentSession().beginTransaction();
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
			//transaction.commit();
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			//transaction.rollback();
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			re.printStackTrace();
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.String id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) sessionFactory.getCurrentSession().get(
					"com.asu.models.User", id);
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

	public User findByUserName(java.lang.String id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) sessionFactory.getCurrentSession().get(
					"com.asu.edu.daos.User", id);
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
	
	public List<User> findAllInActive() {
		log.debug("finding User instance by example");
		try {
			Criteria allDisabledCriteria =sessionFactory.getCurrentSession().createCriteria(User.class);
			allDisabledCriteria.add(Expression.eq("enabled", false));
			List<User> results = (List<User>) sessionFactory
					.getCurrentSession().createCriteria(User.class)
					.add(Expression.eq("enabled", false)).list();
			
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
