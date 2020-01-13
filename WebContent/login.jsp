<% String msg = (String) session.getAttribute("error"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action ="${pageContext.request.contextPath}/LoginServlet" method="POST">
	Username: <input type="text" name="username"> <br>
	Password: <input type="password" name="password"> <br>
	<% if(msg!=null){
		%>
	<p> <%=msg %></p>
	<%} %>
	<input type="submit" value="Accedi">
	</form>
</body>
</html>