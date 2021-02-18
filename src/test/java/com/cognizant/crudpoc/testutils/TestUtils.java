package com.cognizant.crudpoc.testutils;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.crudpoc.model.EmployeeDetails;

public interface TestUtils {

	public static List<EmployeeDetails> getEmloyeeListForTest() {

		List<EmployeeDetails> employeeDetails = new ArrayList<>();

		EmployeeDetails employeeDetail = new EmployeeDetails();

		employeeDetail.setEmployeeId("123");
		employeeDetail.setEmployeeName("arun");
		employeeDetail.setOrganization("cognizant");
		employeeDetail.setSalary("10000");

		employeeDetails.add(employeeDetail);

		return employeeDetails;

	}

}
