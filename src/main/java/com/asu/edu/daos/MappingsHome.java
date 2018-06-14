package com.asu.edu.daos;

// Generated Oct 21, 2014 9:01:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Mappings.
 * @see com.asu.models.Mappings
 * @author Hibernate Tools
 */
public class MappingsHome {

	private static final Log log = LogFactory.getLog(MappingsHome.class);

	private SessionFactory sessionFactory; 

	
	public void setSessionFactory(SessionFactory sessionFactory) {
	      this.sessionFactory = sessionFactory;
	   }
	/*protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}*/

	public void persist(Mappings transientInstance) {
		log.debug("persisting Mappings instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Mappings instance) {
		log.debug("attaching dirty Mappings instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Mappings instance) {
		log.debug("attaching clean Mappings instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Mappings persistentInstance) {
		log.debug("deleting Mappings instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Mappings merge(Mappings detachedInstance) {
		log.debug("merging Mappings instance");
		try {
			Mappings result = (Mappings) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public Mappings findById(java.lang.Integer id) {
		log.debug("getting Mappings instance with id: " + id);
		try {
			Mappings instance = (Mappings) sessionFactory.getCurrentSession()
					.get("com.asu.edu.daos.Mappings", id);
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


	public List<Mappings> findByExample(Mappings instance) {
		log.debug("finding Mappings instance by example");
		try {
			List<Mappings> results = (List<Mappings>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.asu.models.Mappings")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Mappings> getAll() {
		log.debug("finding Mappings instance by example");
		try {
			List<Mappings> results = (List<Mappings>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.asu.edu.daos.Mappings").list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Mappings> getAllowedUserFor(String username){
		log.debug("finding Transactions instance by example");
		try {
			List<Mappings> results = (List<Mappings>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.asu.edu.daos.Mappings")
					.add(Expression.eq("userByInternalUsername.username", username)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
 
}
