package com.package1;

import java.io.IOException;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updatevechile
 */
public class updatevechile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatevechile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String vecno = request.getParameter("vehiclenumber");
		String drivername = request.getParameter("drivername");
		long drivercontact =  Long.parseLong(request.getParameter("drivercontact"));

		
		Connection con = DbConnection.connect();
		try {
			PreparedStatement pstmt = con.prepareStatement("update vehicle set drivername = ? , drivercontact = ? where vehicleno = ? ");
			pstmt.setString(1, drivername);
			pstmt.setLong(2, drivercontact);
			pstmt.setString(3, vecno);
			int i = pstmt.executeUpdate();
			
			if(i>0)
			{
				response.sendRedirect("updatevehicle.html");
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
