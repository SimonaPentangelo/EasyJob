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
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Visualizza Segnalazione</title>
</head>
<body>
<%@include file ="header.jsp"%>
<div style="height:80px"> </div>
<div class="container">
<%
	
	String titolo = segnalazione.getTitolo();
	String corpo = segnalazione.getCorpo();
	
%>
<h2 style="margin-left:5em">Titolo Segnalazione: <%=titolo%></h2>
<h4 style="margin-left:5em">Corpo:<%=corpo%></h4>
 
	<form action="${pageContext.request.contextPath}/VisualizzaAziendaServlet" method="POST">
	<button style="margin-left:25em"class="umb-btn"> Vai all'azienda</button>
	<input type= "hidden" name="az" value="<%=segnalazione.getAzienda()%>">
	</form>
</div>
<div style="height:220px"></div>
 <%@include file ="footer.jsp"%>
</body>
</html>