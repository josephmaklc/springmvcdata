package com.optimal.solutions.springsample.controller.rest;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optimal.solutions.springsample.entities.Employee;
import com.optimal.solutions.springsample.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * This is the API REST controller for insert/update/deleting an employee.
 * It works with the service layer (and not directly with the employee repository) 
 * 
 * It integrates with Open API Swagger annotation so comments can be seen with 
 * http://localhost:8080/swagger-ui/index.html
 * 
 */

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@Operation(summary = "Get a employee by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the employee", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Book.class)) }),
	  @ApiResponse(responseCode = "404", description = "Employee not found", 
	    content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee!=null) {
			return ResponseEntity.ok(employee);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

    @GetMapping(path = "/listByPage")
    public ResponseEntity<List<Employee>> getSomeEmployee(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
    	List<Employee> employees = employeeService.getSomeEmployees(pageNo,pageSize);
		return ResponseEntity.ok(employees);
    }
    
    @PostMapping(path = "/add")
    public ResponseEntity<Employee> addOneEmployee(Employee e) {
    	Employee employee = new Employee();
    	employee.setLastName(e.getLastName());
    	employee.setFirstName(e.getFirstName());
    	employee.setEmail(e.getEmail());
    	employee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Employee> updateEmployee(Employee e) {
		Employee employee = employeeService.getEmployeeById(e.id);
		if (employee!=null) {
			employee.setFirstName(e.getFirstName());
			employee.setLastName(e.getLastName());
			employee.setEmail(e.getEmail());
	    	employeeService.updateEmployee(employee);
			return ResponseEntity.ok(employee);
		}
		else {
			return ResponseEntity.notFound().build();
		}
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> deleteEmployee(Employee e) {
		Employee employee = employeeService.getEmployeeById(e.id);
		if (employee!=null) {
			employeeService.deleteEmployee(e.id);
			return ResponseEntity.ok("Employee Deleted");
		}
		else {
			return ResponseEntity.notFound().build();
		}
    }

}
