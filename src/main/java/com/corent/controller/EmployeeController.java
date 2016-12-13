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
import com.corent.service.EmployeeService;

@RestController
@RequestMapping(value = "/employeeController/")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/Employee", method = RequestMethod.POST)
	public ResponseEntity<Void> createEmployee(@RequestBody Employee emp,
			Salary salary, UriComponentsBuilder ucBuilder) {

		employeeService.createEmployee(emp, salary);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/createemployee/{id}")
				.buildAndExpand(emp.getEmpid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/Employee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> listAllEmployee(Employee employee) {
		List<Employee> emp = employeeService.getAllEmployee(employee);
		if (emp.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(emp, HttpStatus.OK);
	}

	@RequestMapping(value = "/Employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		System.out.println("Fetching User with id " + id);
		Employee emp = employeeService.getSingleEmployee(id);
		if (emp == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}

	@RequestMapping(value = "/Employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id,
			@RequestBody Employee employee) {
		System.out.println("Updating User " + id);
		employee.setEmpid(id);
		Employee emp = employeeService.findEmployeeById(id);

		if (emp == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		Employee empl = employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(empl, HttpStatus.OK);
	}

	@RequestMapping(value = "/Employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting User with id " + id);
		Employee emp = employeeService.findEmployeeById(id);

		if (emp == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployee(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}
