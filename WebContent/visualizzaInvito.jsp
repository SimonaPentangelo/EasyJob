<%
	Boolean autenticato=(Boolean) session.getAttribute("autenticato");
	if((autenticato==null)||(!autenticato.booleanValue()))
	{
		response.sendRedirect("path di tentato accesso alla pagina senza effettuare login!");
		return;
		
	}
%>

<%!String nameString=""; String surnameString=""; %>
<%
	Inoccupato utente=(Inoccupato) session.getAttribute("utenteInoccupato");
	if(utente==null)
	{
		response.sendRedirect("./index.jsp");
		return;
		
	}
%>
<%@page import="easyjob.entity.Inoccupato" %>
<%@page import="easyjob.entity.Invito" %>

<% Invito invito = (Invito) request.getSession().getAttribute("invito"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<link rel="stylesheet" type="text/css" href="css/index.css">
<meta charset="ISO-8859-1">
<title>Invito</title>
</head>
<body>
<%@include file ="header.jsp"%>
<div style="height:70px"></div>
<div class="container">
<h2 style="color:#007bff">Ecco l'invito <%=invito.getTitolo() %></h2>
<ul>
	<li style="list-style: none"><h5>Mittente: <%=invito.getNomeAzienda()%></h5></li>
	<li style="list-style: none"><h5>Corpo: <%=invito.getCorpo()%></h5></li>
</ul>
<button class="umb-btn"><a href=visualizzaElencoInviti.jsp style="color:white">Torna alla lista degli inviti</a></button>
</div>
<div style="height:300px"></div>
<%@include file ="footer.jsp"%>
</body>
</html>