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
<title>Insert title here</title>
<%@include file ="librerie.html"%>
</head>
<body>
<%@include file ="header.jsp"%>
<p>Ciao <%= azienda.getNomeAzienda() %>, ecco l'elenco degli annunci pubblicati </p>
<%
if (listaAnnunci.isEmpty()){
	
%>

<p> Non hai pubblicato annunci fin'ora, puoi pubblicarne subito uno utilizzando il seguente pulsante</p>
<button><a href="pubblicaAnnuncio.jsp"> Pubblica Annuncio</a></button>
<%
}else{
	for (int i=0;i<listaAnnunci.size();i++){
		Annuncio annuncio = listaAnnunci.get(i);
		String titolo = annuncio.getTitolo();
		String data = annuncio.getData();
		int id = annuncio.getIdAnnuncio();
		String ahref= "../../VisualizzaCandidatiServlet?idAn="+id+"&tit="+titolo;
		%>
	
	<a href="<%=ahref%>"><p>Titolo: <%=titolo %></p></a>
	<p> Data pubblicazione: <%=data %></p>
	
<%
	}// FINE FOR
}// FINE ELSE
%>
<%@include file ="footer.jsp"%>
</body>
</html>