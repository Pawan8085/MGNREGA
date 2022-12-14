package com.mgnrega.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mgnrega.bean.Gpm;
import com.mgnrega.bean.Employee;
import com.mgnrega.bean.Project;
import com.mgnrega.exceptions.MgnregaException;
import com.mgnrega.utility.DbUtil;

public class MgnregaDaoImpl implements MgnregaDao{
	@Override
	public String LoginBdo(String email, String Password) throws MgnregaException {
		String message = null;
		
		try(Connection con = DbUtil.provideConnection()) {
			PreparedStatement ps =	con.prepareStatement("select * from bdoaccount where email = ? and password =?");
			ps.setString(1, email);
			ps.setString(2, Password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				message="done";
			}
			else {
				throw new MgnregaException("Invalid Email or Password");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new MgnregaException(e.getMessage());
		}
		
		
		return message;
	}
	
	
	@Override
	public String insertIntoPorject(Project p1) throws MgnregaException {
		// TODO Auto-generated method stub
		String message = "Not Inserted";
		
		try (Connection con = DbUtil.provideConnection()) {
		PreparedStatement ps =	con.prepareStatement("insert into project values(?,?)");
		
		ps.setInt(1, p1.getPid());
		ps.setString(2, p1.getProjectName());
		
		int p = ps.executeUpdate();
		if(p>0) message = "Data Inserted Sucessfully";
			
		} catch (SQLException e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		
		return message;
	}
	
	@Override
	public List<Project> getAllProject() throws MgnregaException {
		List<Project> projects = new ArrayList<>();
		
		try (Connection con = DbUtil.provideConnection()){
			PreparedStatement ps =	con.prepareStatement("select * from project");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Project project= new Project(rs.getInt("pid"), rs.getString("projectName"));
				projects.add(project);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new MgnregaException(e.getMessage());
		}
		
		
		
		return projects;
	}
	
	
	@Override
	public String insertIntoGpm(Gpm g1) throws MgnregaException {
		// TODO Auto-generated method stub
				String message = "Not Inserted";
				
				try (Connection con = DbUtil.provideConnection()) {
				PreparedStatement ps =	con.prepareStatement("insert into gpm values(?,?,?,?,?,?)");
				ps.setInt(1, g1.getGid());
				ps.setString(2, g1.getGname());
				ps.setString(3,g1.getEname());
				ps.setString(4, g1.getEemail());
				ps.setString(5, g1.getEpassword());
				ps.setInt(6, g1.getEwages());
				
				int p = ps.executeUpdate();
				if(p>0) message = "Data Inserted Sucessfully";
					
				} catch (SQLException e) {
					// TODO: handle exception
					message=e.getMessage();
				}
				
				return message;
	}
	
	@Override
	public List<Gpm> getAllGpm() throws MgnregaException {
		List<Gpm> gpms = new ArrayList<>();
		
		try (Connection con = DbUtil.provideConnection()){
			PreparedStatement ps =	con.prepareStatement("select * from gpm");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int gid=rs.getInt("gid");
				String gname=rs.getString("gname");
				String ename=rs.getString("ename");
				String eemail=rs.getString("eemial");
				String epassword=rs.getString("epassword");
				int ewages=rs.getInt("ewages");
				
				Gpm gpm = new Gpm(gid,gname,ename,eemail,epassword,ewages);
				gpms.add(gpm);
				
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new MgnregaException(e.getMessage());
		}
		
		
		if(gpms.size()==0)  System.out.println("No Gpm found");
		return gpms;
	}
	
	@Override
	public String allocateGmp(int pid, int gid) throws MgnregaException {
		String message = "Not Allocated";
		
		try (Connection con = DbUtil.provideConnection()) {
		PreparedStatement ps =	con.prepareStatement("insert into gpm_projects values(?,?)");
		ps.setInt(1, pid);
		
		ps.setInt(2, gid);
		
		int p = ps.executeUpdate();
		if(p>0) message = "Project Allocated Sucessfully";
			
		} catch (SQLException e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		
		return message;
	}
	
	
	@Override
	public String ProjectOfEmployee() throws MgnregaException {
		String message = "Empoyee Not Found";
		
		try(Connection con = DbUtil.provideConnection()) {
			PreparedStatement ps =	con.prepareStatement(" select g.gname, g.ename, g.ewages, p.projectname from gpm g inner join project p inner join gpm_projects gp on g.gid=gp.gid and p.pid=gp.pid;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				message = "done..";
				System.out.println("Gramp Panchayat Name "+rs.getString("gname"));
				System.out.println("Employee Name "+rs.getString("ename"));
				System.out.println("Empoyee Wages "+rs.getInt("ewages"));
				System.out.println("Project Name "+rs.getString("projectname"));
				 System.out.println("__________________________________________");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw  new MgnregaException(e.getMessage());
		}
		
		return message;
	}
	
	
	@Override
	public Gpm loginEmployee(String email, String password) throws MgnregaException {
		Gpm gpm = null;
		
		try(Connection con = DbUtil.provideConnection()) {
		PreparedStatement ps =	con.prepareStatement("select * from gpm where eemial=? and epassword=?");
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			int gid = rs.getInt("gid");
			String gname = rs.getNString("gname");
			String ename = rs.getNString("ename");
			String eemail = rs.getNString("eemial");
			String epassword = rs.getString("epassword");
			int ewages = rs.getInt("ewages");
			
			gpm = new Gpm(gid,gname,ename,eemail,epassword,ewages);
		}
		
		else {
			throw new MgnregaException("Invalid Username or Password");
		}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new MgnregaException(e.getMessage());
		}
		
		return gpm;
	}
	
	@Override
	public String insertIntoEmployee(Employee e1) throws MgnregaException {
		String message = "Not Inserted";
		
		try (Connection con = DbUtil.provideConnection()) {
		PreparedStatement ps =	con.prepareStatement("insert into employee values(?,?,?,?)");
		ps.setInt(1, e1.getEid());
		ps.setString(2, e1.getEmpName());
		ps.setInt(3, e1.getEmpWages());
		ps.setInt(4, e1.getWorkingDays());
		
		int p = ps.executeUpdate();
		if(p>0) message = "Data Inserted Sucessfully";
			
		} catch (SQLException e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		
		return message;
	}
	
	@Override
	public List<Employee> getAllEmployee() throws MgnregaException {
		List<Employee> employees = new ArrayList<>();
		
		try (Connection con = DbUtil.provideConnection()){
			PreparedStatement ps =	con.prepareStatement("select * from employee");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Employee employee= new Employee(rs.getInt("eid"), rs.getString("empName"),rs.getInt("empwages"),rs.getInt("workingdays"));
				employees.add(employee);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new MgnregaException(e.getMessage());
		}
		
		
		
		return employees;
	}
	
	
	@Override
	public String allocateProject(int pid, int gid, int eid) throws MgnregaException {
     String message = "Not Allocated";
		
		try (Connection con = DbUtil.provideConnection()) {
		PreparedStatement ps =	con.prepareStatement("insert into employee_gp_project values(?,?,?)");
		ps.setInt(1, pid);
		ps.setInt(2, gid);
		ps.setInt(3, eid);
		
		int p = ps.executeUpdate();
		if(p>0) message = "Project Allocated Sucessfully";
			
		} catch (SQLException e) {
			// TODO: handle exception
			message=e.getMessage();
		}
		
		return message;
	}
}
