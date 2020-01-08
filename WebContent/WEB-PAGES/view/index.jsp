<% String errorTag = (String) session.getAttribute("errorTag"); %>

<%@page import="easyjob.entity.Moderatore" %>
<%@page import="easyjob.entity.Azienda" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../JS-SCRIPT/controlliRicercaAnnunciModeratore.js"></script>
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
		<input id="dataID" onchange="checkData()" type="date" name="data"> 
		<span id="errorData"></span><br>
		<input id="conferma" type="submit" value="Ricerca per data">
	</form>
	<%
	}// FINE IF, SE IN SESSIONE NON C'é UN MODERATORE ALLORA LA RICERCA IN BACHECA è QUELLA CLASSICA
	else {%>
	
	<form action="${pageContext.request.contextPath}/RicercaAnnunciServlet" method="GET">
		<input type="text" name="searchTag"> <br>
		<input type="submit" value ="Cerca Annunci"> <br>
		<%if(errorTag!= null){ 
		%>
		<p> <%=errorTag %> </p>
		<%} %>
	</form>
	<% }//Fine ELSE %>
	<img src="" alt="Immagine sito"> <br>
	
	<p>Descrizione del sito</p>
</body>
</html>