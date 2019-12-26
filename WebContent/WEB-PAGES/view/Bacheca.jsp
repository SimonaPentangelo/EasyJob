<%@page import="java.util.*"%>
<%@page import="easyjob.entity.Annuncio"%>
<%
	List<Annuncio> annunci = new ArrayList<>();
	annunci = (ArrayList) session.getAttribute("annunci");
	String tagDellaRicerca =(String) session.getAttribute("tag");
	
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
<form action="../../FiltraAnnunciServlet" method="GET">
	 <input type="text" name="advancedSearch">
	 <input type="hidden" name="tag" value="<%=tagDellaRicerca %>">
	 <input type="submit" value="Cerca per città">
</form>
<% 	
for (int i=0;i<annunci.size();i++){
	String titolo = annunci.get(i).getTitolo();

%>
<br>
<p> <%=titolo %> </p>
<br>
<%
	} // fine for
}  // fine else
%>

</body>
</html>