<%
	Boolean autenticato=(Boolean) session.getAttribute("autenticato");
	if((autenticato==null)||(!autenticato.booleanValue()))
	{
		response.sendRedirect("path di tentato accesso alla pagina senza effettuare login!");
		return;
		
	}
%>
<%
	Azienda utente= (Azienda) session.getAttribute("utenteAzienda");
	if(utente==null)
	{
		response.sendRedirect("./index.jsp");
		return;
	}
%>
<%@page import="easyjob.entity.Azienda" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bannato</title>
<%@include file ="librerie.html"%>
</head>
<body>
<%@include file ="header.jsp"%>
SEI STATO BANNATO.
<%@include file ="footer.jsp"%>
</body>
</html>