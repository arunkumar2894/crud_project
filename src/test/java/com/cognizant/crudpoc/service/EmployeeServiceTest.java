package com.cognizant.crudpoc.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.crudpoc.model.EmployeeDetails;
import com.cognizant.crudpoc.repository.EmployeeRepository;
import com.cognizant.crudpoc.testutils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService employeeService;

	@Mock
	EmployeeRepository employeeRepository;

	@Before
	public void setup() {

		MockitoAnnotations.openMocks(this);

	}

	@Test
	public void createEmployeeTestWithEmpId() {

		Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(new EmployeeDetails());
		List<EmployeeDetails> employeeDetails = TestUtils.getEmloyeeListForTest();

		Assert.assertEquals("created",
				employeeService.createEmployee(employeeDetails).getBody().get(employeeDetails.get(0).getEmployeeId()));

	}

	@Test
	public void createEmployeeTestWithOutEmpId() {

		Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(new EmployeeDetails());

		List<EmployeeDetails> employeeDetails = TestUtils.getEmloyeeListForTest();
		employeeDetails.get(0).setEmployeeId(null);

		Assert.assertEquals("emp id null for employee, not added to db", employeeService.createEmployee(employeeDetails)
				.getBody().get(employeeDetails.get(0).getEmployeeName()));

	}

	@Test
	public void createEmployeeTestWithExistingEmp() {

		Mockito.when(employeeRepository.existsById(Mockito.any())).thenReturn(true);

		List<EmployeeDetails> employeeDetails = TestUtils.getEmloyeeListForTest();

		Assert.assertEquals("employee already exist, please use update call to perform update",
				employeeService.createEmployee(employeeDetails).getBody().get(employeeDetails.get(0).getEmployeeId()));

	}

	@Test
	public void createEmployeeTestWithException() {

		Mockito.when(employeeRepository.save(Mockito.any())).thenThrow(new RuntimeException());
		List<EmployeeDetails> employeeDetails = TestUtils.getEmloyeeListForTest();

		Assert.assertEquals("an error occured during save",
				employeeService.createEmployee(employeeDetails).getBody().get(employeeDetails.get(0).getEmployeeId()));

	}

	@Test
	public void updateEmployeeTestWithEmpId() {

		Mockito.when(employeeRepository.existsById(Mockito.any())).thenReturn(true);

		Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(new EmployeeDetails());
		List<EmployeeDetails> employeeDetails = TestUtils.getEmloyeeListForTest();

		Assert.assertEquals("update successful",
				employeeService.updateEmployee(employeeDetails.get(0), "123").getBody().get("status"));

	}

	@Test
	public void updateEmployeeTestWithOutEmpId() {

		Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(new EmployeeDetails());
		List<EmployeeDetails> employeeDetails = TestUtils.getEmloyeeListForTest();

		Assert.assertEquals("provided employeeId cannot be empty",
				employeeService.updateEmployee(employeeDetails.get(0), null).getBody().get("status"));

	}

	@Test
	public void updateEmployeeTestEmpIdDoesNotExist() {

		Mockito.when(employeeRepository.existsById(Mockito.any())).thenReturn(false);
		List<EmployeeDetails> employeeDetails = TestUtils.getEmloyeeListForTest();

		Assert.assertEquals("provided employeeId does not exist to update",
				employeeService.updateEmployee(employeeDetails.get(0), "123").getBody().get("status"));

	}

	@Test
	public void updateEmployeeTestWithException() {

		Mockito.when(employeeRepository.existsById(Mockito.any())).thenReturn(true);

		Mockito.when(employeeRepository.save(Mockito.any())).thenThrow(new RuntimeException());

		List<EmployeeDetails> employeeDetails = TestUtils.getEmloyeeListForTest();

		Assert.assertEquals("an error occured during update",
				employeeService.updateEmployee(employeeDetails.get(0), "123").getBody().get("status"));

	}

	@Test
	public void deleteEmployeeWithId() {

		Mockito.when(employeeRepository.existsById(Mockito.any())).thenReturn(true);

		Mockito.doNothing().when(employeeRepository).deleteById(Mockito.any());

		Assert.assertEquals("deletion successful", employeeService.deleteEmployee("123").getBody().get("status"));

	}

	@Test
	public void deleteEmployeeWithOutId() {

		Mockito.doNothing().when(employeeRepository).deleteById(Mockito.any());

		Assert.assertEquals("provided employeeId cannot be empty",
				employeeService.deleteEmployee(null).getBody().get("status"));

	}

	@Test
	public void deleteEmployeeWithIdNotPresent() {

		Mockito.when(employeeRepository.existsById(Mockito.any())).thenReturn(false);

		Mockito.doNothing().when(employeeRepository).deleteById(Mockito.any());

		Assert.assertEquals("provided employeeId does not exist to delete",
				employeeService.deleteEmployee("123").getBody().get("status"));

	}

	@Test
	public void deleteEmployeeWithException() {

		Mockito.when(employeeRepository.existsById(Mockito.any())).thenReturn(true);

		Mockito.doThrow(new RuntimeException()).when(employeeRepository).deleteById(Mockito.any());

		Assert.assertEquals("an error occured during delete",
				employeeService.deleteEmployee("123").getBody().get("status"));

	}

	@Test
	public void getEmployeeListWithoutException() {

		Mockito.when(employeeRepository.findAll()).thenReturn(new ArrayList<>());

		Assert.assertNotNull(employeeService.getEmployees().getBody());

	}

}
