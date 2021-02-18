package com.cognizant.crudpoc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetails {

	@ApiModelProperty(value = "employee Id of the employee", name = "employeeId", dataType = "String", example = "123")
	@Id
	private String employeeId;

	@ApiModelProperty(value = "name of the employee", name = "employeeName", dataType = "String", example = "arun")
	@Column(name = "employeeName")
	private String employeeName;

	@ApiModelProperty(value = "organization of the employee", name = "organization", dataType = "String", example = "cognizant")
	@Column(name = "organization")
	private String organization;

	@ApiModelProperty(value = "salary of the employee", name = "salary", dataType = "String", example = "10000")
	@Column(name = "salary")
	private String salary;

	public EmployeeDetails() {

	}

	public EmployeeDetails(String employeeId, String employeeName, String organization, String salary) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.organization = organization;
		this.salary = salary;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

}
