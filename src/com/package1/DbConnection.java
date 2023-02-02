package com.package1;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	static Connection connect()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cnd","root","");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return(con);
	}

}
