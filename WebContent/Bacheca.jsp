<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="easyjob.entity.Annuncio"%>
<%@page import="easyjob.entity.Azienda"%>
<%@page import="easyjob.entity.Moderatore"%>
<%
	List<Annuncio> annunci = new ArrayList<>();
	annunci = (ArrayList) session.getAttribute("annunci");
	Moderatore mod = (Moderatore) session.getAttribute("utenteModeratore");
	String tagDellaRicerca = "";
	String cit=(String) session.getAttribute("cit");
	if (mod == null) {
		tagDellaRicerca = (String) session.getAttribute("tag");
	}
	List<Azienda> aziende = (ArrayList) session.getAttribute("aziendeAnnunci");
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bacheca</title>
<%@include file ="librerie.html"%>
<link rel ="stylesheet" type="text/css" href="css/ordini.css">
</head>
<body>
	<%@include file="header.jsp"%>
<div style="height: 100px">
</div>	
<div class="container">
	<%
		if (annunci.isEmpty()) {
	%>
	<p style="color:red">Non sono stati trovati annunci per il tag: <%=tagDellaRicerca %></p>
	<div style="height:350px"></div>
	<%
		} else {

			if (mod == null) {
	%>
	<%if (cit==null){ %>
	<h2>Ecco gli annunci per il tag <%=tagDellaRicerca %></h2>
	<%}else{ %>
	<h2>Ecco gli annunci per il tag <%=tagDellaRicerca %> nella città <%=cit %></h2>
<% }%>
	<form action="FiltraAnnunciServlet" method="GET">
		<input type="text" class="city" name="advancedSearch"> <br> 
		<input type="hidden"name="tag" value="<%=tagDellaRicerca%>"> 
		<input class="umb-btn" type="submit"value="Cerca per città">
	</form>
	<div class="tabbbella">
	<table class="table table-sm">
	<thead>
	<tr>
	<th scope="col" style="color:#26C97B;"></th>
	<th scope="col" style="color:#26C97B;">Titolo</th>	
	<th scope="col" style="color:#26C97B;">Azienda</th>	
	<th scope="col" style="color:#26C97B;">Città</th>	
	<th scope="col" style="color:#26C97B;">TipoContratto</th>	
	</tr>
	</thead>
	<tbody>
	<%
		}
			String city = "";
			String tipoC = "";
			String nomeAz = "";
			for (int i = 0; i < annunci.size(); i++) {
				int id = annunci.get(i).getIdAnnuncio();
				String titolo = annunci.get(i).getTitolo();
				int idAzienda = annunci.get(i).getAzienda();
				String pathImage = "";
				city = annunci.get(i).getCittà();
				tipoC = annunci.get(i).getTipoContratto();
				int idAziendaDaVisualizzare = 0; /*Gigi questo è l'id che devi portarti nella jsp della form segnalazione*/
				for (int j = 0; j < aziende.size(); j++) {
					if (aziende.get(j).getIdUser() == idAzienda)
						pathImage = File.separator + aziende.get(j).getLogoAzienda();
					idAziendaDaVisualizzare = aziende.get(j).getIdUser();
					nomeAz = aziende.get(j).getNomeAzienda();
				}
	%>
	<tr>
	<th scope="row">
		<form
		action="${pageContext.request.contextPath}/VisualizzaAziendaServlet"
		method="GET">
		<button class="imgButton">
			<img class="logoElenco" src="${pageContext.request.contextPath}<%=pathImage%>">
		</button>
		<input type="hidden" name="az" value="<%=idAziendaDaVisualizzare%>">
	</form>
	</th>
	<td>
		<a href="./LeggiAnnuncioServlet?idAnnuncio=<%=id%>"><%=titolo%></a>
	</td>
	<td>
		<%=nomeAz %>
	</td>
	<td>
		<%=city %>
	</td>
		<td>
		<%=tipoC %>
	</td>
	</tr>
	<% } // fine for %>
	</tbody>
	</table>
	</div>
	<% } // fine else %>
</div>
	<%@include file="footer.jsp"%>
</body>
</html>