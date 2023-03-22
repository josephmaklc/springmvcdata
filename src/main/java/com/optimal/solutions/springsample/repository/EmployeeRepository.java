package com.optimal.solutions.springsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optimal.solutions.springsample.entities.Employee;

/**
 * This is the employee repository, an interface that extends JpaRepository, which includes many
 * standard methods to interact with the database without a single line of code needed to create, query, and update.
 * 
 * The EmployeeService class would interact with this repository
 *
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
