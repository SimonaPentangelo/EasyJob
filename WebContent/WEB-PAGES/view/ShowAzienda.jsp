<%@page import="easyjob.entity.Moderatore"%>
<%@ page import="easyjob.entity.Azienda" %>
<%
Azienda azienda = (Azienda) session.getAttribute("aziendaDaVisualizzare");
%>
<%@page import="easyjob.entity.Amministratore" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p> Pagina dell'azienda <%= azienda.getNomeAzienda() %>:<p>
<img src=" ${pageContext.request.contextPath}<%= azienda.getLogoAzienda()%>">
<p> Numero Dipendenti all'attivo: <%=azienda.getNumeroDipendenti() %> </p>
<p>Partita iva: <%=azienda.getPartitaIVA() %> </p>
<p>Data fondazione: <%=azienda.getDataFondazione() %> </p>
<p>Indirizzo sede: <%= azienda.getIndirizzoSede() %>

	<% 
	Moderatore mod = (Moderatore) session.getAttribute("utenteModeratore");
	if (mod != null) {
	%>
	<br>
	<button><a href="formSegnalazione.jsp"> Segnala</a></button>
	<input type= "hidden" name="az" value="<%=azienda.getIdUser()%>">
	<%
	}
	%>
	
	<% 
	Amministratore admin = (Amministratore) session.getAttribute("utenteAmministratore");
	if (admin != null) {
	%>
	<br>
	<button><a href="${pageContext.request.contextPath}/RimozioneUtenteServlet"> Rimuovi</a></button>
	<input type= "hidden" name="az" value="<%=azienda.getIdUser()%>">
	<%
	}
	%>

</body>
</html>