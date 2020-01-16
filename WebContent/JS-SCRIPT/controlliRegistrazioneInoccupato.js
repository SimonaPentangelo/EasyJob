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
    var nome = $("#nome");
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 ]{2,50}$/;

    if ($(nome).val().match(StringValidator) || $(nome).val().trim() == "") {
    	$("#errorNome").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorNome").html("Il nome deve contenere solo lettere e " +
    						"deve essere composto da 2 a 50 lettere.");
		$("#errorNome").show();
        return false;
    }
}

function checkCognome() {
    var nome = $("#cognome");
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 ]{2,50}$/;

    if ($(cognome).val().match(StringValidator) || $(cognome).val().trim() == "") {
    	$("#errorCognome").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorCognome").html("Il cognome deve contenere solo lettere e " +
							"deve essere composto da 2 a 50 lettere.");
    	$("#errorCognome").show();
        return false;
    }
}

function checkCitta() {
    var citta = $("#citta");
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9' ]{2,20}$/;

    if ($(citta).val().match(StringValidator) || $(citta).val().trim() == "") {
    	$("#errorCitta").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorCitta").html("La città deve contenere solo lettere e " +
    			"formata minimo da 2 lettere e massimo da 20.");
    	$("#errorCitta").show();
        return false;
    }
}

function checkResidenza() {
    var residenza = $("#residenza");
    var StringValidator = /^[A-Za-z ]{3,6}[A-Za-z\xE0\xE8\xEC\xF2\xF9 ]{2,35}[,]{1}[0-9 ]{2,5}$/;

    if ($(residenza).val().match(StringValidator) || $(residenza).val().trim() == "") {
    	$("#errorResidenza").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorResidenza").html("L’indirizzo non rispetta il formato.");
    	$("#errorResidenza").show();
        return false;
    }
}

function checkDataNascita() {
    var data = $("#dataNascita");
    var oggi = new Date();
    var nascita = new Date($(data).val());
    if (nascita.getFullYear().toString().length >= 4) 
    {
        if (oggi > nascita) 
        {
        	$("#errorData").hide();
        	if(!$("#conferma").is(":disabled")) {
        		$("#conferma").attr("disabled", false);
        	}
            return true;
        } 
        else
        {
        	$("#conferma").attr("disabled", true);
        	$("#errorData").html("La data inserita non rispetta il formato.");
    		$("#errorData").show();
        	return false;
        }
    }
}

function checkEmail() {
    var email = $("#email");
    var StringValidator = /^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\.[a-zA-Z]{2,3}$/;

    if ($(email).val().match(StringValidator) || $(email).val().trim() == "") {
    	$("#errorMail").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorMail").html("Il formato non è valido.");
		$("#errorMail").show();
        return false;
    }
}

function checkUsername() {
	var us = $("#username");
    var StringValidator = /^[A-Za-z0-9]{5,20}$/;

    if ($(us).val().match(StringValidator) || $(us).val().trim() == "") {
    	$("#errorUser").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorUser").html("L’username deve essere composto da 2 a 50 numeri e lettere.");
		$("#errorUser").show();
        return false;
    }
}

function checkPass() {
    var pass = $("#pass");
    var confPass = $("#confPass");
    var StringValidator = /^[A-Za-z0-9-._]{8,16}$/; 
    
    if ($(pass).val().match(StringValidator) || $(pass).val().trim() == "") {
        if($(pass).val().match($(confPass).val())) {
        	$("#errorPass").hide();
        	$("#errorConfPass").hide();
        	if(!$("#conferma").is(":disabled")) {
        		$("#conferma").attr("disabled", false);
        	}
        	return true;
        } else {
        	$("#conferma").attr("disabled", true);
        	$("#errorConfPass").html("Password non corrispondente.");
    		$("#errorConfPass").show();
    		return false;
        }
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorPass").html("La password deve contenere minimo 8 caratteri e massimo 16.");
		$("#errorPass").show();
    	return false;
    }
}

function checkCurriculum() {
	var curr = $("#curriculum");
	var name = curr.val();
	var ext = name.substring(name.length - 3);
    if (ext == "pdf") {
    	var sizeInMB = curr[0].size / Math.pow(1024,2)
        if(sizeInMB <= 10.00) {
        	$("#errorCurriculum").hide();
        	if(!$("#conferma").is(":disabled")) {
        		$("#conferma").attr("disabled", false);
        	}
        	return true;
        } else {
        	$("#conferma").attr("disabled", true);
        	$("#errorCurriculum").html("La dimensione non deve superare i 10MB.");
    		$("#errorCurriculum").show();
        	return false;
        }
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorCurriculum").html("Il file deve essere in formato PDF.");
		$("#errorCurriculum").show();
        return false;
    }
}

function checkTrattamentoDati() {
	var checked = $("#dati").is(":checked");
	if(checked) {
		$("#errorCheck").hide();
		if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
		return true;
	} else {
		$("#conferma").attr("disabled", true);
		$("#errorCheck").html("E’ obbligatorio spuntare la casella del trattamento dati.");
		$("#errorCheck").show();
		return false;
	}
}

