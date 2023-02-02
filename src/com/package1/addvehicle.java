package com.package1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addvehicle
 */
public class addvehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addvehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int vid = 0;
		String vecno = request.getParameter("vechilenumber");
		String drivername = request.getParameter("drivername");
		long drivercontact =  Long.parseLong(request.getParameter("drivercontact"));
		float latitude = 0;
		float longitute = 0;
		
		
		Connection con = DbConnection.connect();
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into vehicle values(?,?,?,?,?,?)");
			pstmt.setInt(1, vid);
			pstmt.setString(2, vecno);
			pstmt.setString(3, drivername);
			pstmt.setLong(4, drivercontact);
			pstmt.setFloat(5,latitude);
			pstmt.setFloat(6,longitute);
			
			int i = pstmt.executeUpdate();
			
			if(i>0)
			{
				response.sendRedirect("Dashboard.html");
			}
			else
			{
				response.getWriter().append("fail to add vechile").append(request.getContextPath());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
