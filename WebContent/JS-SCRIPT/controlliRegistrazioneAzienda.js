/**
 * 
 */

/**
 * 
 */

$('#logoAzienda').on('change', function(){
$('.fileSpan').html('');
$('.fileSpan').html(document.getElementById("logoAzienda").files[0].name)
}); 

$('#logoAzienda').on('change', function(){
  $('.fileSpan').html('');
  
  $('.fileSpan').html(document.getElementById("logoAzienda").files[0].name)
  
}); 


function checkDescrizione() {
	   var desc = $("#desc");
	    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?']{10,500}$/;

	    if (desc.match(StringValidator) && desc != null) {
	    	$("#errorDesc").hide();
	    	
	    	return true;
	    } else {
	    	
	    	$("#errorDesc").html("La descrizione deve essere di almeno 10 caratteri " +
	    						"e non deve superare i 500 caratteri.");
	    	$("#errorDesc").show();
	        return false;
	    }	
}

function checkNomeAzienda() {
	var nomeAz = $("#nomeAzienda").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF90-9-._ ]{5,50}$/;

    if (nomeAz.match(StringValidator) && nomeAz != null) {
    	$("#errorNome").hide();
    	
        return true;
    } else {
    	
    	$("#errorNome").html("Il nome deve contenere minimo 6 caratteri e massimo 30.");
		$("#errorNome").show();
        return false;
    }
}

function checkPIva() {
	var piva = $("#piva").val();
    var StringValidator = /^[A-Z0-9]{11,11}$/;

    if (piva.match(StringValidator) && piva != null) {
    	$("#errorIVA").hide();
    	console.log("ok piva");
    	
        return true;
    } else {
    
    	$("#errorIVA").html("La partita IVA deve contenere 11 caratteri.");
		$("#errorIVA").show();
        return false;
    }
}

function checkDipendenti() {
	var dip = $("#dip").val();
	var numbers = /^[0-9]+$/;
	if(dip > 0 && dip.match(numbers) && dip != null) {
		$("#errorDip").hide();
		
        return true;
	} else {
	
		$("#errorDip").html("Il campo pu\xF2 contenere solo cifre.");
 		$("#errorDip").show();
 		return false;
	}
}

function checkIndirizzo() {
	 var indirizzo = $("#indirizzo").val();
	 var StringValidator = /^[A-Za-z ]{3,6}[A-Za-z\xE0\xE8\xEC\xF2\xF9 ]{2,35}[,]{1}[0-9 ]{2,5}$/;

     if (indirizzo.match(StringValidator) && indirizzo != null) {
    	$("#errorIndirizzo").hide();
    
        return true;
     } else {
    	
    	$("#errorIndirizzo").html("L’indirizzo non rispetta il formato.");
 		$("#errorIndirizzo").show();
	    return false;
     }
}

function checkDataFondazione() {
    var data = $("#dataFond").val();
    var oggi = new Date();
    var nascita = new Date(data);
    if (data != null && isValidDate(nascita) && nascita.getFullYear().toString().length >= 4) 
    {
        if (oggi > nascita) 
        {
        	$("#errorData").hide();
        	
            return true;
        } 
        else
        {
       
        	$("#errorData").html("La data inserita non rispetta il formato.");
    		$("#errorData").show();
        	return false;
        }
    } else {
    	$("#errorData").html("La data inserita non rispetta il formato.");
		$("#errorData").show();
    	return false;
    }
}

function isValidDate(d) {
	  return d instanceof Date && !isNaN(d);
}

function checkEmail() {
    var email = $("#email").val();
    var StringValidator = /^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\.[a-zA-Z]{2,3}$/;

    if (email.match(StringValidator) && email != null) {
    	$("#errorMail").hide();
    	
        return true;
    } else {
    	
    	$("#errorMail").html("Il formato non \xE8 valido.");
		$("#errorMail").show();
        return false;
    }
}

function checkUsername() {
	var us = $("#username").val();
    var StringValidator = /^[A-Za-z0-9]{5,20}$/;

    if (us.match(StringValidator) && us != null) {
    	$("#errorUser").hide();
    	
        return true;
    } else {
   
    	$("#errorUser").html("L\'username deve essere composto da 2 a 50 numeri e lettere.");
		$("#errorUser").show();
        return false;
    }
}

function checkPass() {
    var pass = $("#pass").val();
    var StringValidator = /^[A-Za-z0-9-._]{8,16}$/; 
    
    if (pass.match(StringValidator) && pass != null) {
        	$("#errorPass").hide(); 	
        	return true;
    } else {
    	
    	$("#errorPass").html("La password deve contenere minimo 8 caratteri e massimo 16.");
		$("#errorPass").show();
    	return false;
    }
}

function checkConfPass() {
	 var pass = $("#pass").val();
	 var confPass = $("#confPass").val();
	 
	 if(pass === confPass && confPass != null) {
     	$("#errorConfPass").hide();
     	
     	return true;
     } else {
     	$("#errorConfPass").html("Password non corrispondente.");
 		$("#errorConfPass").show();
 		return false;
     }
}

function checkLogo() {
	var logo = $("#logoAzienda");
	var name = logo.val();
	var ext = name.substring(name.length - 3);

    if (name != null && (ext == "jpg" || ext == "png")) {
    	$("#errorLogo").hide();
    
       	return true;
    } else {
    	
    	$("#errorLogo").html("Il formato dell’immagine deve essere o png o jpg.");
		$("#errorLogo").show();
        return false;
    }
}

function checkTrattamentoDati() {
	var checked = $("#dati").is(":checked");
	if(checked) {
		$("#errorCheck").hide();
		
		return true;
	} else {

		$("#errorCheck").html("\xE8 obbligatorio spuntare la casella del trattamento dati.");
		$("#errorCheck").show();
		return false;
	}
}

function checkAll() {
	if(!checkNomeAzienda() || !checkLogo() || !checkPIva() || !checkUsername() || !checkIndirizzo() || !checkDataFondazione() || !checkDipendenti() || !checkEmail() || !checkPass() || !checkConfPass() || !checkTrattamentoDati()) {
		
		return false;
	} else {
		
		return true;
	}
}
	

