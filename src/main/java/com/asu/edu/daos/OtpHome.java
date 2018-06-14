package com.asu.edu.daos;

// Generated Oct 21, 2014 9:01:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Otp.
 * @see com.asu.models.Otp
 * @author Hibernate Tools
 */
public class OtpHome {

	private static final Log log = LogFactory.getLog(OtpHome.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void persist(Otp transientInstance) {
		log.debug("persisting Otp instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Otp instance) {
		log.debug("attaching dirty Otp instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Otp instance) {
		log.debug("attaching clean Otp instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Otp persistentInstance) {
		log.debug("deleting Otp instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Otp merge(Otp detachedInstance) {
		log.debug("merging Otp instance");
		try {
			Otp result = (Otp) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Otp findById(java.lang.String id) {
		log.debug("getting Otp instance with id: " + id);
		try {
			Otp instance = (Otp) sessionFactory.getCurrentSession().get(
					"com.asu.edu.daos.Otp", id);
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
	
	public List<Otp> findOtpByUsername(String username) {
		try {
			List<Otp> results = (List<Otp>) sessionFactory
					.getCurrentSession().createCriteria(Otp.class)
					.add(Expression.eq("id.username", username)).list(); // get all
																	// critical
																	// transactions
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}


	public List<Otp> findByExample(Otp instance) {
		log.debug("finding Otp instance by example");
		try {
			List<Otp> results = (List<Otp>) sessionFactory.getCurrentSession()
					.createCriteria("com.asu.models.Otp").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
	public List<Otp> getOtpByUsername(String username) {
		log.debug("finding Transactions instance by pending");
		try {

			List<Otp> results = (List<Otp>) sessionFactory
					.getCurrentSession().createCriteria(Otp.class)
					.add(Expression.eq("id.username", username)).list(); // get all
																	// critical
																	// transactions
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
