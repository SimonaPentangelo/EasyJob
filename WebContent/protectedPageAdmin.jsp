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
	Amministratore utente=(Amministratore) session.getAttribute("utenteAdmin");
	if(utente==null)
	{
		response.sendRedirect("./index.jsp");
		return;
		
	}
	else
	{
		 nameString= utente.getUsername();
		
		
	}
%>

<%@page import="easyjob.entity.Amministratore" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file ="librerie.html"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
<link rel ="stylesheet" type="text/css" href="css/header.css">
<link rel ="stylesheet" type="text/css" href="css/registerform.css">
<link rel ="stylesheet" type="text/css" href="css/responsabbbile.css">
</head>
<body>
<%@include file ="header.jsp"%>

<div class="maremma">
<h1> Benvenuto <%= nameString %></h1> <br>
<form  action="${pageContext.request.contextPath }/VisualizzaSegnalazioniServlet" method="GET">
	<input type="submit" class="umb-btn" value="Visualizza Segnalazioni">
</form>

 <form action = "${pageContext.request.contextPath }/LogoutServlet">
    <input type="submit" class="umb-btn" value="Logout">
    </form>
</div>
<%@include file ="footer.jsp"%>
</body>
</html>