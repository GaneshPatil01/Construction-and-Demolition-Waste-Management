<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>

<%
String status = "approve";
int srno  =  Integer.parseInt(request.getParameter("id"));
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
%>

<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
PreparedStatement pstmt = connection.prepareStatement("update cnd set status = ? where uid = ? ");
pstmt.setString(1, status);
pstmt.setInt(2, srno);
int i = pstmt.executeUpdate();
if(i>0)
{
	response.sendRedirect("cndrequest.jsp");
}
%>
<%
} catch (Exception e) {
e.printStackTrace();
}
%>