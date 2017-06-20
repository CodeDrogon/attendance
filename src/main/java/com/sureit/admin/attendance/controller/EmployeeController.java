package com.sureit.admin.attendance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
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
	public @ResponseBody String message() {
		return "simple message";
	}

	@RequestMapping("/empList")
	public @ResponseBody List<Employee> getAllEmployees() {
		// TODO call business layer service method
		List<Employee> listOfEmployees = null;
		listOfEmployees = employeeService.getAllEmployees();
		return listOfEmployees;
	}

	@CrossOrigin
	@RequestMapping("/getEmployee/{id}")
	public @ResponseBody String getEmployeeBasedOnEmpId(@PathVariable int id) {
		Employee employee = null;
		employee = employeeService.getEmployeeBasedOnEmpId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empId", employee.getEmpId());
		map.put("empName", employee.getEmpName());
		map.put("empEmailAddress", employee.getEmailAddress());
		JSONObject outputJsonObj = new JSONObject(map);
		try {
			outputJsonObj.put("employee", map);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outputJsonObj.toString();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/createEmp")
	public @ResponseBody String createEmployee(@RequestBody Employee emp){
		String status = null;
		if(employeeService.createEmployee(emp)){
			status = "Employee record created successfully";
		}else {
			status = "Employee record creation falied";
		}
		return status;
	}

	@RequestMapping(method=RequestMethod.POST, value="/deleteEmp/{id}")
	public @ResponseBody String deleteEmployeeBasedOnEmpId(@PathVariable("id") int id) {
		String status = null;
		if(employeeService.deleteEmployeeBasedOnEmpId(id)){
			status = "Employee record deleted successfully";
		}else {
			status = "Employee record deletion falied";
		}
		return status;
	}

	@RequestMapping(method=RequestMethod.POST, value="/empLogin/{id}")
	public @ResponseBody String employeeLogin(@PathVariable("id") int id){
		String status = null;
		if(employeeService.employeeLogin(id)){
			status = "Employee Login successful";
		}else{
			status = "Employee already logged in today/is not a valid employee id.";
		}
		
		return status;
	}
	
	/*@RequestMapping("/employeesReport")
	public @ResponseBody List<EmployeeReport> generateEmployeesLoginReport() {

		return null;
	}

	@RequestMapping("/employeeReport/{id}")
	public @ResponseBody EmployeeReport generateEmployeeLoginReport(@PathVariable("id") Long id) {

		return null;
	}*/

}
