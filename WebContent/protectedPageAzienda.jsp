<%
	Boolean autenticato=(Boolean) session.getAttribute("autenticato");
	if(autenticato == null || !autenticato)
	{
		response.sendRedirect("path di tentato accesso alla pagina senza effettuare login!");
		return;
		
	}
%>

<%!String nameString=""; String surnameString=""; %>
<%
	Azienda utente=(Azienda) session.getAttribute("utenteAzienda");
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

<%@page import="easyjob.entity.Azienda" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@include file ="librerie.html"%>
</head>
<body>
<%@include file ="header.jsp"%>
<div style="height:80px"></div>
<h2>Benvenuto <%= nameString %> </h2>

<form action = "${pageContext.request.contextPath}/VisualizzaElencoAnnunciServlet" method="get">
<button type="submit">Visualizza annunci pubblicati </button>
</form>

 <form action = "${pageContext.request.contextPath }/LogoutServlet">
    <input type="submit" value="Logout">
    </form>
<%@include file ="footer.jsp"%>
</body>
</html>