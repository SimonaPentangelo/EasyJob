/**
 * 
 */

function checkTitolo() {
    var tit = $("#titolo");
    var StringValidator = /^[A-Za-z]{6,20}$/;

    if ($(tit).val().match(StringValidator) || $(tit).val().trim() == "") {
    	$("#errorTitolo").hide();
    	return true;
    } else {
    	$("#errorTitolo").html("Il titolo deve contenere minimo 6 e massimo 50 numeri e lettere.");
		$("#errorTitolo").show();
        return false;
    }
}

function checkReq() {
    var req = $("#req");
    var StringValidator = /^[A-Za-z\é\è\ò\à\ù\ì\ .,!?']{10,3000}$/;

    if ($(req).val().match(StringValidator) || $(req).val().trim() == "") {
    	$("#errorReq").hide();
    	return true;
    } else {
    	$("#errorReq").html("La descrizione non può superare i 7.000 " +
    								"caratteri e di almeno 10 caratteri");
		$("#errorReq").show();
        return false;
    }
}

function checkContratto() {
    var cont = $("#cont");

    if () {
    	$("#errorTipo").hide();
    	return true;
    } else {
    	$("#errorTipo").html("Devi selezionare una delle opzioni elencate.");
		$("#errorTipo").show();
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

function checkTags() {
    var tags = $("#tags");
    var StringValidator = /^[A-Za-z]{6,20}$/;

    if ($(tags).val().match(StringValidator) || $(tags).val().trim() == "") {
    	$("#errorTags").hide();
    	return true;
    } else {
    	$("#errorTags").html("Il tag deve contenere solo lettere.");
    	$("#errorTags").show();
        return false;
    }
}

