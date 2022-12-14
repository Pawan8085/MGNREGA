package com.mgnrega.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.mgnrega.bean.Gpm;
import com.mgnrega.bean.Employee;
import com.mgnrega.bean.Project;
import com.mgnrega.dao.MgnregaDao;
import com.mgnrega.dao.MgnregaDaoImpl;
import com.mgnrega.exceptions.MgnregaException;
import com.mgnrega.utility.DbUtil;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter y if you want to login as Block Development Officer");
		String bdo = sc.next();
		MgnregaDao dao = new MgnregaDaoImpl();
		if(bdo.equals("y")) {
			
			
			
			
			System.out.println("Enter User Email :-");
			String email = sc.next();
			
			System.out.println("Enter User Password :-");
			String password = sc.next();
			
			try {
				String login = dao.LoginBdo(email, password);
				if(login.equals("done")) {
					System.out.println("Enter Project Details");
					
					System.out.println("Enter Project Id :-");
					int pid=sc.nextInt();
					
					System.out.println("Enter Project Name :-");
					String projectName=sc.next();
					Project p1 = new Project();
					p1.setPid(pid);
					p1.setProjectName(projectName);
					
					try {
						String msg = dao.insertIntoPorject(p1);
						System.out.println(msg);
					} catch (MgnregaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("______________________________");
					try {
						List<Project> projects = dao.getAllProject();
						projects.forEach(p -> System.out.println(p));
					} catch (MgnregaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("______________________________");
					System.out.println("Enter Gram Panchayat Details");
					 System.out.println("Enter Gram Panchayat Id :-");
					 int gid = sc.nextInt();
					 System.out.println("Enter Gram Panchayat Name :-");
					 String gname=sc.next();
					 System.out.println("Enter Gram Panchayat Employee Name :-");
					 String ename = sc.next();
					 System.out.println("Enter Employee Email :-");
					 String eemail= sc.next();
					 System.out.println("Enter Employee Password :-");
					 String epassword = sc.next();
					 System.out.println("Enter Employee Wages :-");
					 int wages = sc.nextInt();
					 Gpm g1 = new Gpm();
					 g1.setGid(gid);
					 g1.setGname(gname);
					 g1.setEname(ename);
					 g1.setEemail(eemail);
					 g1.setEpassword(epassword);
					 g1.setEwages(wages);
					 
					String gmn = dao.insertIntoGpm(g1);
					System.out.println(gmn);
					
					System.out.println("______________________________________________");
					List<Gpm> gmps = dao.getAllGpm();
					gmps.forEach( g -> System.out.println(g));
					System.out.println("______________________________________________");
					
					System.out.println("Allocate Project To Gpm....");
					 System.out.println("Enter Project Id :-");
					 int _pid = sc.nextInt();
					 System.out.println("Enter Gpm Id :- ");
					 int _gid = sc.nextInt();
					 
					 String alocate = dao.allocateGmp(_pid, _gid);
					 System.out.println(alocate);
					System.out.println("_____________________________________");
					String p_of_e =  dao.ProjectOfEmployee();
					System.out.println(p_of_e);
					
				}
			} catch (MgnregaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		System.out.println("Enter y if you want login as a gram panchayat member");
		String gramPanchayet = sc.next();
		String prName=null;
		int prId=0;
		int gId=0;
		
		if(gramPanchayet.equals("y")) {
			try {
				System.out.println("Enter your email");
				String email=sc.next();
				System.out.println("Enter your password");
				String password = sc.next();
				Gpm gpm = dao.loginEmployee(email, password);
				System.out.println("Welcom Employee "+gpm.getEname());
				try(Connection con = DbUtil.provideConnection()) {
					PreparedStatement ps =	con.prepareStatement("select p.projectname, p.pid, g.gid from project p inner join gpm g inner join gpm_projects gp on p.pid=gp.pid and g.gid=gp.gid and g.epassword=?");
					ps.setString(1, password);
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						
						 prName= rs.getString("projectname");
						 prId = rs.getInt("pid");
						 gId= rs.getInt("gid");
//						System.out.println(prName+"  "+prId+" "+gId);
					}
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println(e);
				}
				boolean check = false;
				 System.out.println("How many employee you want to add :");
				 int n = sc.nextInt();
				 for(int i=0;i<n;++i) {
					 check = true;
					 Employee e1 = new Employee();
					 System.out.println("Enter Employee Id");
					 int _eid=sc.nextInt();
					 e1.setEid(_eid);
					 System.out.println("Enter Employee Name");
					 e1.setEmpName(sc.next());
					 System.out.println("Enter Employee Wages");
					 e1.setEmpWages(sc.nextInt());
					 System.out.println("Enter Employee WorkingDays");
					 e1.setWorkingDays(sc.nextInt());
					 String msg = dao.insertIntoEmployee(e1);
					 System.out.println(msg);
					 dao.allocateProject(prId, gId, _eid);
					 System.out.println("___________________________________");
				 }
				if(check) {
					
					System.out.println(prName +" Project allocated to employees");
					System.out.println("___________________________________________");
					
					
				}
				
				System.out.println("Employee Working days ana Wages");
				
				try(Connection con = DbUtil.provideConnection()) {
					PreparedStatement ps =	con.prepareStatement(" select p.projectname,e.empname,  e.empwages, e.workingdays from project p inner join gpm g inner join employee e inner join employee_gp_project egp on p.pid =egp.pid and g.gid = egp.gid and e.eid = egp.eid");
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						int temp = rs.getInt("empwages")*rs.getInt("workingdays");
						System.out.println("Employee "+rs.getString("empname")+" worked on Project "+rs.getString("projectname")+ " "+rs.getInt("workingdays")+" days Wages of employee is "+temp);
						System.out.println();
						System.out.println("___________________________________________");
					}
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println(e);
				}
			} catch (MgnregaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}

}
