<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Registrazione Azienda</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="./JS-SCRIPT/controlliRegistrazioneAzienda.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
<link rel ="stylesheet" type="text/css" href="css/header.css">
<link rel ="stylesheet" type="text/css" href="css/responsabbbile.css">
<link rel ="stylesheet" type="text/css" href="css/registerform.css">
<link rel ="stylesheet" type="text/css" href="css/publish.css">
</head>
<body>
<%@include file ="header.jsp"%>

<div class="maremma">

		<h1>Registrati ora e crea un account</h1>
		<%
		String stringa = "";
		if(request.getAttribute("errorReg") != null) {
			stringa = request.getAttribute("errorReg").toString();
			%> 	<h1><%=stringa %></h1> <%
		} 
		%>
	<form onsubmit="return checkAll()" enctype='multipart/form-data' action="${pageContext.request.contextPath}/RegistrazioneAziendaServlet" method="POST">
	
	<div class="row">

		<div class="column">
		<div>
		Nome Azienda: <input class="instesto" id="nomeAzienda" type="text" name="nomeAzienda"> 
		</div>
		<span class="spanno" id="errorNome"></span><br><br>
		
		<div id="logoDiv">
		<p id="logo">Logo:</p>
		<span id="fileSpan" class="fileSpan"></span>
		<label class="umb-btn" for="logoAzienda">Scegli file</label>
		<input class="intestoLogo" id="logoAzienda" type="file" accept="image/*" name="logoAzienda"> 
		</div>
		<span class="spanno" id="errorLogo"></span><br><br>
	
		<div>
		Partita IVA: <input class="instesto" id="piva" type="text" name="partitaIVA"> 
		</div>
		<span class="spanno" id="errorIVA"></span><br><br>
	
		<div>
		Username: <input class="instesto" id="username" type="text" name="username"> 
		</div>
		<span class="spanno" id="errorUser"></span><br><br>
	
		<div>
		Indirizzo Sede: <input class="instesto" id="indirizzo" type="text" name="indirizzoSede"> 
		</div>
		<span class="spanno" id="errorIndirizzo"></span><br><br>
	
	</div>
	<div class="column">
	
		<div>
		Data Fondazione: <input class="instesto" id="dataFond" type="date" name="dataFondazione"> 
		</div>
		<span class="spanno" id="errorData"></span><br><br>
		
		<div>
		Numero Dipendenti: <input class="instesto" id="dip" type="text" name="numeroDipendenti"> 
		</div>
		<span class="spanno" id="errorDip"></span><br><br>
	
		<div>
		e-mail: <input class="instesto" id="email" type=email" name="email"> 
		</div>
		<span class="spanno" id="errorMail"></span><br><br>
	
		<div>
		Password: <input class="instesto" id="pass" type="password" name="password"> 
		</div>
		<span class="spanno" id="errorPass"></span><br><br>
	
		<div>
		Conferma Password: <input class="instesto" id="confPass" type="password" name="confermaPassword"> 
		</div>
		<span clas="spanno" id="errorConfPass"></span><br><br>
	
		<div id="checkDiv">
		<input id="dati" type="checkbox" name="trattamentoDati"> Accetto i termini del 
		trattamento dati personali.
		</div> 
		<span class="spanno" id="errorCheck"></span><br><br>
	
	</div>
	</div>
		<input id="conferma" class="umb-btn" type="submit" value="Conferma Registrazione">
	</form>	
</div>
<%@include file ="footer.jsp"%>
</body>
</html>