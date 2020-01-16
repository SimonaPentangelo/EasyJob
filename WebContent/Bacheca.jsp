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
<title>Insert title here</title>
<%@include file ="librerie.html"%>

</head>
<body>
	<%@include file="header.jsp"%>
	<%
		if (annunci.isEmpty()) {
	%>
	<p>Non sono stati trovati annunci</p>

	<%
		} else {

			if (mod == null) {
	%>
	<form action="${pageContext.request.contextPath}/FiltraAnnunciServlet"
		method="GET">
		<input type="text" name="advancedSearch"> <input type="hidden"
			name="tag" value="<%=tagDellaRicerca%>"> <input type="submit"
			value="Cerca per città">
	</form>
	<%
		}

			for (int i = 0; i < annunci.size(); i++) {
				int id = annunci.get(i).getIdAnnuncio();
				String titolo = annunci.get(i).getTitolo();
				int idAzienda = annunci.get(i).getAzienda();
				String pathImage = "";
				int idAziendaDaVisualizzare = 0; /*Gigi questo è l'id che devi portarti nella jsp della form segnalazione*/
				for (int j = 0; j < aziende.size(); j++) {
					if (aziende.get(j).getIdUser() == idAzienda)
						pathImage = File.separator + aziende.get(j).getLogoAzienda();
					idAziendaDaVisualizzare = aziende.get(j).getIdUser();
				}
	%>
	<br>
	<p>
		<a href="./LeggiAnnuncioServlet?idAnnuncio=<%=id%>"><%=titolo%></a>
	</p>
	<form
		action="${pageContext.request.contextPath}/VisualizzaAziendaServlet"
		method="GET">
		<button>
			<img src="${pageContext.request.contextPath}<%=pathImage%>">
		</button>
		<input type="hidden" name="az" value="<%=idAziendaDaVisualizzare%>">
	</form>
	<p>
		Debugging
		<%=pathImage%></p>
	<br>
	<%
		} // fine for
		} // fine else
	%>
	<%@include file="footer.jsp"%>
</body>
</html>