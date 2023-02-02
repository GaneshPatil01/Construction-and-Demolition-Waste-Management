package com.package1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uemail = request.getParameter("email");
		GetterandSetter.setMail(uemail);
		String upass = request.getParameter("password");
		
		Connection con = DbConnection.connect();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("select * from user where email = ? and password = ?");
			pstmt.setString(1, uemail);
			pstmt.setString(2, upass);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				response.sendRedirect("UserDashboard.html");
			}
			else
			{
				response.getWriter().append("Fail TO Login").append(request.getContextPath());
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
