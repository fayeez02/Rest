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
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	SessionFactory sessionFactory;

	public void createEmployee(Employee emp, Salary salary) {

		Session session = sessionFactory.openSession();
		Salary sal = emp.getSalary();
		long sa = sal.getSalary();
		Criteria criteria = session.createCriteria(Salary.class);
		Criterion criterion = Restrictions.eq("salary", sa);
		Salary salar = (Salary) criteria.add(criterion).uniqueResult();
		if (salar != null) {
			emp.setSalary(salar);
		}
		session.saveOrUpdate(emp);
		session.close();
	}

	public List<Employee> getAllEmployee(Employee employee) {
		Session session = sessionFactory.openSession();

		List<Employee> li = null;
		try {
			li = session.createCriteria(Employee.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return li;
	}

	public Employee getSingleEmployee(int id) {
		Session session = sessionFactory.openSession();

		Employee emp = null;
		Criteria criteria = null;
		try {

			criteria = session.createCriteria(Employee.class);
			Criterion criterion = Restrictions.eq("empid", id);
			criteria.add(criterion);
			emp = (Employee) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return emp;
	}

	public Employee updateEmployee(Employee employee) {
		Session session = sessionFactory.openSession();

		Employee emp = null;
		Criteria criteria = null;
		try {
			criteria = session.createCriteria(Employee.class);
			Criterion criterion = Restrictions.eq("empid", employee.getEmpid());
			criteria.add(criterion);
			emp = (Employee) criteria.uniqueResult();
			if (emp != null) {
				emp.setAge(employee.getAge());
				emp.setDesignation(employee.getDesignation());
				emp.setName(employee.getName());
				emp.setEmpid(employee.getEmpid());
				Transaction tx = session.getTransaction();
				tx.begin();
				session.update(emp);
				tx.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return emp;
	}

	public void deleteEmployee(int id) {
		Session session = sessionFactory.openSession();

		Employee emp = null;
		Criteria criteria = null;
		try {

			criteria = session.createCriteria(Employee.class);
			Criterion criterion = Restrictions.eq("empid", id);
			criteria.add(criterion);
			emp = (Employee) criteria.uniqueResult();
			Transaction tx = session.getTransaction();
			tx.begin();
			session.delete(emp);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Employee findEmployeeById(int id) {
		Session session = sessionFactory.openSession();

		Employee emp = null;
		Criteria criteria = null;
		try {

			criteria = session.createCriteria(Employee.class);
			Criterion criterion = Restrictions.eq("empid", id);
			criteria.add(criterion);
			emp = (Employee) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return emp;
	}

}
