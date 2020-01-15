<% String errorFormat = (String) session.getAttribute("errore"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Pubblica Annuncio</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="../../JS-SCRIPT/controlliPubblicaAnnuncio.js"></script>
</head>
<body>
<%@include file ="header.jsp"%>
	<form action="${pageContext.request.contextPath}/PubblicaAnnuncioServlet" method="POST">
	Titolo: <input id="titolo" onchange="checkTitolo()" type="text" name="titolo"> 
	<span id="errorTitolo"></span><br>
	
	Descrizione: <input id="desc" onchange="checkDesc()" type="text" name="descrizione">
	<span id="errorDescrizione"></span><br>
	
	Requisiti: <input id="req" onchange="checkReq()" type="text" name="requisiti"> 
	<span id="errorReq"></span><br>
	
	Tipo contratto: 
	<select id="cont" onchange="checkContratto()" name="tcontratto">
  		<option value="full-time">Full-time</option>
  		<option value="part-time">Part-time</option>
  		<option value="apprendistato">Apprendistato</option>
  		<option value="progetto">Progetto</option>
  		<option value="tirocinio">Tirocinio</option>
  		<option value="stagista"> Stagista </option>
	</select>
	<span id="errorTipo"></span><br>
	
	Città: <input id="citta" onchange="checkCitta()" type="text" name="city"> 
	<span id="errorCitta"></span><br>
	
	Tags: <input id="tags" onchange="checkTags()" type="text" name= "tags" placeholder="Inserisci uno o più tag susseguiti dalla virgola"> 
	<span id="errorTags"></span><br>
	<%if (errorFormat!=null){
		%><p> <%=errorFormat %></p>
	<%} %>
	
	<input id="conferma" type="submit" value="Pubblica Annuncio">
	</form>
<%@include file ="footer.jsp"%>
</body>
</html>