<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%
try{
	String image = "com.mysql.Blob@1bb69cfb";
int id= Integer.parseInt(request.getParameter("id"));
PreparedStatement ps;
Connection con;
out.print("<h1>Displaying Image saved in DB</h1>");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cnd","root","");
ps = con.prepareStatement("select image from cnd where uid = ?");
ps.setString(1,image);//XYZ is image_name stored in DB
ResultSet resultSet=ps.executeQuery();
if(resultSet.next()) {
byte[] content = resultSet.getBytes("image");//PHOTO is column name at which you stored the image
response.setContentType(getServletContext().getMimeType("image"));
response.setContentLength(content.length);
response.getOutputStream().write(content);
}
con.close();

}catch (Exception e) {out.print(e);e.printStackTrace();}
%>