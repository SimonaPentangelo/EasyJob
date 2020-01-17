<% Azienda azienda = (Azienda) session.getAttribute("utenteAzienda");
   ArrayList<Annuncio> listaAnnunci = (ArrayList<Annuncio>) session.getAttribute("listaAnnunci");
%>

<%@ page import="easyjob.entity.Azienda" %>
<%@ page import="easyjob.entity.Annuncio" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elenco annunci</title>
<%@include file ="librerie.html"%>
<link rel="stylesheet" type="text/css" href="css/ordini.css">
</head>
<body>
<%@include file ="header.jsp"%>
<div style="height:80px"></div>
<h2 style="color:#007bff">Ciao <%= azienda.getNomeAzienda() %>, ecco l'elenco degli annunci pubblicati: </h2>
<%
if (listaAnnunci.isEmpty()){
	
%>

<p> Non hai pubblicato annunci fin'ora, puoi pubblicarne subito uno utilizzando il seguente pulsante</p>
<button class="umb-btn"><a href="pubblicaAnnuncio.jsp"> Pubblica Annuncio</a></button>
<%
}else{%>
<div class="tabbbella">
		<table class="table table-sm"> 
		<%
	for (int i=0;i<listaAnnunci.size();i++){
		Annuncio annuncio = listaAnnunci.get(i);
		String titolo = annuncio.getTitolo();
		String data = annuncio.getData();
		int id = annuncio.getIdAnnuncio();
		String ahref= "./VisualizzaCandidatiServlet?idAn="+id+"&tit="+titolo;
		%>
	
			<thead>
				<tr>
					<th scope="col"> Titolo </th>
					<th scope="col"> Data Pubblicazione </th>
				</tr>
			</thead>
	<tbody>
	<tr>
	<th scope="row"><a href="<%=ahref%>"> <%=titolo %> </a> </th>
	<td> <%=data %></td>
					
	</tr>
	
<%
	}// FINE FOR
}// FINE ELSE
%>
</tbody>
</table>
</div>
<%@include file ="footer.jsp"%>
</body>
</html>