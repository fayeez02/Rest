package com.corent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corent.dao.EmployeeDao;
import com.corent.entity.Employee;
import com.corent.entity.Salary;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDao;

	public void createEmployee(Employee emp, Salary salary) {
		employeeDao.createEmployee(emp, salary);
	}

	public List<Employee> getAllEmployee(Employee employee) {
		return employeeDao.getAllEmployee(employee);
	}

	public Employee getSingleEmployee(int id) {
		return employeeDao.getSingleEmployee(id);
	}

	public Employee updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	public void deleteEmployee(int id) {
		employeeDao.deleteEmployee(id);
	}

	public Employee findEmployeeById(int id) {
		return employeeDao.findEmployeeById(id);
	}
}
