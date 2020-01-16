<%Moderatore mod = (Moderatore) session.getAttribute("utenteModeratore");
  int idAzienda = Integer.parseInt(request.getParameter("az"));
 int idMod = mod.getIdUser();
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
</head>
<body>
<%@include file ="header.jsp"%>
<form action="${pageContext.request.contextPath}/SegnalazioneUtenteServlet" method="POST">
	Titolo: <input id="titolo" onchange="checkTitolo()" type="text" name="titolo"> 
	<span id="errorTit"></span><br>
	
	Corpo: <input id="corpo" onchange="checkBody()" type="text" name="corpo"> 
	<span id="errorBody"></span><br>
	
    <input type="hidden" name="azienda" value="<%=idAzienda%>"> <br>
    <input type="hidden" name="moderatore" value="<%=idMod%>"><br> 
	<br>	
	<input id="conferma" type="submit" value="Invia Segnalazione">
	</form>

<%@include file ="footer.jsp"%>
</body>
</html>