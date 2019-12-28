<%@page import="java.util.*"%>
<%@page import="easyjob.entity.Segnalazione"%>
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
</head>
<body>
<% if (segnalazioni.isEmpty()) {%>
<p> Non ci sono segnalazioni</p>

<%} else{ %>
<h2>Elenco Segnalazioni:</h2> <br>
<%for(int i=0; i<segnalazioni.size();i++){
	
	String titolo = segnalazioni.get(i).getTitolo();
	String corpo = segnalazioni.get(i).getCorpo();
	String ahref= "../../VisualizzaSegnalazioneServlet?idAz="+segnalazioni.get(i).getAzienda();
%>
<a href="<%=ahref%>"> <p> Titolo: <%=titolo %></p></a>
<p><h3>Corpo:</h3></p><br>
<%=corpo%>

<%
}
}
 %>
</body>
</html>