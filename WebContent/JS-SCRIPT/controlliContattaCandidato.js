/**
 * 
 */

		 
function checkTitolo() {
	   var tit = $("#titolo").val();
	   var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?']{5,60}$/;

	   if (tit.match(StringValidator) || tit == null ) {
	    	$("#errorTit").hide();
	    	return true;
	    } else {
	    	$("#errorTit").html("Il titolo di invito al colloquio deve contenere minimo 5 caratteri e" +
	    						" al massimo 60 caratteri.");
			$("#errorTit").show();
	        return false;
	    }
}

function checkMsg() {
	var msg = $("#msg").val();
	   var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?']{10,10000}$/;

	   if (msg.match(StringValidator) || msg == null) {
	    	$("#errorMsg").hide();
	    	return true;
	    } else {
	    	$("#errorMsg").html("Il corpo pu\xF2 contenere minimo 10 caratteri " +
	    						"e al massimo 10.000 caratteri");
			$("#errorMsg").show();
	        return false;
	    }
}

function checkAll() {
	if(!checkTitolo() || !checkMsg()) {
		alert("Sono nell'if dovrebbe essere disabilitato");
		return false;
	} else {
		alert("Sono nell'else dovrebbe essere valido");
		return true;
	}
}