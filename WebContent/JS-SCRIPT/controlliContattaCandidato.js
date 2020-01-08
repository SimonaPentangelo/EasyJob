/**
 * 
 */

		 
function checkTitolo() {
	   var tit = $("#titolo");
	   var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?']{5,60}$/;

	   if ($(tit).val().match(StringValidator) || $(tit).val().trim() == "") {
	    	$("#errorTit").hide();
	    	if(!$("#conferma").is(":disabled")) {
	    		$("#conferma").attr("disabled", false);
	    	}
	    	return true;
	    } else {
	    	$("#conferma").attr("disabled", true);
	    	$("#errorTit").html("Il titolo di invito al colloquio deve contenere minimo 5 caratteri e" +
	    						" al massimo 60 caratteri.");
			$("#errorTit").show();
	        return false;
	    }
}

function checkMsg() {
	var msg = $("#msg");
	   var StringValidator = /^[A-Za-z\xE0\xE8\xEC\xF2\xF9 .,!?']{10,10000}$/;

	   if ($(msg).val().match(StringValidator) || $(msg).val().trim() == "") {
	    	$("#errorMsg").hide();
	    	if(!$("#conferma").is(":disabled")) {
	    		$("#conferma").attr("disabled", false);
	    	}
	    	return true;
	    } else {
	    	$("#conferma").attr("disabled", true);
	    	$("#errorMsg").html("Il corpo può contenere minimo 10 caratteri " +
	    						"e al massimo 10.000 caratteri");
			$("#errorMsg").show();
	        return false;
	    }
}