<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione Azienda</title>
</head>
<body>
	<form enctype='multipart/form-data' action="${pageContext.request.contextPath}/RegistrazioneAziendaServlet" method="POST">
	Nome Azienda: <input type="text" name="nomeAzienda"> <br>
	Logo: <input type="file" accept="image/*" name="logoAzienda"> <br>
	Partita IVA: <input type="text" name="partitaIVA"> <br>
	Username: <input type="text" name="username"> <br>
	Indirizzo Sede: <input type="text" name="indirizzoSede"> <br>
	Data Fondazione: <input type="date" name="dataFondazione"> <br>
	Breve Descrizione: <input type="text" name="descrizione"> <br>
	Numero Dipendenti: <input type="text" name="numeroDipendenti"> <br>
	e-mail: <input type=email" name="email"> <br>
	Password: <input type="password" name="password"> <br>
	Conferma Password: <input type="password" name="confermaPassword"> <br>
	<input type="checkbox" name="trattamentoDati"> Accetto i termini del 
	trattamento dati personali. <br>
	<input type="submit" value="Conferma Registrazione">
	</form>
</body>
</html>