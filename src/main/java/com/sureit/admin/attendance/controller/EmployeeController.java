package com.sureit.admin.attendance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sureit.admin.attendance.model.Employee;
import com.sureit.admin.attendance.service.EmployeeService;

@RestController("/")
@EnableAutoConfiguration
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/hello")
	public String getHelloMessage() {
		return "Hellow World";
	}

	@CrossOrigin
	@RequestMapping("/getEmployeeList")
	public @ResponseBody Map<String, Object> getAllEmployees() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Employee> listOfEmployees = null;
		listOfEmployees = employeeService.getAllEmployees();
		map.put("result", listOfEmployees);
		map.put("totalrecords", listOfEmployees.size());
		return map;
	}

	@CrossOrigin
	@RequestMapping("/getEmployee/{id}")
	public @ResponseBody Employee getEmployeeBasedOnEmpId(@PathVariable int id) {
		Employee employee = null;
		employee = employeeService.getEmployeeBasedOnEmpId(id);

		return employee;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/createEmployee", consumes = "application/json")
	public @ResponseBody Map<String, String> createEmployee(@RequestBody(required = true) Employee employee) {
		String status = null;
		Map<String, String> creationOfEmployeeStatus = new HashMap<String, String>();
		if (employeeService.createEmployee(employee)) {
			status = "Employee record created successfully";
		} else {
			status = "Employee record creation falied";
		}
		creationOfEmployeeStatus.put("message", status);
		return creationOfEmployeeStatus;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/deleteEmp/{id}")
	public @ResponseBody String deleteEmployeeBasedOnEmpId(@PathVariable("id") int id) {
		String status = null;
		if (employeeService.deleteEmployeeBasedOnEmpId(id)) {
			status = "Employee record deleted successfully";
		} else {
			status = "Employee record deletion falied";
		}
		return status;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/createTimeSheetEntry/{employeeId}")
	@Produces("application/json")
	public @ResponseBody Map<String, String> createTimeSheetEntry(@PathVariable("employeeId") Integer employeeId) {
		String status = null;
		status = employeeService.createTimeSheetEntry(employeeId);
		Map<String, String> map = new HashMap<>();
		map.put("message", status);

		return map;
	}

}
