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
<meta charset="ISO-8859-1">
<title>Invia Segnalazione</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../JS-SCRIPT/controlliFormSegnalazione.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/SegnalazioneUtenteServlet" method="POST">
	Titolo: <input id="titolo" onchange="checkTitolo()" type="text" name="titolo"> 
	<span id="errorTit"></span><br>
	
	Corpo: <input id="body" onchange="checkCorpo()" type="text" name="corpo"> 
	<span id="errorBody"></span><br>
	
	Data: <input id="data" onchange= type="text" name="data"> 
	<span id="errorData"></span><br>
	
    <input type="hidden" name="azienda" value="<%=idAzienda%>"> <br>
    <input type="hidden" name="moderatore" value="<%=idMod%>"><br> 
	<br>	
	<input id="conferma" type="submit" value="Invia Segnalazione">
	</form>

</body>
</html>