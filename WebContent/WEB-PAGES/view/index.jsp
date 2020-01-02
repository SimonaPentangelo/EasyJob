

<%@page import="easyjob.entity.Moderatore" %>
<%@page import="easyjob.entity.Azienda" %>
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
	</ul>
			
	
	<% 
	Azienda azienda = (Azienda) session.getAttribute("utenteAzienda");
	if (azienda != null) {
	%>
	<p>DEBUGGING </p>
	<button><a href="pubblicaAnnuncio.jsp"> Pubblica Annuncio</a></button>
	<%
	}
	%>
	
	<%
	Moderatore moderatore = (Moderatore) session.getAttribute("utenteModeratore");
	if(moderatore != null){	
	%>
	<form action = "${pageContext.request.contextPath}/RicercaAnnunciModeratore" method="GET">
		<input type="text" name="data"> 
		<input type="submit" value="Ricerca per data">
	</form>
	<%
	}// FINE IF, SE IN SESSIONE NON C'é UN MODERATORE ALLORA LA RICERCA IN BACHECA è QUELLA CLASSICA
	else {%>
	
	<form action="${pageContext.request.contextPath}/RicercaAnnunciServlet" method="GET">
		<input type="text" name="searchTag"> <br>
		<input type="submit" value ="Cerca Annunci"> <br>
	</form>
	<% }//Fine ELSE %>
	<img src="" alt="Immagine sito"> <br>
	
	<p>Descrizione del sito</p>
</body>
</html>