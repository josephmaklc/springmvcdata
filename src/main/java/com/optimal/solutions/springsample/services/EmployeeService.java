package com.optimal.solutions.springsample.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.optimal.solutions.springsample.entities.Employee;
import com.optimal.solutions.springsample.repository.EmployeeRepository;

/**
 * This is the service layer for interacting with the employee repository
 * Both the REST API and the web application uses this (instead of interacting with the repository directly)
 *
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    
    public Employee findEmployeeByExample(String lastName, String firstName) {
    	Employee e = new Employee();
    	e.setFirstName(firstName);
    	e.setLastName(lastName);
    	Example<Employee> example = Example.of(e);
        Optional<Employee> result = employeeRepository.findOne(example);
        if (result.isPresent()) return result.get();
        return null;
    }
    
    public Employee createEmployee(Employee Employee) {
        return employeeRepository.save(Employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		}
		else {
			return null;
		}
    }
    
    public List<Employee> getSomeEmployees(Integer pageNo, Integer pageSize)
    {
    	if (pageNo==null && pageSize==null) {
    		pageNo=0;
    		pageSize=Integer.MAX_VALUE;
    	}
    	Sort sort = Sort.by("lastName").ascending().and(Sort.by("firstName"));
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);

        Page<Employee> pagedResult = employeeRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Employee>();
        }
    }

}
