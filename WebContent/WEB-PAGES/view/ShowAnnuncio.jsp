<%
Inoccupato inoccupato = (Inoccupato) session.getAttribute("utenteInoccupato");
Annuncio annuncioSel = (Annuncio) session.getAttribute("annuncioSelezionato");
String titolo = annuncioSel.getTitolo();
String descrizione = annuncioSel.getTitolo();
String requisiti = annuncioSel.getRequisiti();
List<String> tags = annuncioSel.getTags();
String tipoContratto = annuncioSel.getTipoContratto();
String dataPubb = annuncioSel.getData();
String azienda = annuncioSel.getNomeAzienda();
int idAzienda = annuncioSel.getIdAnnuncio();
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
<%@ page import ="java.util.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<p>Azienda: <%=azienda %></p>
<p>Titolo annuncio: <%=titolo %> </p>
<p>Descrizione: <%=descrizione %> </p>
<p>Requisti: <%= requisiti %> </p>
<p>Tag: 
<% for(int i=0;i<tags.size();i++){
	String tag = tags.get(i);
	%>
	<%=tag %>,
	<%} // fine for %>
</p>
<p>Tipo Contratto: <%=tipoContratto %> </p>
<p>Data pubblicazione: <%=dataPubb %> </p>

<form action="<%=redirect %>" method="GET">
<button type="submit" class="class" value="<%=redirect %>" onsubmit="loginForce()">
Candidati!
</button>
<input type="hidden" name="idUt" value ="<%=idInocc %>">
<input type="hidden" name="idAz" value="<%=idAzienda %>">
</form>

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