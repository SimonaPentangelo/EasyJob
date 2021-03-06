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
<link rel="stylesheet" type="text/css" href="css/responsabbbile.css">
<%@include file ="librerie.html"%>
<link rel="stylesheet" type="text/css" href="css/registerform.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file ="header.jsp"%>
<div class="maremma">

<h5>Pagina dell'azienda <%= azienda.getNomeAzienda() %>:</h5>
		
<img id="showAzienda" src="${pageContext.request.contextPath}\<%= azienda.getLogoAzienda()%>">
<p> Numero Dipendenti all'attivo: <%=azienda.getNumeroDipendenti() %> </p>
<p>Partita iva: <%=azienda.getPartitaIVA() %> </p>
<p>Data fondazione: <%=azienda.getDataFondazione() %> </p>
<p>Indirizzo sede: <%= azienda.getIndirizzoSede() %>

	<% 
	Moderatore mod = (Moderatore) session.getAttribute("utenteModeratore");
	if (mod != null) {
	%>
	<br>
	<button><a href="formSegnalazione.jsp?az=<%=azienda.getIdUser()%>"> Segnala </a></button>
	<%
	}
	%>
	
	<%
		String stringa = "";
		if(request.getSession().getAttribute("errorRemove") != null) {
			stringa = (String) request.getSession().getAttribute("errorRemove");
			request.getSession().removeAttribute("errorRemove");
			%> 	<h3><%=stringa %></h3> <%
		} 
		%>
	
	<% 
	Amministratore admin = (Amministratore) session.getAttribute("utenteAdmin");
	if (admin != null) {
	%>
	<br>
	<form action="${pageContext.request.contextPath}/RimozioneUtenteServlet" method="POST">
	<input type= "submit" value="Rimuovi">
	<input type= "hidden" name="az" value="<%=azienda.getIdUser()%>">
	</form>
	<%
	}
	%>
	</div>
	<div style="height:130px"></div>
<%@include file ="footer.jsp"%>
</body>
</html>