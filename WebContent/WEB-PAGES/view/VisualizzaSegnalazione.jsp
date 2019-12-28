<%@page import="java.util.*"%>
<%@page import="easyjob.entity.Segnalazione"%>
<%
	Segnalazione segnalazione = new Segnalazione();
	segnalazione = (Segnalazione) session.getAttribute("segnalazione");	
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizza Segnalazione</title>
</head>
<body>

<h2>Segnalazione:</h2> <br>
<%
	
	String titolo = segnalazione.getTitolo();
	String corpo = segnalazione.getCorpo();
	
%>
<h2><%=titolo%></h2><br>
<p><h3>Corpo:</h3></p><br>
<%=corpo%> <br>
<form action="${pageContext.request.contextPath}/RimozioneUtenteServlet" method="GET">
	<button type="submit">Rimuovi Utente</button>
	</form>
 
</body>
</html>