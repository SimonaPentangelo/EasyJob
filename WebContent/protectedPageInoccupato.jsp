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
		response.sendRedirect("index.jsp");
		return;
		
	}
	else
	{
		 nameString= utente.getUsername();
		
		
	}
	
%>
<%
Annuncio annuncioSel = (Annuncio) session.getAttribute("annuncioSelezionato");
%>

<%@page import="easyjob.entity.Inoccupato" %>
<%@page import="easyjob.entity.Invito" %>
<%@page import="java.util.*"%>
<%@page import="easyjob.entity.Annuncio" %>

<%
	List<Invito> inviti = new ArrayList<>();
	inviti = (ArrayList) session.getAttribute("inviti");	
%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina Personale Inoccupato</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../JS-SCRIPT/controlliModificaCurriculum.js"></script>
</head>
<body>
	<p> Benvenuto nella tua area private <%= nameString %></p>
	<button><a href="index.jsp"> Torna alla home</a></button>

	<ul>
	<li><%=nameString%></li>
	<li><%=utente.getNome()%></li>
	<li><%=utente.getCognome()%></li>
	<li><%=utente.getCittà()%></li>
	<li><%=utente.getResidenza()%></li>
	</ul> <br>
	
	<% if (inviti.isEmpty()) {%>
	
	<p> Non ci sono inviti</p>

	<%} else{ %>
	
	<h2>Elenco Inviti:</h2> <br>
	
	<%for(int i=0; i<inviti.size() ;i++){
		String titolo = inviti.get(i).getTitolo();
		String ahref= "../../VisualizzaInvitoServlet?idAd="+inviti.get(i).getAnnuncio() + "&idInocc="+inviti.get(i).getInoccupato();
	%>
	
	<a href="<%=ahref%>"> <p> Titolo: <%=titolo %></p></a>
	
	<% } } %>
	
	<form action="${pageContext.request.contextPath}/VisualizzaInvitiServlet" method="POST">
		<input type="submit" value="Inviti ricevuti"> <br> <br>
	</form>
	
	<div>
		<form action="${pageContext.request.contextPath}/DisplayCurriculumServlet" method="POST">
			<input type="submit" value="Visualizza Curriculum"> <br>
		</form> 
		
		<form action="${pageContext.request.contextPath}/ModificaCurriculumServlet" method="POST" enctype='multipart/form-data'>
			 Nuovo Curriculum: <input id="curriculum" onchange="checkCurriculum" type="file" name="curriculum"> <br>
			<input id="conferma" type="submit" value="Modifica Curriculum">
		</form> 
		
		<form action="${pageContext.request.contextPath}/VisualizzaCandidatureServlet" method ="GET">
		<input type="submit" value="Visualizza candidature effettuate">
		</form>
    </div>
    <form action = "${pageContext.request.contextPath }/LogoutServlet">
    <input type="submit" value="Logout">
    </form>
    <% if(annuncioSel != null){
    	%>
    	<a href="ShowAnnuncio.jsp">Torna all'annuncio</a>
    <% }%>
</body>
</html>