package com.corent.dao;

import java.util.List;

import com.corent.entity.Salary;

public interface SalaryDao {
	public void createSalary(Salary salary);

	public List<Salary> getAllSalary();

	public Salary getSalaryById(long id);

	public Salary updateSalary(Salary salary);

	public void deleteSalary(long id);

}
