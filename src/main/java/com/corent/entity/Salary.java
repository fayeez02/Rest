package com.corent.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salary")
public class Salary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long salaryid;
	@Column(unique = true)
	private long salary;

	public Salary() {
	}

	public Salary(long salary) {
		this.salary = salary;
	}

	public long getSalaryid() {
		return salaryid;
	}

	public void setSalaryid(long salaryid) {
		this.salaryid = salaryid;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

}
