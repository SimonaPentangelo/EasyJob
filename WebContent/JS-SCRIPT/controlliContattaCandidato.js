/**
 * 
 */

function checkAll() {
	
	if(!checkTitolo()) {
		return false;
	} else if(!checkMsg())  {
		return false;
	} else {
		var form = $('#contatta')[0]; 
		var formData = new FormData(form);
		
		$.ajax({
		    url: "../../ContattaCandidatoServlet",
		    data: formData,
		    type: 'POST',
		    contentType: false, 
		    processData: false,
		    async : false,
		    success: function(data){
	             //codice per la redirect
	         }
		});
		return true;
	}
}
		 
function checkTitolo() {
	   var tit = $("#titolo");
	   var StringValidator = /^[A-Za-z\é\è\ò\à\ù\ì\ .,!?']{5,60}$/;

	   if ($(tit).val().match(StringValidator) || $(tit).val().trim() == "") {
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
	var msg = $("#msg");
	   var StringValidator = /^[A-Za-z\é\è\ò\à\ù\ì\ .,!?']{10,10000}$/;

	   if ($(msg).val().match(StringValidator) || $(msg).val().trim() == "") {
	    	$("#errorMsg").hide();
	    	return true;
	    } else {
	    	$("#errorMsg").html("Il corpo può contenere minimo 10 caratteri " +
	    						"e al massimo 10.000 caratteri");
			$("#errorMsg").show();
	        return false;
	    }
}