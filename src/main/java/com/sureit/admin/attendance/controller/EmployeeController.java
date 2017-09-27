package com.sureit.admin.attendance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sureit.admin.attendance.controller.exception.AttendanceException;
import com.sureit.admin.attendance.model.Employee;
import com.sureit.admin.attendance.service.EmployeeService;

@RestController("/")
@EnableAutoConfiguration
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@CrossOrigin
	@RequestMapping("/getEmployeeList")
	public @ResponseBody ResponseEntity<List<Employee>> getAllEmployees() throws AttendanceException {
		List<Employee> listOfEmployees = null;

		listOfEmployees = employeeService.getAllEmployees();
		if (listOfEmployees == null) {
			throw new AttendanceException("No employee records found");
		}

		return new ResponseEntity<List<Employee>>(listOfEmployees, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping("/getEmployee/{id}")
	public @ResponseBody ResponseEntity<Employee> getEmployeeBasedOnEmpId(@PathVariable int id)
			throws AttendanceException {
		Employee employee = null;
		employee = employeeService.getEmployeeBasedOnEmpId(id);
		if (employee == null) {
			throw new AttendanceException("Employee record not found for given id - " + id);
		}

		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/createEmployee", consumes = "application/json")
	@Produces("application/json")
	public @ResponseBody ResponseEntity<Map<String, String>> createEmployee(
			@RequestBody(required = true) Employee employee) throws AttendanceException {
		Employee employeeTemp = null;
		String status = null;
		try {
			employeeTemp = employeeService.createEmployee(employee);

			status = "User created successfully";
		} catch (Exception e) {
			throw new AttendanceException("User creation failed");
		}
		if (employeeTemp == null) {
			throw new AttendanceException("User details already exists with email id- " + employee.getEmailAddress());
		}
		Map<String, String> creationOfEmployeeStatus = new HashMap<String, String>();
		creationOfEmployeeStatus.put("message", status);
		return new ResponseEntity<Map<String, String>>(creationOfEmployeeStatus, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteEmp/{id}")
	public @ResponseBody ResponseEntity<Map<String, String>> deleteEmployeeBasedOnEmpId(@PathVariable("id") int id)
			throws AttendanceException {
		String status = null;
		try {
			employeeService.deleteEmployeeBasedOnEmpId(id);
			status = "Employee record deleted successfully";
		} catch (Exception e) {
			throw new AttendanceException("Employee record deletion failed");
		}
		Map<String, String> deletionOfEmployeeStatus = new HashMap<String, String>();
		deletionOfEmployeeStatus.put("message", status);
		return new ResponseEntity<Map<String, String>>(deletionOfEmployeeStatus, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, value = "/updateEmployee")
	public @ResponseBody ResponseEntity<Map<String, String>> updateEmployee(
			@RequestBody(required = true) Employee employee) throws AttendanceException {
		String status = null;
		try {
			employeeService.updateEmployee(employee);
			status = "Employee record updated successfully";
		} catch (Exception e) {
			throw new AttendanceException("Employee record updation failed");
		}
		Map<String, String> updateEmployeeStatusMap = new HashMap<String, String>();
		updateEmployeeStatusMap.put("message", status);
		return new ResponseEntity<Map<String, String>>(updateEmployeeStatusMap, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/createTimeSheetEntry/{employeeId}")
	@Produces("application/json")
	public ResponseEntity<Map<String, String>> createTimeSheetEntry(@PathVariable Integer employeeId)
			throws AttendanceException {
		String status = null;
		try {
			status = employeeService.createTimeSheetEntry(employeeId);
		} catch (Exception e) {
			throw new AttendanceException("Timesheet entry failed for employee id: " + employeeId);
		}
		if (status == null) {
			throw new AttendanceException("Timesheet entry already exists");
		}

		Map<String, String> map = new HashMap<>();
		map.put("message", status);

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.CREATED);
	}

	@CrossOrigin
	@RequestMapping("/validateEmployee/")
	@Consumes("application/json")
	public @ResponseBody ResponseEntity<Employee> validateEmployee(@QueryParam("emailAddress") String emailAddress)
			throws AttendanceException {
		Employee employee = null;
		try {
			employee = employeeService.fetchEmployeeByEmailAddress(emailAddress);
		} catch (Exception e) {
			throw new AttendanceException("Sytems is not available. Please try after some time");
		}
		if (employee == null) {
			throw new AttendanceException("Not a valid user");
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

}
