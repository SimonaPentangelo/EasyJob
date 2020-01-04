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
   
}

function checkNomeAzienda() {
	
}

function checkPIva() {

}

function checkIndirizzo() {
	 var indirizzo = $("#indirizzo");
	 var StringValidator = /^[A-Za-z ]{3,6}[A-Za-z ]{2,35}[,]{1}[0-9 ]{2,5}$/;

     if ($(indirizzo).val().match(StringValidator) || $(indirizzo).val().trim() == "") {
    	$("#errorIndirizzo").hide();
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
    var minDate = new Date(oggi.getFullYear() - 120, oggi.getMonth(), oggi.getDay());
    if (nascita.getFullYear().toString().length >= 4) 
    {
        if (oggi <= nascita || minDate >= nascita) 
        {
        	$("#errorData").hide();
            return false;
        } 
        else
        {
        	$("#errorData").html("La data inserita non rispetta il formato.");
    		$("#errorData").show();
        	return true;
        }
    }
}

function checkEmail() {
    var email = $("#email");
    var StringValidator = /^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\.[a-zA-Z]{2,3}$/;

    if ($(email).val().match(StringValidator) || $(email).val().trim() == "") {
    	$("#errorMail").hide();
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
		$("#errorCheck").html("E’ obbligatorio spuntare la casella del trattamento dati.");
		$("#errorCheck").show();
		return false;
	}
}

