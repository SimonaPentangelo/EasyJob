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
<script src="./JS-SCRIPT/controlliModificaCurriculum.js"></script>
<%@include file ="librerie.html"%>
</head>
<body>
<%@include file ="header.jsp"%>

<div class="container">
<div style="height:100px"></div>
	<h2 style=""> Benvenuto nella tua area privata <%= nameString %>!</h2>
	<div style="heigt:80px"></div>
	<h2 style=""> Ecco un riassunto dei tuoi dati personali: </h2>
<div class="row">

<div class="col-md-6">
	<ul>
	<li style="list-style-type: none;"> Username: <%=nameString%></li>	
	<li style="list-style-type: none;"> Nome: <%=utente.getNome()%></li>
	<li style="list-style-type: none;"> Cognome: <%=utente.getCognome()%></li>
	<li style="list-style-type: none;"> Città: <%=utente.getCittà()%></li>
	<li style="list-style-type: none;"> Residenza: <%=utente.getResidenza()%></li>
	</ul>
</div>
<div class="col-md-6">
		<form action="${pageContext.request.contextPath}/DisplayCurriculumServlet" method="POST">
			<button class="umb-btn" type="submit">Visualizza Curriculum</button> <br>
		</form> 
		
		<span id="errorCurriculum"></span>
		<div style="height:30px"></div>
		<form onsubmit="checkCurriculum()" action="${pageContext.request.contextPath}/ModificaCurriculumServlet" method="POST" enctype='multipart/form-data'>
			 Nuovo Curriculum: <input id="curriculum" type="file" name="curriculum"> <br>
			<button class="umb-btn" id="conferma" type="submit">Modifica Curriculum </button>
		</form> 
		<%
		String stringa = "";
		if(response.getHeader("errorUpdate") != null) {
			stringa = response.getHeader("errorUpdate");
		} 
		if(response.getHeader("successUpdate") != null) {
			stringa = response.getHeader("successUpdate");
		}
		%>
		<span><%=stringa %></span>
		<div style="height:30px"></div>
		<form action="${pageContext.request.contextPath}/VisualizzaCandidatureServlet" method ="GET">
		<button class="umb-btn" type="submit">Visualizza candidature effettuate</button>
		</form>
		
		<div style="height:30px"></div>
		    <% if(annuncioSel != null){
    	%>
    	<a href="ShowAnnuncio.jsp">Torna all'annuncio</a>
    <% }%>
</div>
</div>


<div style="height:40px"></div>

	<% if (inviti.isEmpty()) {%>
	
	<p> Non ci sono inviti</p>

	<%} else{ %>
	
	<h2>Elenco Inviti:</h2> 
	<ol>
	<%for(int i=0; i<inviti.size() ;i++){
		String titolo = inviti.get(i).getTitolo();
		String ahref= "VisualizzaInvitoServlet?idAd="+inviti.get(i).getAnnuncio() + "&idInocc="+inviti.get(i).getInoccupato();
	%>
	<li style="padding-right:80%"><p style="align:left">Titolo: <a href="<%=ahref%>"><%=titolo %></a></p></li>	
	<% }%>
	</ol>
	<%} %>
	
	<form action="${pageContext.request.contextPath}/VisualizzaInvitiServlet" method="POST">
		<button class="umb-btn" type="submit">Inviti ricevuti</button> 
	</form>
	

    
    </div>
    <%@include file ="footer.jsp"%>
</body>
</html>