package com.sureit.admin.attendance.repository;

import org.springframework.data.repository.CrudRepository;

import com.sureit.admin.attendance.model.Employee;


public interface IEmployeeDAO extends CrudRepository<Employee, Integer> {

	public Employee findByEmailAddress(String EmailAddress);
}
