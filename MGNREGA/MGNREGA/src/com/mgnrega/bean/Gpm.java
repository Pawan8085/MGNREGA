package com.mgnrega.bean;

public class Gpm {
	private int  gid;
	private String gname;
	private String ename;
	private String eemail;
	private String epassword;
	private int ewages;
	
	public Gpm() {
		// TODO Auto-generated constructor stub
	}

	public Gpm(int gid, String gname, String ename, String eemail, String epassword, int ewages) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.ename = ename;
		this.eemail = eemail;
		this.epassword = epassword;
		this.ewages = ewages;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEemail() {
		return eemail;
	}

	public void setEemail(String eemail) {
		this.eemail = eemail;
	}

	public String getEpassword() {
		return epassword;
	}

	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}

	public int getEwages() {
		return ewages;
	}

	public void setEwages(int ewages) {
		this.ewages = ewages;
	}

	@Override
	public String toString() {
		return "Gpm [gid=" + gid + ", gname=" + gname + ", ename=" + ename + ", eemail=" + eemail + ", epassword="
				+ epassword + ", ewages=" + ewages + "]";
	}
	
}