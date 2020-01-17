<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file ="librerie.html"%>
<meta charset="ISO-8859-1">
<title>Registrazione Inoccupato</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="./JS-SCRIPT/controlliRegistrazioneInoccupato.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<link rel="stylesheet" type="text/css" href="css/umbtn.css">
<link rel ="stylesheet" type="text/css" href="css/header.css">
<link rel ="stylesheet" type="text/css" href="css/responsabbbile.css">
<link rel ="stylesheet" type="text/css" href="css/registerform.css">
</head>
<body>
<%@include file ="header.jsp"%>

<div class="maremma">

		<h1>Registrati ora e crea un account</h1>
		<%
		String stringa = "";
		if(response.getHeader("errorReg") != null) {
			stringa = response.getHeader("errorReg");
		} 
		%>
		<span><%=stringa %></span>
	<form enctype='multipart/form-data' action="${pageContext.request.contextPath}/RegistrazioneInoccupatoServlet" method="POST">
	
	<div class="row">

		<div class="column">
		<div>
		Nome: <input class="instesto" id="nome" onchange="checkNome()" type="text" name="nome">
		<span class="spanno" id="errorNome"></span><br><br>
		</div>
		
		<div>
		Cognome: <input class="instesto" id="cognome" onchange="checkCognome()" type="text" name="cognome">
		<span id="errorCognome" class="spanno"></span><br><br>
		</div>
		
		<div>
		Città: <input class="instesto" id="citta" onchange="checkCitta()" type="text" name="cittaNascita">
		<span class="spanno" id="errorCitta"></span><br><br><br>
		</div>
		
		<div>
		Residenza: <input class="instesto" id="residenza" onchange="checkResidenza()" type="text" name="residenza">
		<span class="spanno" id="errorResidenza"></span><br><br>
		</div>
		
		<div>
		Data di Nascita: <input class="instesto" id="dataNascita" onchange="checkDataNascita()" type="date" name="dataNascita">
		<span id="errorData" class="spanno"></span><br>
		</div>
		</div>
		
		<div class="column">
		<div>
		e-mail: <input class="instesto" id="email" onchange="checkEmail()" type="email" name="email">
		<span class="spanno" id="errorMail"></span><br><br>
		</div>
		
		<div>
		Username: <input class="instesto" id="username" onchange="checkUsername()" type="text" name="username"> 
		<span class="spanno" id="errorUser"></span><br><br>
		</div>
		
		<div>
		Password: <input class="instesto" id="pass" onchange="checkPass()" type="password" name="password">
		<span class="spanno" id="errorPass"></span><br><br>
		</div>
		
		<div>
		Conferma Password: <input class="instesto" id="confPass" onchange="checkPass()" type="password" name="confermaPassword">
		<span class="spanno" id="errorConfPass"></span><br><br>
		</div>
		
		<div id="cvDiv">
		<p id="cv">Curriculum:</p>
		<span id="fileSpan" class="fileSpan"></span>
		<label class="umb-btn" for="curriculum">Scegli file</label>
		<input id="curriculum" class="intestoLogo" onchange="checkCurriculum()" type="file" name="curriculum">
		<span class="spanno" id="errorCurriculum"></span><br><br>
		</div>

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