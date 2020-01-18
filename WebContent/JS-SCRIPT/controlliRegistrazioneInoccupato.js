/**
 * 
 */

$('#curriculum').on('change', function(){
$('.fileSpan').html('');
$('.fileSpan').html(document.getElementById("curriculum").files[0].name)
}); 

$('#logoAzienda').on('change', function(){
  $('.fileSpan').html('');
  
  $('.fileSpan').html(document.getElementById("curriculum").files[0].name)
  
});

function checkNome() {
    var nome = $("#nome").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 ]{2,50}$/;

    if (nome.match(StringValidator) && nome != null) {
    	$("#errorNome").hide();
    	
    	return true;
    } else {
    	$("#errorNome").html("Il nome deve contenere solo lettere e " +
    						"deve essere composto da 2 a 50 lettere.");
		$("#errorNome").show();
        return false;
    }
}

function checkCognome() {
    var nome = $("#cognome").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 ]{2,50}$/;

    if (nome.match(StringValidator) && nome != null) {
    	$("#errorCognome").hide();
    	
    	return true;
    } else {
    	
    	$("#errorCognome").html("Il cognome deve contenere solo lettere e " +
							"deve essere composto da 2 a 50 lettere.");
    	$("#errorCognome").show();
        return false;
    }
}

function checkCitta() {
    var citta = $("#citta").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9' ]{2,20}$/;

    if (citta.match(StringValidator) && citta != null) {
    	$("#errorCitta").hide();
    	
    	return true;
    } else {
    
    	$("#errorCitta").html("La città deve contenere solo lettere e " +
    			"formata minimo da 2 lettere e massimo da 20.");
    	$("#errorCitta").show();
        return false;
    }
}

function checkResidenza() {
    var residenza = $("#residenza").val();
    var StringValidator = /^[A-Za-z ]{3,6}[A-Za-z\xE0\xE8\xEC\xF2\xF9 ]{2,35}[,]{1}[0-9 ]{2,5}$/;

    if (residenza.match(StringValidator) && residenza != null) {
    	$("#errorResidenza").hide();
    	
    	return true;
    } else {
    	
    	$("#errorResidenza").html("L’indirizzo non rispetta il formato.");
    	$("#errorResidenza").show();
        return false;
    }
}

function checkDataNascita() {
    var data = $("#dataNascita").val();
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
    var confPass = $("#confPass").val();
    var StringValidator = /^[A-Za-z0-9-._]{8,16}$/; 
    
    if (pass.match(StringValidator) && pass != null) {
        if(pass.match(confPass) && confPass != null) {
        	$("#errorPass").hide();
        	$("#errorConfPass").hide();
        	
        	return true;
        } else {
        	
        	$("#errorConfPass").html("Password non corrispondente.");
    		$("#errorConfPass").show();
    		return false;
        }
    } else {
    	
    	$("#errorPass").html("La password deve contenere minimo 8 caratteri e massimo 16.");
		$("#errorPass").show();
    	return false;
    }
}

function checkCurriculum() {
	var curr = $("#curriculum");
	var name = curr.val();
	var ext = name.substring(name.length - 3);
    if (name != null && ext == "pdf") {
    	var sizeInMB = curr[0].size / Math.pow(1024,2)
        if(sizeInMB <= 10.00) {
        	$("#errorCurriculum").hide();
        	
        	return true;
        } else {
        	
        	$("#errorCurriculum").html("La dimensione non deve superare i 10MB.");
    		$("#errorCurriculum").show();
        	return false;
        }
    } else {
    	
    	$("#errorCurriculum").html("Il file deve essere in formato PDF.");
		$("#errorCurriculum").show();
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
	if(!checkNome() || !checkCognome() || !checkCitta() || !checkResidenza() || !checkDataNascita() || !checkEmail() || !checkUsername() || !checkPass() || !checkCurriculum() || !checkTrattamentoDati()) {
		
		return false;
	} else {
		
		return true;
	}
}

