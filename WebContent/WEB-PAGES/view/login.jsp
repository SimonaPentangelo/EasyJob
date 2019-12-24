<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action ="../../LoginServlet" method="POST">
	Username: <input type="text" name="username"> <br>
	Password: <input type="password" name="password"> <br>
	<input type="submit" value="Accedi">
	</form>
</body>
</html>