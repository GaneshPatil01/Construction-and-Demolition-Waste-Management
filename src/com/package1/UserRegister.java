package com.package1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRegister
 */
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con  = DbConnection.connect();
		int srno = 0;
		String name = request.getParameter("name");
		long contact = Long.parseLong(request.getParameter("contactno"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String profession = request.getParameter("profession");
		String address = request.getParameter("address"); 
		
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
			pstmt.setInt(1,srno);
			pstmt.setString(2, name);
			pstmt.setLong(3, contact);
			pstmt.setString(4, email);
			pstmt.setString(5, password);
			pstmt.setString(6, profession);
			pstmt.setString(7, address);
			
			int i = pstmt.executeUpdate();
			if(i>0)
			{
				response.sendRedirect("index.html");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Fail To Register Try Again!").append(request.getContextPath());	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
