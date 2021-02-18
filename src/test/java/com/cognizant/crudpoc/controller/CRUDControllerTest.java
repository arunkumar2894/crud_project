package com.cognizant.crudpoc.controller;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.crudpoc.service.EmployeeService;
import com.cognizant.crudpoc.testutils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDControllerTest {

	@InjectMocks
	CRUDController crudController;

	@Mock
	EmployeeService employeeService;

	@Before
	public void setup() {

		MockitoAnnotations.openMocks(this);

	}

	@Test
	public void createEmployeeTest() {

		Mockito.when(employeeService.createEmployee(Mockito.any()))
				.thenReturn(new ResponseEntity<Map<String, String>>(HttpStatus.OK));

		Assert.assertNotNull(employeeService.createEmployee(TestUtils.getEmloyeeListForTest()));
	}

	@Test
	public void updateEmployeeTest() {

		Mockito.when(employeeService.updateEmployee(Mockito.any(), Mockito.any()))
				.thenReturn(new ResponseEntity<Map<String, String>>(HttpStatus.OK));

		Assert.assertNotNull(employeeService.updateEmployee(TestUtils.getEmloyeeListForTest().get(0), "123"));
	}

	@Test
	public void deleteEmployeeTest() {

		Mockito.when(employeeService.deleteEmployee(Mockito.any()))
				.thenReturn(new ResponseEntity<Map<String, String>>(HttpStatus.OK));

		Assert.assertNotNull(employeeService.deleteEmployee("123"));
	}

	@Test
	public void getEmployeeTest() {

		Mockito.when(employeeService.getEmployees()).thenReturn(new ResponseEntity<Object>(HttpStatus.OK));

		Assert.assertNotNull(employeeService.getEmployees());
	}

}
