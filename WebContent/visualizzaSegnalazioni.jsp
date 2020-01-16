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
</head>
<body>
<%@include file ="header.jsp"%>
<% if (segnalazioni.isEmpty()) {%>
<p> Non ci sono segnalazioni</p>

<%} else{ %>
<h2>Elenco Segnalazioni:</h2> <br>
<%for(int i=0; i<segnalazioni.size();i++){
	
	String titolo = segnalazioni.get(i).getTitolo();
	String corpo = segnalazioni.get(i).getCorpo();
	String ahref= "/VisualizzaSegnalazioneServlet?idAz="+segnalazioni.get(i).getAzienda() + "&idMod=" + segnalazioni.get(i).getModeratore();
%>
<a href="<%=ahref%>"> <p> Titolo: <%=titolo %></p></a>
<p><h3>Corpo:</h3></p><br>
<%=corpo%>

<%
}
}
 %>
 <%@include file ="footer.jsp"%>
</body>
</html>