<%
	Boolean autenticato=(Boolean) session.getAttribute("autenticato");
	if((autenticato==null)||(!autenticato.booleanValue()))
	{
		response.sendRedirect("path di tentato accesso alla pagina senza effettuare login!");
		return;
		
	}
%>

<%!String nameString=""; String surnameString=""; %>
<%
	Inoccupato utente=(Inoccupato) session.getAttribute("utenteInoccupato");
	if(utente==null)
	{
		response.sendRedirect("./index.jsp");
		return;
		
	}
	else
	{
		 nameString= utente.getUsername();
		
		
	}
%>

<%@page import="easyjob.entity.Inoccupato" %>
<%@page import="easyjob.entity.Azienda" %>
<%@page import="easyjob.entity.Invito" %>
<%@page import="java.util.*"%>

<%
	List<Invito> inviti = new ArrayList<Invito>();
	inviti = (ArrayList) session.getAttribute("elencoInviti");	
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elenco Inviti</title>
</head>
<body>
	<% if (inviti.isEmpty()) {%>
	
	<p> Non ci sono inviti</p>

	<%} else{ %>
	
	<h2>Elenco Inviti:</h2> <br>
	
	<%for(int i=0; i<inviti.size() ;i++){
		String titolo = inviti.get(i).getTitolo();
		String azienda = inviti.get(i).getNomeAzienda();
		String ahref1= "../../VisualizzaInvitoServlet?ad="+inviti.get(i).getAnnuncio();
		String ahref2="../../VisualizzaAziendaServlet?az="+inviti.get(i).getAzienda();
	%>
	
	<a href="<%=ahref1%>"> <p> Titolo: <%=titolo %></p></a>
	<a href="<%=ahref2%>"> <p> Mittente: <%=azienda %></p></a>
	<% } } %>
</body>
</html>