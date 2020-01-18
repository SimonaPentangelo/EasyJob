/**
 * 
 */


function checkTitolo() {
    var tit = $("#titolo").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?' ]{5,60}$/;
    
    if (tit.match(StringValidator) && tit != null) {
    	$("#errorTit").hide();
    	return true;
    } else {
    	$("#errorTit").html("Il titolo deve contenere minimo 5 e massimo 50 numeri e lettere.");
		$("#errorTit").show();
        return false;
    }
}

function checkBody() {
    var body = $("#corpo").val();
    var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?']{10,10000}$/;

    if (body.match(StringValidator) && body != null) {
    	$("#errorBody").hide();
    	return true;
    } else {
    	$("#errorBody").html("Il corpo pu\xF2 contenere minimo 10 caratteri e al massimo 10.000 caratteri.");
		$("#errorBody").show();
        return false;
    }
}

function checkAll() {
	if(!checkTitolo() || !checkBody()) {
		
		return false;
	} else {
		
		return true;
	}
}