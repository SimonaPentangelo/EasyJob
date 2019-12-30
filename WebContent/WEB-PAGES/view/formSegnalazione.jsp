<%Moderatore mod = (Moderatore) session.getAttribute("utenteModeratore");
  int idAzienda = Integer.parseInt(request.getParameter("idAzienda"));
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
</head>
<body>
<form action="${pageContext.request.contextPath}/SegnalazioneUtenteServlet" method="GET">
	Titolo: <input type="text" name="titolo"> <br>
	Corpo: <input type="text" name="corpo"> <br>
	Data: <input type="text" name="data"> <br>
    <input type="hidden" name="azienda" value="<%=idAzienda%>"> <br>
    <input type="hidden" name= "moderatore" value="<%=idMod%>"> <br> 
	<br>
	
	<input type="submit" value="Invia Segnalazione">
	</form>

</body>
</html>