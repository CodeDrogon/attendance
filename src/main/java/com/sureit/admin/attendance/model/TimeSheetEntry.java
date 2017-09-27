package com.sureit.admin.attendance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "timesheet_entry")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TimeSheetEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "timesheetid")
	private int timesheetId;
	@Column(name = "empid")
	private int empid;
	@Column(name = "login_Date_Time")
	private Date loginDateTime;

	public TimeSheetEntry() {

	}

	public TimeSheetEntry(int empId, Date loginDateTime) {
		setEmpid(empId);
		setLoginDateTime(loginDateTime);
	}

	public int getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(int timesheetId) {
		this.timesheetId = timesheetId;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public Date getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(Date loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

}
