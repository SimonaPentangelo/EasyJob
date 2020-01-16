<% 
int idUt = Integer.parseInt(request.getParameter("idUt"));
int idAn = Integer.parseInt(request.getParameter("idAn"));
String errorFormat = "";
if(response.getHeader("errore") != null) {
	errorFormat = response.getHeader("errore").toString(); }
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
<script src="./JS-SCRIPT/controlliContattaCandidato.js"></script>
<%@include file ="librerie.html"%>
</head>
<body>
<%@include file ="header.jsp"%>

<div class="maremma">

<h1>Contatta un candidato</h1>


<%if (errorFormat!=null){
			%><h3><%=errorFormat %></h3>
		<%} %>
	<form action="${pageContext.request.contextPath}/PubblicaAnnuncioServlet" method="POST">
		Titolo: <input id="titolo" onchange="checkTitolo()" type="text" name="titolo">
		<span id="errorTit"></span> <br>
		
		Messaggio: <input id="msg" onchange="checkMsg()" type="text" name="messaggio">
		<span id="errorMsg"></span> <br>
		<input type="hidden" name="idUtente" value="<%=idUt%>">
		<input type="hidden" name="idAnnuncio" value="<%=idAn%>">
		<input id="conferma" type="submit" value="Invia">
	</form>
	<%@include file ="footer.jsp"%>
</body>
</html>