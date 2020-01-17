<% String errorFormat = "";
if(response.getHeader("errore") != null) {
	errorFormat = response.getHeader("errore").toString(); }%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Pubblica Annuncio</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="./JS-SCRIPT/controlliPubblicaAnnuncio.js"></script>
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
<link rel ="stylesheet" type="text/css" href="css/header.css">
<link rel ="stylesheet" type="text/css" href="css/responsabbbile.css">
<link rel ="stylesheet" type="text/css" href="css/registerform.css">
<link rel ="stylesheet" type="text/css" href="css/publish.css">
</head>
<body>
<%@include file ="header.jsp"%>

<div class="maremma">

<h1>Pubblica un annuncio</h1>


<%if (errorFormat!=null){
			%><h3><%=errorFormat %></h3>
		<%} %>
	<form onsubmit="return checkAll()" action="${pageContext.request.contextPath}/PubblicaAnnuncioServlet" method="POST">
	<div class="row">

		<div class="column">
		<div>
		Titolo: <input class="instesto" id="titolo" type="text" name="titolo"> 
		<span class="spanno" id="errorTitolo"></span><br><br>
		</div>
		
		<div>
		Città: <input class="instesto" id="citta" type="text" name="city"> 
		<span id="errorCitta"></span><br><br>
		</div> 
		</div>
		
		<div class="column">
		<div>
		Tipo contratto: 
		<select class="instesto" id="cont" name="tcontratto">
		<option value="iniziale">--Seleziona un tipo--</option>
  		<option value="full-time">Full-time</option>
  		<option value="part-time">Part-time</option>
  		<option value="apprendistato">Apprendistato</option>
  		<option value="progetto">Progetto</option>
  		<option value="tirocinio">Tirocinio</option>
  		<option value="stagista"> Stagista </option>
		</select>
		<span class="spanno"  id="errorTipo"></span><br><br>
		</div>
		
		<div>
		Tags: <input class="instesto" id="tags" type="text" name= "tags" placeholder="Inserisci uno o più tag susseguiti dalla virgola"> 
		<span class="spanno"  id="errorTags"></span><br><br>

		</div>
		
		</div>
		</div>
		
		<div>
		<span>Descrizione:</span><br> <textarea class="bigText" id="desc" onchange="checkDesc()" name="descrizione"></textarea> 
		<br>
		<span class="spanno2" id="errorDescrizione"></span><br><br>
		</div> 
		
		<div>
		<span>Requisiti:</span><br> <textarea class="bigText" id="req" onchange="checkReq()" name="requisiti"></textarea> 
		<br>
		<span class="spanno2" id="errorReq"></span><br><br>
		</div>
		
	<input id="conferma" class="umb-btn" type="submit" value="Pubblica Annuncio">
	</form>
	
</div>
<%@include file ="footer.jsp"%>
</body>
</html>