package com.optimal.solutions.springsample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	// here I can add freebie methods without implementation, no need to build an SQL statement,
	// loop through resultsets and return your list of object
	
	// freebie method: findBy<Field>, and findBy<Field> followed by more stuff, 
    List<Employee> findByLastNameContainingIgnoreCase(String name);
    
	// freebie method: to get a count, I just need countBy<Field>
    // select count(*) from Employee where lastName='<lastName>'
	long countByLastName(String lastName);
	
	// or I can use the @Query to do some custom queries
	@Query("SELECT e FROM Employee e WHERE e.lastName LIKE %:searchString% or e.firstName LIKE %:searchString% ORDER by lastName,firstName")
	List<Employee> searchByLastNameOrFirstName(@Param("searchString") String searchString);
}