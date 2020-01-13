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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<ul>
		<li><a href="registrazioneInoccupato.jsp">Registrazione Inoccupato</a></li>
		<li><a href="registrazioneAzienda.jsp">Registrazione Azienda</a></li>
		<li><a href="login.jsp">Login</a></li>
	</ul>
			
	<div class="container-fluid padding back-food">
		<div class="zona" id="insert-address">
	<% 
	Azienda azienda = (Azienda) session.getAttribute("utenteAzienda");
	if (azienda != null) {
	%>
	<button><a href="pubblicaAnnuncio.jsp"> Pubblica Annuncio</a></button>
	<%
	}
	%>
	
	<%
	Moderatore moderatore = (Moderatore) session.getAttribute("utenteModeratore");
	if(moderatore != null){	
	%>
	<h3> Ricerca un annuncio per data</h3>
	<form  class= "group" action = "${pageContext.request.contextPath}/RicercaAnnunciModeratore" method="GET">
		<input id="dataID" onchange="checkData()" type="date" name="data"> 
		<span id="errorData"></span><br>
		<input id="conferma" type="submit" value="Ricerca per data">
	</form>
	<%
	}// FINE IF, SE IN SESSIONE NON C'é UN MODERATORE ALLORA LA RICERCA IN BACHECA è QUELLA CLASSICA
	else {%>
	<h3> Ricerca il tuo annuncio con un tag</h3>
	<form class ="group" action="${pageContext.request.contextPath}/RicercaAnnunciServlet" method="GET">
		<input type="text" name="searchTag" placeholder="Es:Informatica...">
		<button type=submit><i class="fa fa-search"></i></button><br><br>
		<%if(errorTag!= null){ 
		%>
		<span class="errore"> <%=errorTag %> </span>
		<%} %>
	</form>
		</div>
	</div>
	<% }//Fine ELSE %>

</body>
</html>