package com.sureit.admin.attendance;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sureit.admin.attendance.AttendanceApplication;
import com.sureit.admin.attendance.controller.EmployeeController;
import com.sureit.admin.attendance.model.Employee;
import com.sureit.admin.attendance.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AttendanceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Before
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	public void getAllEmployeesTest() throws Exception {
		List<Employee> employees = Arrays.asList(
				new Employee(1, "EmpFirstName1@EmpLastName1.com", "EmpFirstName1", "EmpLastName1", "111111",
						new Date()),
				new Employee(2, "EmpFirstName2@EmpLastName2.com", "EmpFirstName2", "EmpLastName2", "111111",
						new Date()));

		when(employeeService.getAllEmployees()).thenReturn(employees);

		mockMvc.perform(get("/getEmployeeList").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].empId", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("EmpFirstName1"))).andExpect(jsonPath("$[1].empId", is(2)))
				.andExpect(jsonPath("$[1].firstName", is("EmpFirstName2")));

		verify(employeeService, times(1)).getAllEmployees();
		verifyNoMoreInteractions(employeeService);

	}

	@Test
	public void getEmployeeBasedOnEmpIdTest() throws Exception {
		Employee employee = new Employee(1, "EmpFirstName1@EmpLastName1.com", "EmpFirstName1", "EmpLastName1", "111111",
				new Date());

		when(employeeService.getEmployeeBasedOnEmpId(1)).thenReturn(employee);

		mockMvc.perform(get("/getEmployee/{id}", 1).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.empId", is(1))).andExpect(jsonPath("$.firstName", is("EmpFirstName1")));

		verify(employeeService, times(1)).getEmployeeBasedOnEmpId(1);
		verifyNoMoreInteractions(employeeService);

	}

	@Test
	public void createEmployeeTest() throws Exception {
		Employee employee = new Employee(3, "EmpFirstName3@EmpLastName3.com", "EmpFirstName3", "EmpLastName3", "111111",
				new Date());
		// we can write below statement as
		// createEmployee(Mockito.any(Employee.class)) if equals and
		// hashcode is not overridden in corresponding pojo.
		when(employeeService.createEmployee(employee)).thenReturn(employee);

		mockMvc.perform(post("/createEmployee").contentType(MediaType.APPLICATION_JSON).content(asJsonString(employee))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		verify(employeeService, times(1)).createEmployee(employee);
		verifyNoMoreInteractions(employeeService);

	}

	@Test
	public void createTimeSheetEntryTest() throws Exception {
		Employee employee = new Employee(4, "EmpFirstName4@EmpLastName4.com", "EmpFirstName4", "EmpLastName4", "111111",
				new Date());

		when(employeeService.createTimeSheetEntry(employee.getEmpId()))
				.thenReturn("Timesheet entry created successfully");

		mockMvc.perform(post("/createTimeSheetEntry/{employeeId}", employee.getEmpId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.message", is("Timesheet entry created successfully")));

		verify(employeeService, times(1)).createTimeSheetEntry(employee.getEmpId());
		verifyNoMoreInteractions(employeeService);
	}

	@Test
	public void validateEmployeeTest() throws Exception {
		Employee employee = new Employee(5, "EmpFirstName5@EmpLastName5.com", "EmpFirstName5", "EmpLastName5", "111111",
				new Date());

		when(employeeService.fetchEmployeeByEmailAddress(employee.getEmailAddress())).thenReturn(employee);

		mockMvc.perform(
				get("/validateEmployee/?emailAddress=" + employee.getEmailAddress()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.empId", is(5)))
				.andExpect(jsonPath("$.firstName", is("EmpFirstName5")));

		verify(employeeService, times(1)).fetchEmployeeByEmailAddress(employee.getEmailAddress());
		verifyNoMoreInteractions(employeeService);

	}

	public static String asJsonString(final Object obj) {
		String returnValue = null;
		try {
			returnValue = new ObjectMapper().writeValueAsString(obj);
			return returnValue;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
