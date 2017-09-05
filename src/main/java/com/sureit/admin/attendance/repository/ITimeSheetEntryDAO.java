package com.sureit.admin.attendance.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sureit.admin.attendance.model.TimeSheetEntry;

public interface ITimeSheetEntryDAO extends CrudRepository<TimeSheetEntry, Integer> {

	@Query("SELECT p FROM TimeSheetEntry p WHERE p.empid = ?1 and login_Date_Time=current_date")
	public TimeSheetEntry findByEmpidAndLoginDateTime(int empid);
}
