package com.sureit.admin.attendance.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sureit.admin.attendance.model.Employee;
import com.sureit.admin.attendance.model.EmployeeLogin;
import com.sureit.admin.attendance.repository.IEmployeeDAO;
import com.sureit.admin.attendance.repository.IEmployeeLoginDAO;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	private IEmployeeDAO employeeDAO;
	
	@Autowired
	private IEmployeeLoginDAO employeeLoginDAO;

	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = null;
		try {
			employeeList = (List<Employee>) employeeDAO.findAll();
		} catch (DataAccessException e) {

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
		} catch (DataAccessException e) {
			// TODO: handle exception

		}
		return status;
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
	
	public boolean employeeLogin(int id){
		boolean status = false;
		try{
			Employee emp = getEmployeeBasedOnEmpId(id);
			if(emp!=null){
				EmployeeLogin empLogin = employeeLoginDAO.findByEmpidAndLoginDateTime(id, new Date());
				if(empLogin == null){
					empLogin = new EmployeeLogin(id, new Date(), new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime()));
					employeeLoginDAO.save(empLogin);
					status = true;
				}
			}
		}catch(DataAccessException e){
			
		}
		return status;
	}
}
