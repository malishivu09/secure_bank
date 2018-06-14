package com.asu.edu.daos;

// Generated Oct 31, 2014 3:28:40 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.edu.utils.Constants;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Transactions.
 * 
 * @see com.asu.edu.daos.Transactions
 * @author Hibernate Tools
 */
public class TransactionsHome {

	private static final Log log = LogFactory.getLog(TransactionsHome.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void persist(Transactions transientInstance) {
		log.debug("persisting Transactions instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Transactions instance) {
		log.debug("attaching dirty Transactions instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void saveTransaction(Transactions instance) {
		log.debug("attaching dirty Transactions instance");
		try {
			sessionFactory.getCurrentSession().save(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(Transactions instance) {
		log.debug("attaching clean Transactions instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Transactions persistentInstance) {
		log.debug("deleting Transactions instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Transactions merge(Transactions detachedInstance) {
		log.debug("merging Transactions instance");
		try {
			Transactions result = (Transactions) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Transactions findById(java.lang.String id) {
		log.debug("getting Transactions instance with id: " + id);
		try {
			Transactions instance = (Transactions) sessionFactory
					.getCurrentSession().get("com.asu.edu.daos.Transactions",
							id);
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

	public List<Transactions> findByExample(Transactions instance) {
		log.debug("finding Transactions instance by example");
		try {
			List<Transactions> results = (List<Transactions>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.asu.edu.daos.Transactions")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Transactions> findByTransactionID(String transactionId) {
		log.debug("finding Transactions instance by example");
		try {
			List<Transactions> results = (List<Transactions>) sessionFactory
					.getCurrentSession()
					.createCriteria("com.asu.edu.daos.Transactions")
					.add(Expression.eq("transactionId", transactionId)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public boolean addTransaction(Transactions tr) {
		log.debug("getting Account instance with id: "
				+ tr.getAccountByAccountSource().getAccountNumber());
		try {

			String hql = "insert into transactions values (?, ?, ?, ?, ?, ?, ?, ?)";

			Query query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(hql)
					.setParameter(0,
							tr.getAccountByAccountSource().getAccountNumber())
					.setParameter(1,
							tr.getAccountByAccountTarget().getAccountNumber())
					.setParameter(2, tr.getTransactionAmount())
					.setParameter(3, tr.getTransactionId())
					.setParameter(4, tr.getTransactionType())
					.setParameter(5, tr.getTransactionStatus())
					.setParameter(6, tr.isIsCritical())
					.setParameter(7, tr.getTransactionTime());

			int insert = query.executeUpdate();

			if (insert == 1) {
				return true;
			} else {
				return false;
			}

		} catch (RuntimeException re) {
			log.error("get failed", re);
			System.out.println(re.getLocalizedMessage());
			throw re;
		}
	}

	// get all critical transactions
	public List<Transactions> findAllPending() {
		log.debug("finding Transactions instance by pending");
		try {

			List<Transactions> results = (List<Transactions>) sessionFactory
					.getCurrentSession().createCriteria(Transactions.class)
					.add(Expression.eq("isCritical", true)).list(); // get all
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
	
	
	@SuppressWarnings("deprecation")
	public List<Transactions> findAllMerchantPayment(String fromAcc) {
		log.debug("finding Transactions instance by pending");
		try {

			@SuppressWarnings("unchecked")
			List<Transactions> results = (List<Transactions>) sessionFactory
					.getCurrentSession().createCriteria(Transactions.class)
					.add(Expression.like("transactionStatus", Constants.PAYMENT_REQUEST_USER_PENDING))
					.add(Expression.eq("accountByAccountSource.accountNumber", fromAcc)).list(); // get all
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
	
	
	public List<Transactions> getAllTransactionRelatedToUser(String accountNumber) {
		log.debug("finding Transactions instance by pending");
		try {

			List<Transactions> results = (List<Transactions>) sessionFactory
					.getCurrentSession().createCriteria(Transactions.class)
					.add(Expression.or(Expression.eq("accountByAccountSource.accountNumber", accountNumber), Expression.eq("accountByAccountTarget.accountNumber", accountNumber))).list(); // get all
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
