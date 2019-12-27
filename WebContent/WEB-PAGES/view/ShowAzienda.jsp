<%
Inoccupato inoccupato = (Inoccupato) session.getAttribute("utenteInoccupato");
Annuncio annuncioSel = (Annuncio) session.getAttribute("annuncioSelezionato");
String titolo = annuncioSel.getTitolo();
String descrizine = annuncioSel.getTitolo();
String requisiti = annuncioSel.getRequisiti();
List<String> tags = annuncioSel.getTags();
String tipoContratto = annuncioSel.getTipoContratto();
String dataPubb = annuncioSel.getData();
String azienda = annuncioSel.get        // Ci vorrebbe il nome azienda qui
%>
<%@ page import ="easyjob.entity.Inoccupato" %>
<%@ page import ="easyjob.entity.Annuncio" %>
<%@ page import ="java.util.*;" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


</body>
</html>