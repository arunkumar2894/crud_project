package com.cognizant.crudpoc.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.crudpoc.model.EmployeeDetails;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, String> {

}
