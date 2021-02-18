package com.cognizant.crudpoc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.crudpoc.model.EmployeeDetails;
import com.cognizant.crudpoc.repository.EmployeeRepository;

/**
 * service class to handle the crud operations
 * 
 * @author arunk
 *
 */
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * service method to add list of employees to the database
	 * 
	 * @param employeeDetailsList
	 * @return
	 */
	public ResponseEntity<Map<String, String>> createEmployee(List<EmployeeDetails> employeeDetailsList) {

		Map<String, String> empRespMap = new HashMap<>();

		employeeDetailsList.stream().filter(emp -> Optional.ofNullable(emp).isPresent()).forEach(emp -> {
			if (Optional.ofNullable(emp.getEmployeeId()).isPresent() && !emp.getEmployeeId().isEmpty()) {
				try {

					if (!employeeRepository.existsById(emp.getEmployeeId())) {
						employeeRepository.save(emp);
						empRespMap.put(emp.getEmployeeId(), "created");

					} else {
						empRespMap.put(emp.getEmployeeId(),
								"employee already exist, please use update call to perform update");
					}
				} catch (Exception e) {
					empRespMap.put(emp.getEmployeeId(), "an error occured during save");
				}
			} else {
				empRespMap.put(emp.getEmployeeName(), "emp id null for employee, not added to db");

			}

		});

		return new ResponseEntity<>(empRespMap, HttpStatus.OK);
	}

	/**
	 * service method to update employee details
	 * 
	 * @param employeeDetails
	 * @param employeeId
	 * @return
	 */
	public ResponseEntity<Map<String, String>> updateEmployee(EmployeeDetails employeeDetails, String employeeId) {

		Map<String, String> respMap = new HashMap<String, String>();

		if (Optional.ofNullable(employeeDetails).isPresent()) {
			try {
				if (employeeId != null && !employeeId.isEmpty()) {
					if (employeeRepository.existsById(employeeId)) {

						employeeDetails.setEmployeeId(employeeId);
						employeeRepository.save(employeeDetails);
					} else {

						respMap.put("status", "provided employeeId does not exist to update");
						return new ResponseEntity<>(respMap, HttpStatus.BAD_REQUEST);
					}
				} else {
					respMap.put("status", "provided employeeId cannot be empty");
					return new ResponseEntity<>(respMap, HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {

				respMap.put("status", "an error occured during update");
				return new ResponseEntity<>(respMap, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		}

		respMap.put("status", "update successful");
		return new ResponseEntity<>(respMap, HttpStatus.OK);
	}

	/**
	 * service functionality to delete a given employee from database
	 * 
	 * @param employeeId
	 * @return
	 */
	public ResponseEntity<Map<String, String>> deleteEmployee(String employeeId) {

		Map<String, String> respMap = new HashMap<String, String>();

		if (null != employeeId && !employeeId.isEmpty()) {
			if (employeeRepository.existsById(employeeId)) {

				try {
				employeeRepository.deleteById(employeeId);
				}
				catch(Exception e) {
					respMap.put("status", "an error occured during delete");
					return new ResponseEntity<>(respMap, HttpStatus.BAD_REQUEST);
				}
			}

			else {
				respMap.put("status", "provided employeeId does not exist to delete");
				return new ResponseEntity<>(respMap, HttpStatus.BAD_REQUEST);
			}
		} else {
			respMap.put("status", "provided employeeId cannot be empty");
			return new ResponseEntity<>(respMap, HttpStatus.BAD_REQUEST);
		}

		respMap.put("status", "deletion successful");
		return new ResponseEntity<>(respMap, HttpStatus.OK);
	}

	/**
	 * service method to get list of all the employees in database
	 * 
	 * @return
	 */
	public ResponseEntity<Object> getEmployees() {

		Map<String, String> respMap = new HashMap<String, String>();
		try {

			return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);

		} catch (Exception e) {

			respMap.put("status", "an error occured during retrieve operation, please try after some time");
			return new ResponseEntity<>(respMap, HttpStatus.OK);
		}
	}

}
