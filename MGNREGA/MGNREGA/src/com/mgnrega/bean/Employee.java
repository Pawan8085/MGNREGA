package com.mgnrega.bean;

public class Employee {
	private int eid;
	private String empName;
	private int empWages;
	private int workingDays;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int eid, String empName, int empWages, int workingDays) {
		super();
		this.eid = eid;
		this.empName = empName;
		this.empWages = empWages;
		this.workingDays = workingDays;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpWages() {
		return empWages;
	}

	public void setEmpWages(int empWages) {
		this.empWages = empWages;
	}

	public int getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", empName=" + empName + ", empWages=" + empWages + ", workingDays="
				+ workingDays + "]";
	}
	
	
}
