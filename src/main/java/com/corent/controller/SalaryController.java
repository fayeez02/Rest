package com.corent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.corent.entity.Employee;
import com.corent.entity.Salary;
import com.corent.service.SalaryService;

@RestController
@RequestMapping(value = "/salaryController/")
public class SalaryController {

	@Autowired
	SalaryService salaryService;

	@RequestMapping(value = "/createSalary", method = RequestMethod.POST)
	public ResponseEntity<Void> createSalary(@RequestBody Salary salary,
			UriComponentsBuilder ucBuilder) {
		salaryService.createSalary(salary);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/createSalary/{id}")
				.buildAndExpand(salary.getSalaryid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/listAllSalary", method = RequestMethod.GET)
	public ResponseEntity<List<Salary>> listAllSalary(Salary salary) {
		List<Salary> sal = salaryService.getAllSalary();
		if (sal.isEmpty()) {
			return new ResponseEntity<List<Salary>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Salary>>(sal, HttpStatus.OK);
	}

	@RequestMapping(value = "/listSalaryById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Salary> getSalary(@PathVariable("id") long id) {
		System.out.println("Fetching User with id " + id);
		Salary salary = salaryService.getSalaryById(id);
		if (salary == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Salary>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Salary>(salary, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateSalary/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Salary> updateSalary(@PathVariable("id") long id,
			@RequestBody Salary sal) {
		System.out.println("Updating User " + id);
		sal.setSalaryid(id);
		Salary salar=salaryService.getSalaryById(id);
		
		if (salar == null) {
			
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Salary>(HttpStatus.NOT_FOUND);
		}
		Salary salary = salaryService.updateSalary(sal);
		return new ResponseEntity<Salary>(salary, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteSalary/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Salary> deleteSalary(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting User with id " + id);
		Salary salar=salaryService.getSalaryById(id);
		
		if (salar == null) {
			
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Salary>(HttpStatus.NOT_FOUND);
		}
		salaryService.deleteSalary(id);
		return new ResponseEntity<Salary>(HttpStatus.NO_CONTENT);
	}

}
