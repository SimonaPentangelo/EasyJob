<%
Moderatore moderatore = (Moderatore) session.getAttribute("utenteModeratore");
Inoccupato inoccupato = (Inoccupato) session.getAttribute("utenteInoccupato");
Annuncio annuncioSel = (Annuncio) session.getAttribute("annuncioSelezionato");
String titolo = annuncioSel.getTitolo();
String descrizione = annuncioSel.getTitolo();
String requisiti = annuncioSel.getRequisiti();
List<String> tags = annuncioSel.getTags();
String tipoContratto = annuncioSel.getTipoContratto();
String dataPubb = annuncioSel.getData();
String azienda = annuncioSel.getNomeAzienda();
int idAnnuncio = annuncioSel.getIdAnnuncio();
int idInocc= 0;
String redirect="";
%>

<% if (inoccupato == null) {
	 redirect="login.jsp";
}
else{
	idInocc = inoccupato.getIdUser();
	System.out.println(idInocc);
	redirect ="../../CandidaturaServlet";
}
%>
<%@ page import ="easyjob.entity.Inoccupato" %>
<%@ page import ="easyjob.entity.Annuncio" %>
<%@ page import="easyjob.entity.Moderatore" %>
<%@ page import ="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Annuncio</title>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/responsabbbile.css">
<link rel ="stylesheet" type="text/css" href="css/umbbtn.css">
<%@include file ="librerie.html"%>
</head>
<body>
<%@include file ="header.jsp"%>

<div style="margin-top:80px">

<p> <h4 style="margin-left:490px">Azienda: <%=azienda %></h4></p>
<div class="list">
<li style="list-style:none">Titolo annuncio: <%=titolo %> </li>
<li style="list-style:none">Descrizione: <%=descrizione %> </li>
<li style="list-style:none">Requisti: <%= requisiti %> </li>
<li style="list-style:none">Tag: 
<% for(int i=0;i<tags.size();i++){
	String tag = tags.get(i);
	%>
	<%=tag %>,
	<%} // fine for %>
</li>
<li style="list-style:none">Tipo Contratto: <%=tipoContratto %> </li>
<li style="list-style:none">Data pubblicazione: <%=dataPubb %> </li>
</ul>
<% if(moderatore != null) {

%>
<form action ="${pageContext.request.contextPath}/RimuoviAnnuncioServlet" method="GET">
	<button type="submit" value="Rimuovi annuncio"> Rimuovi Annuncio
	</button>
	<input type="hidden" name="idDaRimuovere" value = "<%=idAnnuncio %>">
</form>
<%}else{ %>
<div style="height:40px">
</div>
<form action="<%=redirect %>" method="GET">
<button style ="margin-left:100px"type="submit" class="umb-btn" value="<%=redirect %>" onsubmit="loginForce()">
Candidati!
</button>
<input type="hidden" name="idUt" value ="<%=idInocc %>">
<input type="hidden" name="idAz" value="<%=idAnnuncio %>">
</form>
<%} %>
</div>
</div>
<div style="height:110px">
</div>
<%@include file ="footer.jsp"%>
<script>
$(document).ready(function(){
	var stringlog ="login.jsp";
	$(".class").click(function(event){
		var buttonID = event.target.value;
		if(buttonID === stringlog){
			alert("Devi essere autenticato come Inoccupato per poterti candidare,ti portiamo alla pagina di login");
		}
	});
});
</script>

</body>
</html>