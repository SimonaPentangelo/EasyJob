<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
</head>
<body>
	<ul>
		<li><a href="WEB-PAGES/view/registrazioneInoccupato.jsp">Registrazione Inoccupato</a></li>
		<li><a href="WEB-PAGES/view/registrazioneAzienda.jsp">Registrazione Azienda</a></li>
		<li><a href="WEB-PAGES/view/login.jsp">Login</a></li>
		<li><a href="WEB-PAGES/view/pubblicaAnnuncio.jsp">Pubblica Annuncio</a></li>
	</ul>
	
	<form action="RicercaAnnunciServlet" method="GET">
		<input type="text" name="searchTag"> <br>
		<input type="submit" value ="Cerca Annunci"> <br>
	</form>
	
	<img src="" alt="Immagine sito"> <br>
	
	<p>Descrizione del sito</p>
</body>
</html>