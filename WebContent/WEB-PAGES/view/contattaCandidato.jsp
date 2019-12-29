<% 
int idUt = Integer.parseInt(request.getParameter("idUt"));
int idAn = Integer.parseInt(request.getParameter("idAn"));
%>
<%@page import="javax.servlet.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contatta Candidato</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/ContattaCandidatoServlet" method="POST">
	Titolo: <input type="text" name="titolo">
	Messaggio: <input type="text" name="messaggio">
	<input type="hidden" name="idUtente" value="<%=idUt %>">
	<input type="hidden" name="idAnnuncio" value="<%=idAn %>">
	<input type="submit" value="Invia">
	</form>
</body>
</html>