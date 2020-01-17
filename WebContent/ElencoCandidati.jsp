<%

List<Inoccupato> listaCandidati = (List<Inoccupato>)session.getAttribute("listaCandidati");
String titoloAnnuncio = (String) session.getAttribute("titoloAnnuncio");
int idAnnuncio =  (Integer) session.getAttribute("idAnnuncio"); 
%>
<%@page import="java.util.*" %>
<%@page import="easyjob.entity.Inoccupato" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elenco Candidati</title>
<%@include file ="librerie.html"%>
<link rel="stylesheet" type="text/css" href="css/ordini.css">
</head>
<body>
<%@include file ="header.jsp"%>
<div style="height:80px"></div>
<h3> Ecco i candidati per l'annuncio <%=titoloAnnuncio %> </h3>
<%if (listaCandidati.isEmpty()){
%>
<p> Non ci sono ancora candidati per questo annuncio </p>

<%
}else { %>
<div class="tabbbella">
	<table class="table table-sm">
		<thead>
			<tr>
			<th scope="col" style="color:#007bff"> Nome </th>
			<th scope="col" style="color:#007bff"> Cognome </th>
			<th scope="col" style="color:#007bff">Data di nascita </th>
			<th scope="col" style="color:#007bff"> Residenza </th>
			<th scope="col" style="color:#007bff"> Curriculum </th>
			<th scope="col" style="color:#007bff"> Contatta candidato </th>
			</tr>
		</thead>
	<tbody>
	<% 
	for(int i=0;i<listaCandidati.size();i++){
		Inoccupato temp = listaCandidati.get(i);
		int id = temp.getIdUser();
		String nome = temp.getNome();
		String cognome = temp.getCognome();
		String dataNascita = temp.getDataNascita();
		String residenza = temp.getResidenza();
		String cv = temp.getCurriculum();
	
%>
	<tr>
	<th scope="row"> <%=nome %> </th>
	<td> <%=cognome %></td>
	<td> <%= dataNascita %></td>
	<td> <%= residenza %></td>
	<td><form action="${pageContext.request.contextPath}/DisplayCurriculumServlet" method="POST">
	<input type="hidden" name="attributoFittizio" value="<%=id %>">
	<button type="submit" style="background-color:transparent; border:none;"><img src="css/images/pdf-icon.png" class="miaClasse"></button>	
	</form></td>
	<td>
	<a href=<%="./contattaCandidato.jsp?idUt=" + id + "&idAn=" + idAnnuncio%>>
	<button style="background-color:transparent;border:none; padding-right:20px" type="submit"><img  class="miaClasse2" src="css/images/msg2.png"></button>
	</a>
	</td>
	
	</tr>
	
	
	
	
	
<%
	}//FINE FOR
} // FINE ELSE
%>
</tbody>
</table>
</div>
<div style="height:150px"> </div>
<%@include file ="footer.jsp"%>
</body>
</html>