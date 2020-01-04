/**
 * 
 */

function checkAll() {

	
	if(!checkNome()) {
		return false;
	} else if(!checkCognome()) {
		return false;
	} else if(!checkCitta()) {
		return false;
	} else if(!checkResidenza()) {
		return false;
	} else if(!checkDataNascita()) {
		return false;
	} else if(!checkEmail())  {
		return false;
	} else if(!checkUsername()) {
		return false
	} else if(!checkPass()) {
		return false;
	} else if(!checkCurriculum()) {
		return false;
	} else if(!checkTrattamentoDati()) {
		return false;
	} else {
		var form = $('#regInocc')[0]; 
		var formData = new FormData(form);
		
		$.ajax({
		    url: "../../RegistrazioneInoccupatoServlet",
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

function checkNome() {
    var nome = $("#nome");
    var StringValidator = /^[A-Za-z ]{2,50}$/;

    if ($(nome).val().match(StringValidator) || $(nome).val().trim() == "") {
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
    var nome = $("#cognome");
    var StringValidator = /^[A-Za-z ]{2,50}$/;

    if ($(cognome).val().match(StringValidator) || $(cognome).val().trim() == "") {
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
    var citta = $("#citta");
    var StringValidator = /^[A-Za-z' ]{2,20}$/;

    if ($(citta).val().match(StringValidator) || $(citta).val().trim() == "") {
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
    var residenza = $("#residenza");
    var StringValidator = /^[A-Za-z ]{3,6}[A-Za-z ]{2,35}[,]{1}[0-9 ]{2,5}$/;

    if ($(residenza).val().match(StringValidator) || $(residenza).val().trim() == "") {
    	$("#errorResidenza").hide();
    	return true;
    } else {
    	$("#errorResidenza").html("L’indirizzo non rispetta il formato.");
    	$("#errorResidenza").show();
        return false;
    }
}

function checkDataNascita() {
    var data = $("#dataNascita");
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

function checkCurriculum() {
	var curr = $("#curriculum");
	var name = curr.val();
	var ext = name.substring(name.length - 3);
    if (ext == "pdf") {
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
		$("#errorCheck").html("E’ obbligatorio spuntare la casella del trattamento dati.");
		$("#errorCheck").show();
		return false;
	}
}

