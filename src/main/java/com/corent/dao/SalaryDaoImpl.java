package com.corent.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.corent.entity.Employee;
import com.corent.entity.Salary;

@Repository
public class SalaryDaoImpl implements SalaryDao {

	@Autowired
	SessionFactory sessionFactory;

	public void createSalary(Salary salary) {

		Session session = sessionFactory.openSession();
		session.saveOrUpdate(salary);
		session.close();
	}

	public List<Salary> getAllSalary() {
		Session session = sessionFactory.openSession();

		List<Salary> li = null;
		try {
			li = session.createCriteria(Salary.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return li;
	}

	public Salary getSalaryById(long id) {
		Session session = sessionFactory.openSession();

		Salary salary = null;
		Criteria criteria = null;
		try {

			criteria = session.createCriteria(Salary.class);
			Criterion criterion = Restrictions.eq("salaryid", id);
			criteria.add(criterion);
			salary = (Salary) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return salary;
	}

	public Salary updateSalary(Salary salary) {
		Session session = sessionFactory.openSession();

		Salary sal = null;
		Criteria criteria = null;
		try {
			criteria = session.createCriteria(Salary.class);
			Criterion criterion = Restrictions.eq("salaryid",
					salary.getSalaryid());
			criteria.add(criterion);
			sal = (Salary) criteria.uniqueResult();
			if (sal != null) {
				sal.setSalaryid(salary.getSalaryid());
				sal.setSalary(salary.getSalary());
				Transaction tx = session.getTransaction();
				tx.begin();
				session.update(sal);
				tx.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sal;
	}

	public void deleteSalary(long id) {
		Session session = sessionFactory.openSession();

		Salary salary = null;
		Criteria criteria = null;
		try {

			criteria = session.createCriteria(Salary.class);
			Criterion criterion = Restrictions.eq("salaryid", id);
			criteria.add(criterion);
			salary = (Salary) criteria.uniqueResult();
			Transaction tx = session.getTransaction();
			tx.begin();
			session.delete(salary);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
