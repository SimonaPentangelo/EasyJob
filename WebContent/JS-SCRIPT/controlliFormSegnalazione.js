/**
 * 
 */


function checkTitolo() {
    var tit = $("#titolo");
    var StringValidator = /^[A-Za-z\é\è\ò\à\ù\ì\ .,!?']{5,60}$/;

    if ($(tit).val().match(StringValidator) || $(tit).val().trim() == "") {
    	$("#errorTit").hide();
    	$("#conferma").attr("disabled", false);
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorTit").html("Il titolo deve contenere minimo 5 e massimo 50 numeri e lettere.");
		$("#errorTit").show();
        return false;
    }
}

function checkBody() {
    var body = $("body");
    var StringValidator = /^[A-Za-z\é\è\ò\à\ù\ì\ .,!?']{10,10000}$/;

    if ($(body).val().match(StringValidator) || $(body).val().trim() == "") {
    	$("#errorBody").hide();
    	$("#conferma").attr("disabled", false);
    	return true;
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorBody").html("Il corpo può contenere minimo 10 caratteri e al massimo 10.000 caratteri.");
		$("#errorBody").show();
        return false;
    }
}