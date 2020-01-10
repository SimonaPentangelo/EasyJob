/**
 * 
 */


function checkTitolo() {
    var tit = $("#titolo");
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF90-9,. ]{6,50}$/;

    if ($(tit).val().match(StringValidator) || $(tit).val().trim() == "") {
    	$("#errorTitolo").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorTitolo").html("Il titolo deve contenere minimo 5 e massimo 60 numeri e lettere.");
		$("#errorTitolo").show();
        return false;
    }
}

function checkReq() {
    var req = $("#req");
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?']{10,3000}$/;

    if ($(req).val().match(StringValidator) || $(req).val().trim() == "") {
    	$("#errorReq").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorReq").html("I requisiti non devono superare i 3000 caratteri " +
    			"e di almeno 10 caratteri.");
		$("#errorReq").show();
        return false;
    }
}

function checkContratto() {
	var cont = $("#cont");
	if($(cont).val()) {
		$("#errorTipo").hide();
		if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
		return true;
	} else {
		$("#conferma").attr("disabled", true);
		$("#errorTipo").html("Devi selezionare una delle opzioni elencate.");
		$("#errorTipo").show();
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

function checkDesc() {
	   var desc = $("#desc");
	    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9  .,!?']{10,7000}$/;

	    if ($(desc).val().match(StringValidator) || $(desc).val().trim() == "") {
	    	$("#errorDescrizione").hide();
	    	if(!$("#conferma").is(":disabled")) {
	    		$("#conferma").attr("disabled", false);
	    	}
	    	return true;
	    } else {
	    	$("#conferma").attr("disabled", true);
	    	$("#errorDescrizione").html("La descrizione non può superare i 7.000 caratteri e " +
	    								"di almeno 10 caratteri");
	    	$("#errorDescrizione").show();
	        return false;
	    }	   
}

function checkTags() {
    var tags = $("#tags");
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9, ]{4,50}$/;

    if ($(tags).val().match(StringValidator) || $(tags).val().trim() == "") {
    	$("#errorTags").hide();
    	if(!$("#conferma").is(":disabled")) {
    		$("#conferma").attr("disabled", false);
    	}
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorTags").html("Il tag deve contenere solo lettere.");
    	$("#errorTags").show();
        return false;
    }
}

