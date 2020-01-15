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
    
   
</body>

</html>