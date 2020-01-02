<%
	Boolean autenticato=(Boolean) session.getAttribute("autenticato");
	if((autenticato==null)||(!autenticato.booleanValue()))
	{
		response.sendRedirect("path di tentato accesso alla pagina senza effettuare login!");
		return;
		
	}
%>
<%
	Amministratore utente=(Amministratore) session.getAttribute("utenteAdmin");
	if(utente==null)
	{
		response.sendRedirect("./index.jsp");
		return;
		
	}
%>

<%@page import="easyjob.entity.Amministratore" %>
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
<h2><%=segnalazione.getAzienda()%></h2><br>
<p><h3>Corpo:</h3></p><br>
<%=corpo%> <br>
	<form action="${pageContext.request.contextPath}/VisualizzaAziendaServlet" method="POST">
	<input type= "submit" value="Vai all'azienda">
	<input type= "hidden" name="az" value="<%=segnalazione.getAzienda()%>">
	</form>
 
</body>
</html>