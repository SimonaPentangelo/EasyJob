<%Moderatore mod = (Moderatore) session.getAttribute("utenteModeratore");
  int idAzienda = Integer.parseInt(request.getParameter("az"));
%>

<%@ page import ="easyjob.entity.Moderatore" %>
<%@ page import ="easyjob.entity.Segnalazione" %>
<%@ page import ="java.util.*" %>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Invia Segnalazione</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="./JS-SCRIPT/controlliFormSegnalazione.js"></script>
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
<link rel ="stylesheet" type="text/css" href="css/header.css">
<link rel ="stylesheet" type="text/css" href="css/responsabbbile.css">
<link rel ="stylesheet" type="text/css" href="css/registerform.css">
<link rel ="stylesheet" type="text/css" href="css/publish.css">
</head>
<body>
<%@include file ="header.jsp"%>

<div class="maremma">

<h1>Segnala un'azienda</h1><br>
<%
		String stringa = "";
		if(response.getHeader("errorReport") != null) {
			stringa = response.getHeader("errorReport").toString();
		} 
		%>
		<h3><%=stringa %></h3>

<form onsubmit="checkAll()" action="${pageContext.request.contextPath}/SegnalazioneUtenteServlet" method="POST">

	<div>
	<span>Titolo:</span><br>
	<input class="instestoPub"  id="titolo" type="text" name="titolo"> 
	<span id="errorTit"></span><br><br>
	</div>
	
	<div>
	<span>Messaggio:</span><br> 
	<textarea class="bigText" id="corpo" name="corpo"></textarea> <br>
	<span id="errorMsg"></span><br><br>
	</div>
	
    <input type="hidden" name="azienda" value="<%=idAzienda%>">
	<input id="conferma" class="umb-btn" type="submit" value="Invia Segnalazione">
	</form>
</div>
<%@include file ="footer.jsp"%>
</body>
</html>