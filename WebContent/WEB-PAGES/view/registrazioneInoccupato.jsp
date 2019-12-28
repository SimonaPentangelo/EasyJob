<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione Inoccupato</title>
</head>
<body>
	<form enctype='multipart/form-data' action="${pageContext.request.contextPath}/RegistrazioneInoccupatoServlet" method="POST">
	Nome: <input type="text" name="nome"> <br>
	Cognome: <input type="text" name="cognome"> <br>
	Città di Nascita: <input type="text" name="cittàNascita"> <br>
	Residenza: <input type="text" name="residenza"> <br>
	Data di Nascita: <input type="date" name="dataNascita"> <br>
	e-mail: <input type="email" name="email"> <br>
	Username: <input type="text" name="username"> <br>
	Password: <input type="password" name="password"> <br>
	Conferma Password: <input type="password" name="confermaPassword"> <br>
	Curriculum: <input type="file" name="curriculum"> <br>
	<input type="checkbox" name="trattamentoDati"> Accetto i termini del 
	trattamento dati personali. <br>
	<input type="submit" value="Conferma Registrazione">
	</form>
</body>
</html>