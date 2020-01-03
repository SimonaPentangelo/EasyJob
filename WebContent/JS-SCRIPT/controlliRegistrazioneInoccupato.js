/**
 * 
 */

function checkAll() {
	
	if(!checkNome()) {
		alert("ERRORE!");
		return false;
	} else if(!checkCognome()) {
		alert("ERRORE!");
		return false;
	} else if(!checkCitta()) {
		alert("ERRORE!");
		return false;
	} else if(!checkResidenza()) {
		alert("ERRORE!");
		return false;
	} else if(!checkDataNascita()) {
		alert("ERRORE!");
		return false;
	} else if(!checkEmail())  {
		alert("ERRORE!");
		return false;
	} else if(!checkUsername()) {
		alert("ERRORE!");
		return false
	} else if(!checkPass()) {
		alert("ERRORE!");
		return false;
	} else if(!checkCurriculum()) {
		alert("ERRORE!");
		return false;
	} else if(!checkTrattamentoDati()) {
		alert("ERRORE!");
		return false;
	} else {
		$.post("../../src/easyjob/control/gestione_utenti/RegistrazioneInoccupatoServlet", function(data) {
			   // The data here represents the answer from the server
			   console.log("Data Loaded: " + data);
			});
		return true;
	}
}

function checkNome() {
    var nome = $("#nome");
    var StringValidator = /^[A-Za-z ]{2,50}$/;

    if ($(nome).val().match(StringValidator) || $(nome).val().trim() == "") {
        return true;
    } else {
        alert("ERRORE NOME");
        return false;
    }
}

function checkCognome() {
    var nome = $("#cognome");
    var StringValidator = /^[A-Za-z ]{2,50}$/;

    if ($(cognome).val().match(StringValidator) || $(cognome).val().trim() == "") {
        return true;
    } else {
        alert("ERRORE COGNOME");
        return false;
    }
}

function checkCitta() {
    var citta = $("#citta");
    var StringValidator = /^[A-Za-z' ]{2,20}$/;

    if ($(citta).val().match(StringValidator) || $(citta).val().trim() == "") {
        return true;
    } else {
        alert("ERRORE CITTà");
        return false;
    }
}

function checkResidenza() {
    var residenza = $("#residenza");
    var StringValidator = /^[A-Za-z ]{3,6}[A-Za-z ]{2,35}[,]{1}[0-9 ]{2,5}$/;

    if ($(residenza).val().match(StringValidator) || $(residenza).val().trim() == "") {
        return true;
    } else {
        alert("ERRORE RESIDENZA");
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
        	alert("DATA NON VALIDA");
            return false;
        } 
        else
        {
        	hideAlert(date);
        	return true;
        }
    }
}

function checkEmail() {
    var email = $("#email");
    var StringValidator = /^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\.[a-zA-Z]{2,3}$/;

    if ($(email).val().match(StringValidator) || $(email).val().trim() == "") {
        return true;
    } else {
        alert("ERRORE EMAIL");
        return false;
    }
}

function checkUsername() {
	var us = $("#username");
    var StringValidator = /^[A-Za-z0-9]{5,20}$/;

    if ($(us).val().match(StringValidator) || $(us).val().trim() == "") {
        return true;
    } else {
        alert("ERRORE USERNAME");
        return false;
    }
}

function checkPass() {
    var pass = $("#pass");
    var confPass = $("#confPass");
    var StringValidator = /^[A-Za-z0-9-._]{8,16}$/; 
    
    if ($(pass).val().match(StringValidator) || $(pass).val().trim() == "") {
        if($(pass).val().match($(confPass).val())) {
        	return true;
        } else {
        	alert("ERRORE CONFERMA PASS");
        }
    } else {
    	alert("ERRORE PASSWORD");
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
        	return true;
        } else {
        	alert("FILE TROPPO GRANDE");
        	return false;
        }
    } else {
        alert("ERRORE FILE CURRICULUM")
        return false;
    }
}

function checkTrattamentoDati() {
	var checked = $("dati").is(":checked");
	if(checked) {
		return true;
	} else {
		alert("CHECK ASSENTE");
		return false;
	}
}

