/**
 * 
 */

/**
 * 
 */

function checkAll() {
	
	if(!checkNomeAzienda()) {
		return false;
	} else if(!checkLogo()) {
		return false;
	} else if(!checkPIva()) {
		return false;
	} else if(!checkUsername()) {
		return false;
	} else if(!checkIndirizzo()) {
		return false;
	} else if(!checkDataFondazione())  {
		return false;
	} else if(!checkDescrizione()) {
		return false
	} else if(!checkDipendenti()) {
		return false;
	} else if(!checkEmail()) {
		return false;
	} else if(!checkPass()) {
		return false;
	} else if(!checkTrattamentoDati()) {
		return false;
	} else {
		console.log("CIAOOOO");
		var form = $('#regAz')[0]; 
		var formData = new FormData(form);
		
		$.ajax({
		    url: "../../RegistrazioneAziendaServlet",
		    enctype: 'multipart/form-data',
		    data: formData,
		    type: 'POST',
		    contentType: false, 
		    processData: false,
		    async : false,
		    success: function(data){
	             //codice per la redirect
	         }
		});
		return true;
	}
}

function checkDescrizione() {
	   var desc = $("#desc");
	    var StringValidator = /^[A-Za-z\é\è\ò\à\ù\ì\ .,!?']{10,500}$/;

	    if ($(desc).val().match(StringValidator) || $(desc).val().trim() == "") {
	    	$("#errorDesc").hide();
	    	console.log("ok desc");
	    	return true;
	    } else {
	    	$("#errorDesc").html("La descrizione deve essere di almeno 10 caratteri " +
	    						"e non deve superare i 500 caratteri.");
	    	$("#errorDesc").show();
	        return false;
	    }	
}

function checkNomeAzienda() {
	var nomeAz = $("#nomeAzienda");
    var StringValidator = /^[A-Za-z0-9-._ ]{5,50}$/;

    if ($(nomeAz).val().match(StringValidator) || $(nomeAz).val().trim() == "") {
    	$("#errorNome").hide();
    	console.log("ok nome az");
        return true;
    } else {
    	$("#errorNome").html("Il nome deve contenere minimo 6 caratteri e massimo 30.");
		$("#errorNome").show();
        return false;
    }
}

function checkPIva() {
	var piva = $("#piva");
    var StringValidator = /^[A-Z0-9]{11,11}$/;

    if ($(piva).val().match(StringValidator) || $(piva).val().trim() == "") {
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
	var dip = $("#dip");
	var numbers = /^[0-9]+$/;
	if(dip.val() > 0 && dip.val().match(numbers)) {
		$("#errorDip").hide();
		console.log("ok dip");
        return true;
	} else {
		$("#errorDip").html("Il campo può contenere solo cifre.");
 		$("#errorDip").show();
 		return false;
	}
}

function checkIndirizzo() {
	 var indirizzo = $("#indirizzo");
	 var StringValidator = /^[A-Za-z ]{3,6}[A-Za-z ]{2,35}[,]{1}[0-9 ]{2,5}$/;

     if ($(indirizzo).val().match(StringValidator) || $(indirizzo).val().trim() == "") {
    	$("#errorIndirizzo").hide();
    	console.log("ok index");
        return true;
     } else {
    	$("#errorIndirizzo").html("L’indirizzo non rispetta il formato.");
 		$("#errorIndirizzo").show();
	    return false;
     }
}

function checkDataFondazione() {
    var data = $("#dataFond");
    var oggi = new Date();
    var nascita = new Date($(data).val());
    if (nascita.getFullYear().toString().length >= 4) 
    {
        if (oggi > nascita) 
        {
        	$("#errorData").hide();
        	console.log("ok data");
            return true;
        } 
        else
        {
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
    	console.log("ok mail");
        return true;
    } else {
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
    	console.log("ok username");
        return true;
    } else {
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
        	console.log("ok pass");
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

function checkLogo() {
	var logo = $("#logo");
	var name = logo.val();
	var ext = name.substring(name.length - 3);
    if (ext == "jpg" || ext == "png") {
    	$("#errorLogo").hide();
    	console.log("ok logo");
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
		console.log("ok check");
		return true;
	} else {
		$("#errorCheck").html("E’ obbligatorio spuntare la casella del trattamento dati.");
		$("#errorCheck").show();
		return false;
	}
}

