package com.cognizant.crudpoc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.crudpoc.model.EmployeeDetails;
import com.cognizant.crudpoc.service.EmployeeService;

@RestController
public class CRUDController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee/addAll")
	public ResponseEntity<Map<String, String>> createEmployee(@RequestBody List<EmployeeDetails> employeeDetails) {

		return employeeService.createEmployee(employeeDetails);

	}

	@PutMapping("/employee/update/{employeeId}")
	public ResponseEntity<Map<String, String>> updateEmployee(@RequestBody EmployeeDetails employeeDetails,
			@PathVariable String employeeId) {

		return employeeService.updateEmployee(employeeDetails, employeeId);

	}

	@DeleteMapping("/employee/delete/{employeeId}")
	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String employeeId) {

		return employeeService.deleteEmployee(employeeId);

	}

	@GetMapping("/employee/getAll")
	public ResponseEntity<Object> getEmployees() {

		return employeeService.getEmployees();

	}

	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
