/**
 * 
 */

/**
 * 
 */

function checkAll() {
	
	if(!checkNomeAzienda()) {
		alert("ERRORE!");
		return false;
	} else if(!checkLogo()) {
		alert("ERRORE!");
		return false;
	} else if(!checkPIva()) {
		alert("ERRORE!");
		return false;
	} else if(!checkUsername()) {
		alert("ERRORE!");
		return false;
	} else if(!checkIndirizzo()) {
		alert("ERRORE!");
		return false;
	} else if(!checkDataFondazione())  {
		alert("ERRORE!");
		return false;
	} else if(!checkDescrizione()) {
		alert("ERRORE!");
		return false
	} else if(!checkDipendenti()) {
		alert("ERRORE!");
		return false;
	} else if(!checkEmail()) {
		alert("ERRORE!");
		return false;
	} else if(!checkPass()) {
		alert("ERRORE!");
		return false;
	} else if(!checkTrattamentoDati()) {
		alert("ERRORE!");
		return false;
	} else {
		$.post("../../src/easyjob/control/gestione_utenti/RegistrazioneAziendaServlet");
	}
}

function checkDescrizione() {
   
}

function checkNomeAzienda() {
	
}

function checkPIva() {

}

function checkIndirizzo() {

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

function checkLogo() {

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

