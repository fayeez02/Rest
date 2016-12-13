package com.corent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corent.dao.SalaryDao;
import com.corent.entity.Salary;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {
	@Autowired
	SalaryDao salaryDao;

	public void createSalary(Salary salary) {
		salaryDao.createSalary(salary);
	}

	public List<Salary> getAllSalary() {
		return salaryDao.getAllSalary();
	}

	public Salary getSalaryById(long id) {
		return salaryDao.getSalaryById(id);
	}

	public Salary updateSalary(Salary salary) {
		return salaryDao.updateSalary(salary);
	}

	public void deleteSalary(long id) {
		salaryDao.deleteSalary(id);
	}
}
