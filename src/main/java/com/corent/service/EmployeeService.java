package com.corent.service;

import java.util.List;

import com.corent.entity.Employee;
import com.corent.entity.Salary;

public interface EmployeeService {

	public void createEmployee(Employee emp, Salary salary);

	public List<Employee> getAllEmployee(Employee employee);

	public Employee getSingleEmployee(int id);

	public Employee updateEmployee(Employee employee);

	public void deleteEmployee(int id);

	public Employee findEmployeeById(int id);

}
