<%@page import="java.util.*"%>
<%@page import="easyjob.entity.Segnalazione"%>
<%
	Boolean autenticato=(Boolean) session.getAttribute("autenticato");
	if((autenticato==null)||(!autenticato.booleanValue()))
	{
		response.sendRedirect("path di tentato accesso alla pagina senza effettuare login!");
		return;
		
	}
%>
<%
	Amministratore utente=(Amministratore) session.getAttribute("utenteAdmin");
	if(utente==null)
	{
		response.sendRedirect("./index.jsp");
		return;
		
	}
%>

<%@page import="easyjob.entity.Amministratore" %>
<%
	List<Segnalazione> segnalazioni = new ArrayList<>();
	segnalazioni = (ArrayList) session.getAttribute("segnalazioni");	
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizza Segnalazioni</title>
<%@include file ="librerie.html"%>
<link rel="stylesheet" type="text/css" href="css/responsabbbile.css">
<link rel="stylesheet" type="text/css" href="css/elencoSegnalazioni.css">
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
</head>
<body>
<%@include file ="header.jsp"%>
<% if (segnalazioni.isEmpty()) {%>
<h2> Non ci sono segnalazioni</h2>

<%} else{ %>
<div class="cont">
<h2>Elenco Segnalazioni:</h2> <br>
	<div class="myRow">
            <div class="myCol">
<%for(int i=0; i<segnalazioni.size();i++){
	
	String titolo = segnalazioni.get(i).getTitolo();
	String corpo = segnalazioni.get(i).getCorpo();
	String ahref= "VisualizzaSegnalazioneServlet?idAz="+segnalazioni.get(i).getAzienda() + "&idMod=" + segnalazioni.get(i).getModeratore();
%>
<div class="myCard">
<a href="<%=ahref%>"> <h3><%=titolo %></h3></a>
<br>
<%=corpo%>
</div>
<%
}
}
 %>
 </div>
 </div>
 <%@include file ="footer.jsp"%>
</body>
</html>