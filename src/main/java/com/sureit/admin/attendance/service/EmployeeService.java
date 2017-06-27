package com.sureit.admin.attendance.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sureit.admin.attendance.model.Employee;
import com.sureit.admin.attendance.model.TimeSheetEntry;
import com.sureit.admin.attendance.repository.IEmployeeDAO;
import com.sureit.admin.attendance.repository.ITimeSheetEntryDAO;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	private IEmployeeDAO employeeDAO;

	@Autowired
	private ITimeSheetEntryDAO timeSheetEntryDAO;

	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = null;
		employeeList = (List<Employee>) employeeDAO.findAll();

		return employeeList;
	}

	public Employee getEmployeeBasedOnEmpId(int id) {
		Employee employee = null;
		employee = employeeDAO.findOne(id);

		return employee;
	}

	public Employee createEmployee(Employee emp) {
		Employee employee = null;
		Employee employeeTemp = employeeDAO.findByEmailAddress(emp.getEmailAddress());
		if (employeeTemp == null) {
			employee = employeeDAO.save(emp);
		}
		return employee;
	}

	public void updateEmployee(Employee emp) {
		employeeDAO.save(emp);

	}

	public Employee fetchEmployeeByEmailAddress(String emailAddress) {
		Employee employee = null;

		employee = employeeDAO.findByEmailAddress(emailAddress);

		return employee;
	}

	public void deleteEmployeeBasedOnEmpId(int id) {
		employeeDAO.delete(id);
	}

	public String createTimeSheetEntry(int id) throws Exception {
		String status = null;
		TimeSheetEntry empLogin = null;

		empLogin = timeSheetEntryDAO.findByEmpidAndLoginDateTime(id);
		if (empLogin == null) {
			empLogin = new TimeSheetEntry(id, new Date());
			timeSheetEntryDAO.save(empLogin);
			status = "Timesheet entry created successfully";
		}

		return status;
	}

}
