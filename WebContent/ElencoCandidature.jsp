<% 
ArrayList<Candidatura> elenco = (ArrayList<Candidatura>) session.getAttribute("candidature");
if(elenco== null){
	System.out.println("è null");
	elenco = new ArrayList<>();
}
ArrayList<Annuncio> elencoAn = (ArrayList<Annuncio>) session.getAttribute("elencoAnn");
if(elencoAn==null){
System.out.println("anche qusto è null");
}%>
<%@page import="easyjob.entity.Inoccupato" %>
<%@page import="easyjob.entity.Candidatura" %>
<%@page import="java.util.*" %>
<%@page import="easyjob.entity.Annuncio" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file ="librerie.html"%>
<link rel="stylesheet" type="text/css" href="css/responsabbbile.css">
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
<link rel="stylesheet" type="text/css" href="css/elencoSegnalazioni.css">
</head>
<body>
<%@include file ="header.jsp"%>
<div id="contain" style="margin-top:130px">
<p> Ciao , ecco l'elenco delle tue candidature:</p>
<%if(elenco.size()==0){
%>

<p>Non hai ancora effettuato una candidatura, puoi candidarti attraverso la nostra bacheca </p>
<button id="conferma" class="umb-btn" style="margin-left:695px ;margin-top:160px"><a href="Bacheca.jsp">Vai alla bacheca</a></button>
<div class="myRow">
<div class="myCol">
<% } else{
	for(int i=0;i<elenco.size();i++){
		for(int j=0;j<elencoAn.size();j++){
			if(elenco.get(i).getAnnuncio()==elencoAn.get(j).getIdAnnuncio()){
				String tit = elencoAn.get(j).getTitolo();
				String dat = elenco.get(i).getData();
				%>
				<p> <%=tit %>   <%=dat %></p>
	<% 		}
		}
	}
}
	%>
</div>
</div>
</div>
<div style="height:70px"></div>
	<%@include file ="footer.jsp"%>
</body>
</html>