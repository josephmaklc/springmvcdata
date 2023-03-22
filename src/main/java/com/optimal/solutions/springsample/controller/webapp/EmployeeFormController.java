package com.optimal.solutions.springsample.controller.webapp;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.optimal.solutions.springsample.entities.Employee;
import com.optimal.solutions.springsample.services.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This is the controller for the employee form, to be used by both edit and new employees
 *
 */
@Controller
public class EmployeeFormController {

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Called by "Add new employee" link, which just shows the blank employee form
	 * The real action takes place when the submit button on that form is clicked
	 * @return
	 */
	@GetMapping("/addEmployee")
	public ModelAndView blankEmployeePage() {
		ModelAndView result = new ModelAndView();
		result.addObject("title","Enter New Employee");
		result.addObject("employee",new Employee());
		result.addObject("theaction","/addEmployee");
		result.setViewName("employeeform");
		return result;
	}
	
	/**
	 * Handles the Submit button of the Enter New Employee form.
	 * Redirect back to main page when done
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@PostMapping("/addEmployee")
	public void addNewEmployeePage( HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee e = new Employee();
		e.setFirstName(request.getParameter("firstName"));
		e.setLastName(request.getParameter("lastName"));
		e.setEmail(request.getParameter("email"));
		employeeService.createEmployee(e);
		response.sendRedirect("/");
	}

	/**
	 * Called by "Update" employee link, which loads the employee data identified 
	 * by the id and pass to the "employee" variable for the view
	 * @param request
	 * @return
	 */
	@GetMapping("/editEmployee")
	public ModelAndView editEmployeePage(HttpServletRequest request) {
		Long id=Long.parseLong(request.getParameter("id"));
		ModelAndView result = new ModelAndView();
		result.addObject("title","Edit Employee");
		result.addObject("employee",employeeService.getEmployeeById(id));
		result.addObject("theaction","/editEmployee");
		result.setViewName("employeeform");
		return result;
	}
	
	/**
	 * Handles the submit button of Edit Employee form
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@PostMapping("/editEmployee")
	public void saveEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id=Long.parseLong(request.getParameter("id"));
		Employee e = employeeService.getEmployeeById(id);
		e.setFirstName(request.getParameter("firstName"));
		e.setLastName(request.getParameter("lastName"));
		e.setEmail(request.getParameter("email"));
		employeeService.updateEmployee(e);
		response.sendRedirect("/");
	}
	
	/**
	 * Handles the delete button of the employee list, which deletes the employee identified with id
	 * and then redirect back to main page
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/deleteEmployee")
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id=Long.parseLong(request.getParameter("id"));
		employeeService.deleteEmployee(id);
		response.sendRedirect("/");
	}
}
