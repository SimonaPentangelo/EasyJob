<% Inoccupato inoccupatoHeader = (Inoccupato) session.getAttribute("utenteInoccupato");
Azienda aziendaHeader = (Azienda) session.getAttribute("utenteAzienda");
Moderatore moderatoreHeader = (Moderatore) session.getAttribute("utenteModeratore");
Amministratore amministratoreHeader = (Amministratore) session.getAttribute("utenteAdmin");
%>
<%@page import="easyjob.entity.*" %>


<% if(inoccupatoHeader== null && aziendaHeader==null && moderatoreHeader== null && amministratoreHeader == null){ // utente non loggato
%>

    <header>
        <nav class="navbar navbar-expand-md navbar-light bg-dark fixed-top">
            <a class="navbar-brand" href="index.jsp"><h2>EasyJob</h2></a>
            <button class="navbar-toggler border border-0" type="button" data-toggle="collapse"
                data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Accedi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="registrazioneAzienda.jsp">Registrati come azienda</a>
                    </li>
                    <li class="nav-item">
                    	<a class="nav-link" href="registrazioneInoccupato.jsp">Registrati come inoccupato</a>
                    </li>
                    
                </ul>
            </div>
        </nav>
        <!--End Navbar-->
    </header>

<%
}else {
	if(inoccupatoHeader!= null) { // utente inocc

%>

    <header>
        <nav class="navbar navbar-expand-md navbar-light bg-dark fixed-top">
            <a class="navbar-brand" href="index.jsp"><h2>EasyJob</h2></a>
            <button class="navbar-toggler border border-0" type="button" data-toggle="collapse"
                data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <li class="nav-item">
                    <form action="LogoutServlet" method="POST">
                
                        <button type="submit" style=
  						" background-color: Transparent;  border: none; padding-top:6.9px; opacity:50%">Logout</button>
                     
                     </form>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="protectedPageInoccupato.jsp">Pagina Personale</a>
                    </li>
                    
                    
                </ul>
            </div>
        </nav>
        </header>
<%} else{
	if(aziendaHeader!=null){
		// fine if
	%>
	<header>
        <nav class="navbar navbar-expand-md navbar-light bg-dark fixed-top">
            <a class="navbar-brand" href="index.jsp"><h2>EasyJob</h2></a>
            <button class="navbar-toggler border border-0" type="button" data-toggle="collapse"
                data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <li class="nav-item">
                    <form action="LogoutServlet" method="POST">
                
                        <button type="submit" style=
  						" background-color: Transparent;  border: none; padding-top:6.9px; opacity:50%">Logout</button>
                     
                     </form>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="pubblicaAnnuncio.jsp">Pubblica Annuncio</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="protectedPageAzienda.jsp">Pagina Personale</a>
                    </li>
                    
                </ul>
            </div>
        </nav>
        </header>
<% }
	if(moderatoreHeader!=null){
		%>
		<header>
        <nav class="navbar navbar-expand-md navbar-light bg-dark fixed-top">
            <a class="navbar-brand" href="index.jsp"><h2>EasyJob</h2></a>
            <button class="navbar-toggler border border-0" type="button" data-toggle="collapse"
                data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <li class="nav-item">
                    <form action="LogoutServlet" method="POST">
                
                        <button type="submit" style=
  						" background-color: Transparent;  border: none; padding-top:6.9px; opacity:50%">Logout</button>
                     
                     </form>
                    </li>
              
                    <li class="nav-item">
                        <a class="nav-link" href="protectedPageModeratore.jsp">Pagina Personale</a>
                    </li>
                    
                </ul>
            </div>
        </nav>
        </header>
		
	<% 
	}else{
		%>
		<header>
        <nav class="navbar navbar-expand-md navbar-light bg-dark fixed-top">
            <a class="navbar-brand" href="index.jsp"><h2>EasyJob</h2></a>
            <button class="navbar-toggler border border-0" type="button" data-toggle="collapse"
                data-target="#navbarResponsive">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">

                    <li class="nav-item">
                    <form action="LogoutServlet" method="POST">
                
                        <button type="submit" style=
  						" background-color: Transparent;  border: none; padding-top:6.9px; opacity:50%">Logout</button>
                     
                     </form>
                    </li>
                    <li class="nav-item">
                    <form action="${pageContext.request.contextPath }/VisualizzaSegnalazioniServlet" method="GET">
                    <button type="submit" style=
  						" background-color: Transparent;  border: none; padding-top:6.9px; opacity:50%">Elenco Segnalazioni</button>
                        
                     </form>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="protectedPageAdmin.jsp">Pagina Personale</a>
                    </li>
                    
                </ul>
            </div>
        </nav>
        </header>
	<% 
}
}
} // fine else%>



