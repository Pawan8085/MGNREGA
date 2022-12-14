package com.mgnrega.dao;

import java.util.List;

import com.mgnrega.bean.Gpm;
import com.mgnrega.bean.Employee;
import com.mgnrega.bean.Project;
import com.mgnrega.exceptions.MgnregaException;

public interface MgnregaDao {
	public String LoginBdo(String email, String Password)throws MgnregaException;
	public String insertIntoPorject(Project p1)throws MgnregaException;
	public List<Project>  getAllProject() throws MgnregaException;
	
	
	public String insertIntoGpm(Gpm g1)throws MgnregaException;
	public List<Gpm> getAllGpm()throws MgnregaException;
	
	public String allocateGmp(int pid, int gid)throws MgnregaException;
	public String ProjectOfEmployee()throws MgnregaException;
	
	
	public Gpm loginEmployee(String email, String password)throws MgnregaException;
	public String insertIntoEmployee(Employee e1)throws MgnregaException;
	public List<Employee> getAllEmployee()throws MgnregaException;
	public String allocateProject(int pid, int gid, int eid)throws MgnregaException;
	
}
