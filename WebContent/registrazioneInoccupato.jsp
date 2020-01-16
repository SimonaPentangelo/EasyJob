<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Registrazione Inoccupato</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="./JS-SCRIPT/controlliRegistrazioneInoccupato.js"></script>
</head>
<body>
<%@include file ="header.jsp"%>
	<form enctype='multipart/form-data' action="${pageContext.request.contextPath}/RegistrazioneInoccupatoServlet" method="POST">
	Nome: <input id="nome" onchange="checkNome()" type="text" name="nome">
	<span id="errorNome"></span><br>
	
	Cognome: <input id="cognome" onchange="checkCognome()" type="text" name="cognome">
	<span id="errorCognome"></span><br>
	
	Città: <input id="citta" onchange="checkCitta()" type="text" name="cittaNascita">
	<span id="errorCitta"></span><br>
	
	Residenza: <input id="residenza" onchange="checkResidenza()" type="text" name="residenza">
	<span id="errorResidenza"></span><br>
	
	Data di Nascita: <input id="dataNascita" onchange="checkDataNascita()" type="date" name="dataNascita">
	<span id="errorData"></span><br>
	
	e-mail: <input id="email" onchange="checkEmail()" type="email" name="email">
	<span id="errorMail"></span><br>
	
	Username: <input id="username" onchange="checkUsername()" type="text" name="username"> 
	<span id="errorUser"></span><br>
	
	Password: <input id="pass" onchange="checkPass()" type="password" name="password">
	<span id="errorPass"></span><br>
	
	Conferma Password: <input id="confPass" onchange="checkPass()" type="password" name="confermaPassword">
	<span id="errorConfPass"></span><br>
	
	Curriculum: <input id="curriculum" onchange="checkCurriculum()" type="file" name="curriculum">
	<span id="errorCurriculum"></span><br>
	
	<input id="dati" type="checkbox" name="trattamentoDati"> Accetto i termini del 
	trattamento dati personali. 
	<span id="errorCheck"></span><br>
	
	<input id="conferma" type="submit" value="Conferma Registrazione">
	</form>
<%@include file ="footer.jsp"%>
</body>
</html>