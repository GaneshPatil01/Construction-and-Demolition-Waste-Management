package com.package1;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Userraisecndreq
 */

@WebServlet(name="Userraisecndreq",urlPatterns=("/Userraisecndreq"))
@MultipartConfig(maxFileSize=16177216)//1.5mb
 public class Userraisecndreq extends HttpServlet {
	
	PrintWriter out;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uid=0;
		String email = GetterandSetter.getMail();
		String address=request.getParameter("address");
		String title=request.getParameter("title");
		String discription=request.getParameter("description");
		response.setContentType("text/html;charset=UTF-8");
		out=response.getWriter();
		int result=0;
		Part part=request.getPart("image");
		String status ="disapprove";
		if(part!=null){
			try{
				Connection con=DbConnection.connect();
				PreparedStatement ps= con.prepareStatement("insert into cnd values(?,?,?,?,?,?,?)");
				InputStream is=part.getInputStream();
				ps.setInt(1,uid);
				ps.setString(2, email);
				ps.setString(3, address);
				ps.setString(4, title);
				ps.setString(5, discription);
				ps.setBlob(6, is);
				ps.setString(7,status);
				result=ps.executeUpdate();
				if(result>0){
					response.sendRedirect("Userraisecndreq.jsp");
				}
				else{
					response.getWriter().append("sorry");
				}
			}catch(Exception e){
				out.println(e);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
