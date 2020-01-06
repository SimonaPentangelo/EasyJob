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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../JS-SCRIPT/controlliContattaCandidato.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/ContattaCandidatoServlet" method="POST">
		Titolo: <input id="titolo" onchange="checkTitolo()" type="text" name="titolo">
		<span id="errorTit"></span> <br>
	
		Messaggio: <input id="msg" onchange="checkMsg()" type="text" name="messaggio">
		<span id="errorMsg"></span> <br>
	
		<input type="hidden" name="idUtente" value="<%=idUt%>">
		<input type="hidden" name="idAnnuncio" value="<%=idAn%>">
		<input id="conferma" type="submit" value="Invia">
	</form>
</body>
</html>