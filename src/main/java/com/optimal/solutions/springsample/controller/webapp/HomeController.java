package com.optimal.solutions.springsample.controller.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.optimal.solutions.springsample.services.EmployeeService;

/**
 * This is the Spring Web Home Page (/) controller (with the thymeleaf engine).
 * viewName refers to html file in the resources/templates folder
 * @author mak_j
 *
 */
@Controller
public class HomeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * The entrance page of the web application, get all the employees and pass to the index thymeleaf page
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView result = new ModelAndView();
		result.setViewName("index");
		result.addObject("employees",employeeService.getAllEmployees());
		return result;
	}
	

}
