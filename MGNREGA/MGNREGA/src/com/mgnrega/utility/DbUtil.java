package com.mgnrega.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	public static Connection provideConnection() {
		
		Connection con=null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url="jdbc:mysql://localhost:3306/pr1mgnrega";
		
		try {
			con= DriverManager.getConnection(url,"root","eq0144au");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return con;
	}
}
