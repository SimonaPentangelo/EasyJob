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
<script src="./JS-SCRIPT/controlliContattaCandidato.js"></script>
<%@include file ="librerie.html"%>
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
<link rel ="stylesheet" type="text/css" href="css/header.css">
<link rel ="stylesheet" type="text/css" href="css/responsabbbile.css">
<link rel ="stylesheet" type="text/css" href="css/registerform.css">
<link rel ="stylesheet" type="text/css" href="css/publish.css">
</head>
<body>
<%@include file ="header.jsp"%>


<div class="maremma">

<h1>Contatta un candidato</h1><br>
<%
		String stringa = "";
		if(response.getHeader("errore") != null) {
			stringa = response.getHeader("errore");
		} 
		%>
		<h3><%=stringa %></h3>

	<form action="${pageContext.request.contextPath}/ContattaCandidatoServlet" method="POST">
	
		<div>
		<span>Titolo:</span><br>
		<input class="instestoPub" id="titolo" onchange="checkTitolo()" type="text" name="titolo">
		<span id="errorTit"></span><br><br>
		</div>
		
		<div>
		<span>Messaggio:</span><br> 
		<textarea class="bigText" id="msg" onchange="checkMsg()" name="messaggio"></textarea> <br>
		<span id="errorMsg"></span><br><br>
		</div>
		
		<input type="hidden" name="idUtente" value="<%=idUt%>">
		<input type="hidden" name="idAnnuncio" value="<%=idAn%>">
		<input id="conferma" class="umb-btn" type="submit" value="Invia">
	</form>
</div>
	<%@include file ="footer.jsp"%>
</body>
</html>