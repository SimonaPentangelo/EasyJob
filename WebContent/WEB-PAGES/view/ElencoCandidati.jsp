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
<title>Insert title here</title>
</head>
<body>
<p> Ecco i candidati per l'annuncio <%=titoloAnnuncio %> </p>
<%if (listaCandidati.isEmpty()){
%>
<p> Non ci sono ancora candidati per questo annuncio </p>

<%
}else {
	for(int i=0;i<listaCandidati.size();i++){
		Inoccupato temp = listaCandidati.get(i);
		int id = temp.getIdUser();
		String nome = temp.getNome();
		String cognome = temp.getCognome();
		String dataNascita = temp.getDataNascita();
		String residenza = temp.getResidenza();
		String cv = temp.getCurriculum();
	
%>
	Nome: <%=nome %>
	Cognome:<%=cognome %>
	Data di nascita: <%=dataNascita %>
	Residenza: <%=residenza %>
	
	<form action="${pageContext.request.contextPath}/DisplayCurriculumServlet" method="POST">
	<input type="hidden" name="attributoFittizio" value="<%=id %>">
	<button type="submit">Mostra CV</button>	
	</form>
	
	<a href=<%="./contattaCandidato.jsp?idUt=" + id + "&idAn=" + idAnnuncio%>>
	<button type="submit">Contatta il Candidato</button>
	</a>
<%
	}//FINE FOR
} // FINE ELSE
%>
</body>
</html>