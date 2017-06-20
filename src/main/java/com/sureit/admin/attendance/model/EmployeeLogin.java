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
@Table(name = "employee_login")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EmployeeLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "login_id")
	private int loginid;
	@Column(name = "empid")
	private int empid;
	@Column(name = "login_Date_Time")
	private Date loginDateTime;
	@Column(name = "month")
	private String month;

	public EmployeeLogin(){
		
	}
	
	public EmployeeLogin(int empid, Date loginDateTime, String month) {
		super();
		this.empid = empid;
		this.loginDateTime = loginDateTime;
		this.month = month;
	}

	/**
	 * @return the loginid
	 */
	public int getLoginid() {
		return loginid;
	}

	/**
	 * @param loginid
	 *            the loginid to set
	 */
	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	/**
	 * @return the empid
	 */
	public int getEmpid() {
		return empid;
	}

	/**
	 * @param empid
	 *            the empid to set
	 */
	public void setEmpid(int empid) {
		this.empid = empid;
	}

	/**
	 * @return the loginDateTime
	 */
	public Date getLoginDateTime() {
		return loginDateTime;
	}

	/**
	 * @param loginDateTime
	 *            the loginDateTime to set
	 */
	public void setLoginDateTime(Date loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
