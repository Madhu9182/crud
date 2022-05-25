package org.capgemini.repository;

import org.capgemini.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	//subclass : EmployeeRepository
	//Parent class : JpaRepository
	
}
