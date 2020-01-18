<% String msg = "";
if(request.getAttribute("message") != null) { request.getAttribute("message").toString(); } %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@include file ="librerie.html"%>
 <link rel="stylesheet" href="css/login.css">
</head>
<body>
<%@include file ="header.jsp"%>
<div class="wrap cont-centred">
        <div class="login">
        <h2>Accedi</h2>
	<form action ="${pageContext.request.contextPath}/LoginServlet" method="POST">
	 <div class="form-input">
	 <label for="user"><b>Username:</b></label>
	 <input type="text" name="username" placeholder="Enter username">
	 <label for="pass"><b>Password:</b></label>
	<input type="password" name="password" placeholder="Enter password"> <br>
	<% if(msg!=null && !msg.equals("")){
		%>
	<p> <%=msg %></p>
	<%} %>
	<button class="umb-btn" type="submit">Login</button>
	
	 <hr>
	 
	 <p class="text"> Non ti sei ancora registrato? <br> Crea qui il tuo account da inoccupato: <span><a href="registrazioneInoccupato.jsp" class="signInLink">Registrati</a> </span>
	</div>
	</form>
</div>
</div>	
	<%@include file ="footer.jsp"%>
</body>
</html>