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

<% Invito i = (Invito) request.getSession().getAttribute("invito"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Invito</title>
</head>
<body>
<%@include file ="header.jsp"%>
<ul>
	<li>Oggetto: <%=i.getTitolo()%></li>
	<li>Mittente: <%=i.getNomeAzienda()%></li>
	<li><%=i.getCorpo()%></li>
	</ul> <br>
<%@include file ="footer.jsp"%>
</body>
</html>