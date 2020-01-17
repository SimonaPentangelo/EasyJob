/**
 * 
 */


function checkTitolo() {
    var tit = $("#titolo").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF90-9,. ]{6,50}$/;

    if (tit.match(StringValidator) && tit != null) {
    	$("#errorTitolo").hide();
    	
    	return true;
    } else {
    	
    	$("#errorTitolo").html("Il titolo deve contenere minimo 5 e massimo 60 numeri e lettere.");
		$("#errorTitolo").show();
        return false;
    }
}

function checkReq() {
    var req = $("#req").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?']{10,3000}$/;

    if (req.match(StringValidator) && req != null) {
    	$("#errorReq").hide();
    	
    	return true;
    } else {
    	
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
    	$("#errorTags").html("Il tag deve contenere solo lettere e ogni parola deve essere separata da virgola.");
    	$("#errorTags").show();
        return false;
    }
}
