<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contatta Candidato</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/ContattaCandidatoServlet" method="POST">
	Titolo: <input type="text" name="titolo"> <br>
	Messaggio: <br>
	 <textarea name="Messaggio" rows=10 cols=20> </textarea> 
	<input type="submit" value="Invia">
	</form>
</body>
</html>