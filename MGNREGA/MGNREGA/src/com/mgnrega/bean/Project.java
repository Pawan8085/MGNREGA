package com.mgnrega.bean;

public class Project {
	private int pid;
	private String projectName;
	
	public Project() {
		// TODO Auto-generated constructor stub
	}

	public Project(int pid, String projectName) {
		super();
		this.pid = pid;
		this.projectName = projectName;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "Project [pid=" + pid + ", projectName=" + projectName + "]";
	}
	
	
}
