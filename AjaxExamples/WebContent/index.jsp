<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.sql.*" %>
<%
String comment =request.getParameter("comment");
String email =request.getParameter("email");
if( comment==null || email ==null || comment.trim().equals("")|| email.trim().equals("")){
	out.print("<p>Please write comment</p>");	
}else{
	try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ajaxdb","root","");
		PreparedStatement ps=con.prepareStatement("insert into comments(comment,email) values(?, ?)");
		ps.setString(1, comment);
		ps.setString(2, email);
		int i= ps.executeUpdate();
		PreparedStatement ps2=con.prepareStatement("SELECT * FROM comments ORDER BY id DESC");
		ResultSet rs=ps2.executeQuery();
		out.print("<hr/><h2>Comments:</h2>");
		while(rs.next()){
			out.print("<div class='box'>");
			out.print("<p>" + rs.getString("comment") + "</p>");
			out.print("<p><strong>By: " + rs.getString("email") +
			"</strong></p>");
			out.print("</div>");
		}
		rs.close();
		ps.close();
		ps2.close();
		con.close();
	} catch(Exception e){
		out.print("Error: " + e.getMessage());
	}
}
%>
</body>
</html>