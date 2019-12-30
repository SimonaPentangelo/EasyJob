<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="easyjob.entity.Annuncio"%>
<%@page import="easyjob.entity.Azienda"%>
<%
	List<Annuncio> annunci = new ArrayList<>();
	annunci = (ArrayList) session.getAttribute("annunci");
	String tagDellaRicerca =(String) session.getAttribute("tag");
	List<Azienda> aziende = (ArrayList) session.getAttribute("aziendeAnnunci");
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


</head>
<body>
<% if (annunci.isEmpty()) {%>
<p> Non sono stati trovati annunci</p>

<%} else{

%>
<form action="${pageContext.request.contextPath}/FiltraAnnunciServlet" method="GET">
	 <input type="text" name="advancedSearch">
	 <input type="hidden" name="tag" value="<%=tagDellaRicerca %>">
	 <input type="submit" value="Cerca per città">
</form>
<% 	
for (int i=0;i<annunci.size();i++){
	int id = annunci.get(i).getIdAnnuncio();
	String titolo = annunci.get(i).getTitolo();
	int idAzienda = annunci.get(i).getAzienda();
	String pathImage = "";
	for (int j=0;j<aziende.size();j++){
		if(aziende.get(j).getIdUser() == idAzienda)
		 pathImage = File.separator + aziende.get(j).getLogoAzienda();
	}

%>
<br>
<p><a href="../../LeggiAnnuncioServlet?idAnnuncio=<%=id %>"><%=titolo %></a>  </p>
<img src="${pageContext.request.contextPath}<%=pathImage%>"> 
<p> Debugging <%=pathImage %></p>
<br>
<%
	} // fine for
}  // fine else
%>

</body>
</html>