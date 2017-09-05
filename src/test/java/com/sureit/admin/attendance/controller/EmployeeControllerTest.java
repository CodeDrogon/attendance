package com.sureit.admin.attendance.controller;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sureit.admin.attendance.baseclass.ControllerTestBaseClass;
import com.sureit.admin.attendance.model.Employee;
import com.sureit.admin.attendance.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest extends ControllerTestBaseClass {

	@Autowired
	private EmployeeService employeeService;

	static Map<String, Object> employeeDetailsMap = new HashMap<String, Object>();

	@Test
	public void helloWorld() {
		given().when().get("/hello").then().statusCode(200);
	}

	@Test
	public void getEmployeeBasedOnInvalidEmpIdTest() {
		// given invalid employee id
		given().when().get("/getEmployee/1000").body().asString().equals(null);
	}

	@Test
	public void getEmployeeBasedOnEmpIdTest1() {
		// given valid employee id test
		given().when().get("/getEmployee/700").then().statusCode(200);
	}

	@Test
	public void getAllEmployeesTest() {
		given().when().get("/getEmployeeList").then().statusCode(200);
	}

	@Test
	public void deleteEmployeeBasedOnEmpIdTest() {
		Employee employee = employeeService
				.fetchEmployeeByEmailAddress((String) employeeDetailsMap.get("emailAddress"));
		if (employee == null) {
			employeeDetailsMap.put("firstName", "TestFirstName");
			employeeDetailsMap.put("lastName", "TestLastName");
			employeeDetailsMap.put("mobileNumber", 12345);
			employeeDetailsMap.put("emailAddress", "TestFirstName.TestLastName@test.com");
			given().contentType("application/json").body(employeeDetailsMap).when().post("/createEmployee").then()
					.statusCode(200);
			employee = employeeService.fetchEmployeeByEmailAddress((String) employeeDetailsMap.get("emailAddress"));
		}
		String url = "/deleteEmp/" + employee.getEmpId();
		given().when().post(url).then().statusCode(200);

	}

	@Test
	public void createEmployeeTest() {
		employeeDetailsMap.put("firstName", "TestFirstName");
		employeeDetailsMap.put("lastName", "TestLastName");
		employeeDetailsMap.put("mobileNumber", 12345);
		employeeDetailsMap.put("emailAddress", "TestFirstName.TestLastName@test.com");
		given().contentType("application/json").body(employeeDetailsMap).when().post("/createEmployee").then()
				.statusCode(200);
		deleteEmployeeBasedOnEmpIdTest();
	}

}
