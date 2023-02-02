<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.sql.Blob" %>
<%@page import="java.io.OutputStream" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>    
<%
int id= Integer.parseInt(request.getParameter("id"));
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "cnd";
String userId = "root";
String password = "";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
try{
	connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
PreparedStatement ps =connection.prepareStatement("select image from cnd where uid=?");
ps.setInt(1,id);
ResultSet rs=ps.executeQuery();
if(rs.next()){
	Blob blob=rs.getBlob("image");
	byte byteArray[]=blob.getBytes(1,(int) blob.length());
	response.setContentType("image/gif");
	OutputStream os=response.getOutputStream();
	os.write(byteArray);
	os.flush();
	os.close();
}else{
	System.out.println("not found");
}
}catch(Exception e){
	out.println(e);
}
%>