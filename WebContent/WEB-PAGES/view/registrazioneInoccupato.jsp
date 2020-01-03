<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione Inoccupato</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../JS-SCRIPT/controlliRegistrazioneInoccupato.js"></script>
</head>
<body>
	<form enctype='multipart/form-data' method="POST">
	Nome: <input id="nome" onchange="checkNome()" type="text" name="nome"> <br>
	Cognome: <input id="cognome" onchange="checkCognome()" type="text" name="cognome"> <br>
	Città: <input id="citta" onchange="checkCitta()" type="text" name="cittàNascita"> <br>
	Residenza: <input id="residenza" onchange="checkResidenza()" type="text" name="residenza"> <br>
	Data di Nascita: <input id="dataNascita" onchange="checkDataNascita()" type="date" name="dataNascita"> <br>
	e-mail: <input id="email" onchange="checkEmail()" type="email" name="email"> <br>
	Username: <input id="username" onchange="checkUsername()" type="text" name="username"> <br>
	Password: <input id="pass" onchange="checkPass()" type="password" name="password"> <br>
	Conferma Password: <input id="confPass" onchange="checkPass()" type="password" name="confermaPassword"> <br>
	Curriculum: <input id="curriculum" onchange="checkCurriculum()" type="file" name="curriculum"> <br>
	<input id="dati" type="checkbox" name="trattamentoDati"> Accetto i termini del 
	trattamento dati personali. <br>
	<input onclick="checkAll()" type="submit" value="Conferma Registrazione">
	</form>
</body>
</html>