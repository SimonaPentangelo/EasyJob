<!-- Questo header è quello di tutte le pagine,tranne index, quando non si è loggati -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    
    	<link rel="stylesheet" type="text/css" href="css/responsabbbile.css">

    <title>Document</title>
    
</head>

<body>

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
        <!--End Navbar-->
    </header>
    
   
</body>

</html>