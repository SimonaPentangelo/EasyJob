<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Registrazione Azienda</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="./JS-SCRIPT/controlliRegistrazioneAzienda.js"></script>
</head>
<body>
<%@include file ="header.jsp"%>
	<form enctype='multipart/form-data' action="${pageContext.request.contextPath}/RegistrazioneAziendaServlet" method="POST">
	Nome Azienda: <input id="nomeAzienda" onchange="checkNomeAzienda()" type="text" name="nomeAzienda"> 
	<span id="errorNome"></span><br>
	
	Logo: <input id="logo" onchange="checkLogo()" type="file" accept="image/*" name="logoAzienda"> 
	<span id="errorLogo"></span><br>
	
	Partita IVA: <input id="piva" onchange="checkPIva()" type="text" name="partitaIVA"> 
	<span id="errorIVA"></span><br>
	
	Username: <input id="username" onchange="checkUsername()" type="text" name="username"> 
	<span id="errorUser"></span><br>
	
	Indirizzo Sede: <input id="indirizzo" onchange="checkIndirizzo()" type="text" name="indirizzoSede"> 
	<span id="errorIndirizzo"></span><br>
	
	Data Fondazione: <input id="dataFond" onchange="checkDataFondazione()" type="date" name="dataFondazione"> 
	<span id="errorData"></span><br>
	
	Breve Descrizione: <input id="desc" onchange="checkDescrizione()" type="text" name="descrizione"> 
	<span id="errorDesc"></span><br>
	
	Numero Dipendenti: <input id="dip" onchange="checkDipendenti()" type="text" name="numeroDipendenti"> 
	<span id="errorDip"></span><br>
	
	e-mail: <input id="email" onchange="checkEmail()" type=email" name="email"> 
	<span id="errorMail"></span><br>
	
	Password: <input id="pass" onchange="checkPass()" type="password" name="password"> 
	<span id="errorPass"></span><br>
	
	Conferma Password: <input id="confPass" type="password" onchange="checkPass()" name="confermaPassword"> 
	<span id="errorConfPass"></span><br>
	
	<input id="dati" type="checkbox" name="trattamentoDati"> Accetto i termini del 
	trattamento dati personali. 
	<span id="errorCheck"></span><br>
	
	<input id="conferma" type="submit" value="Conferma Registrazione">
	</form>
<%@include file ="footer.jsp"%>
</body>
</html>