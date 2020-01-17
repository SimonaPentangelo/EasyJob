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
	
	if($(cont).val() != "iniziale") {
		$("#errorTipo").hide();
		
		return true;
	} else {
		
		$("#errorTipo").html("Devi selezionare una delle opzioni elencate.");
		$("#errorTipo").show();
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
    	
    	$("#errorCitta").html("La citt\xE0 deve contenere solo lettere e " +
    			"formata minimo da 2 lettere e massimo da 20.");
    	$("#errorCitta").show();
        return false;
    }
}

function checkDesc() {
	   var desc = $("#desc").val();
	    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9  .,!?']{10,7000}$/;

	    if (desc.match(StringValidator) && desc != null) {
	    	$("#errorDescrizione").hide();
	    	
	    	return true;
	    } else {
	    
	    	$("#errorDescrizione").html("La descrizione non pu\xF2 superare i 7.000 caratteri e " +
	    								"di almeno 10 caratteri");
	    	$("#errorDescrizione").show();
	        return false;
	    }	   
}

function checkTags() {
    var tags = $("#tags").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9, ]{4,50}$/;

    if (tags.match(StringValidator) && tags != null) {
    	$("#errorTags").hide();
    	
    	return true;
    } else {
    	
    	$("#errorTags").html("Il tag deve contenere solo lettere e ogni parola deve essere separata da virgola.");
    	$("#errorTags").show();
        return false;
    }
}

function checkAll() {
	if(!checkTitolo() || !checkCitta() || !checkContratto() || !checkTags()) {
		
		return false;
	} else {
		
		return true;
	}
}

