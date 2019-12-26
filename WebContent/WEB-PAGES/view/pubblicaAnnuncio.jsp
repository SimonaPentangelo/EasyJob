<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pubblica Annuncio</title>
</head>
<body>
	<form action="../../PubblicaAnnuncioServlet" method="GET">
	Titolo: <input type="text" name="titolo"> <br>
	Descrizione: <input type="text" name="descrizione"> <br>
	Requisiti: <input type="text" name="requisiti"> <br>
	Tipo contratto: 
	<select name="tcontratto">
  		<option value="full-time">Full-time</option>
  		<option value="part-time">Part-time</option>
  		<option value="apprendistato">Apprendistato</option>
  		<option value="progetto">Progetto</option>
  		<option value="tirocinio">Cottimo</option>
  		<option value="stagista"> Stagista </option>
	</select>
	Città: <input type="text" name="city"> <br>
	Tags: <input type="text" name= "tags" placeholder="Inserisci uno o più tag susseguiti dalla virgola"> <br> 
	<br>
	
	<input type="submit" value="Pubblica Annuncio">
	</form>
</body>
</html>