/**
 * 
 */


function checkTitolo() {
    var tit = $("#titolo").val();
    var StringValidator = /^[A-Za-zàèìòù .,!?' ]{5,60}$/;
    if (tit.match(StringValidator) || tit.trim() == "") {
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
    var body = $("#corpo").val();
    var StringValidator = /^[A-Za-zàèìòù .,!?']{10,10000}$/;

    if (body.match(StringValidator) || body.trim() == "") {
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