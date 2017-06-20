package com.sureit.admin.attendance.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.sureit.admin.attendance.model.EmployeeLogin;


public interface IEmployeeLoginDAO extends CrudRepository<EmployeeLogin, Integer>{

	public EmployeeLogin findByEmpidAndLoginDateTime(int empid, Date LoginDateTime);
}
