package com.sureit.admin.attendance.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
		try {
			employeeList = (List<Employee>) employeeDAO.findAll();
		} catch (Exception e) {

		}
		return employeeList;
	}

	public Employee getEmployeeBasedOnEmpId(int id) {
		Employee employee = null;
		try {
			employee = employeeDAO.findOne(id);
		} catch (DataAccessException e) {

		}
		return employee;
	}

	public boolean createEmployee(Employee emp) {
		boolean status = false;
		try {
			Employee employeeTemp = employeeDAO.findByEmailAddress(emp.getEmailAddress());
			if (employeeTemp == null) {
				employeeDAO.save(emp);
				status = true;
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
		return status;
	}

	public Employee fetchEmployeeByEmailAddress(String emailAddress) {
		Employee employee = null;
		try {
			employee = employeeDAO.findByEmailAddress(emailAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	public boolean deleteEmployeeBasedOnEmpId(int id) {
		boolean status = false;
		try {
			employeeDAO.delete(id);
			status = true;
		} catch (DataAccessException e) {
			// TODO: handle exception

		}
		return status;
	}

	public String createTimeSheetEntry(int id) {
		String status = null;
		TimeSheetEntry empLogin = null;
		try {
			Employee emp = getEmployeeBasedOnEmpId(id);
			if (emp != null) {
				empLogin = timeSheetEntryDAO.findByEmpidAndLoginDateTime(id);
				if (empLogin == null) {
					empLogin = new TimeSheetEntry(id, new Date());
					timeSheetEntryDAO.save(empLogin);
					status = "Timesheet entry created successfully";
				} else {
					status = "Timesheet entry already exists for Employee id- " + id;
				}
			} else {
				status = "Employee id- " + id + " does not exist. Please enter valid id";
			}
		} catch (Exception e) {

		}
		return status;
	}
}
