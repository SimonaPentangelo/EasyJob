<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione Azienda</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../JS-SCRIPT/controlliRegistrazioneAzienda.js"></script>
</head>
<body>
	<form enctype='multipart/form-data' action="${pageContext.request.contextPath}/RegistrazioneAziendaServlet" method="POST">
	Nome Azienda: <input id="nomeAzienda" onchange="checkNomeAzienda" type="text" name="nomeAzienda"> <br>
	Logo: <input id="logo" onchange="checkLogo()" type="file" accept="image/*" name="logoAzienda"> <br>
	Partita IVA: <input id="piva" onchange="checkPIva()" type="text" name="partitaIVA"> <br>
	Username: <input id="username" onchange="checkUsername()" type="text" name="username"> <br>
	Indirizzo Sede: <input id="indirizzo" onchange="checkIndirizzo()" type="text" name="indirizzoSede"> <br>
	Data Fondazione: <input id="dataFond" onchange="checkDataFondazione()" type="date" name="dataFondazione"> <br>
	Breve Descrizione: <input id="desc" onchange="checkDescrizione()" type="text" name="descrizione"> <br>
	Numero Dipendenti: <input id="dip" onchange="checkDipendenti()" type="text" name="numeroDipendenti"> <br>
	e-mail: <input id="email" onchange="checkEmail()" type=email" name="email"> <br>
	Password: <input id="pass" onchange="checkPass()" type="password" name="password"> <br>
	Conferma Password: <input type="password" name="confermaPassword"> <br>
	<input type="checkbox" name="trattamentoDati"> Accetto i termini del 
	trattamento dati personali. <br>
	<input type="submit" value="Conferma Registrazione">
	</form>
</body>
</html>