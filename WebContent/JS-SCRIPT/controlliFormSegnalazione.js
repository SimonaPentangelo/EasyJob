/**
 * 
 */

function checkAll() {
	
	if(!checkTitolo()) {
		return false;
	} else if(!checkBody())  {
		return false;
	} else {
		var form = $('#segnala')[0]; 
		var formData = new FormData(form);
		
		$.ajax({
		    url: "../../SegnalazioneUtenteServlet",
		    data: formData,
		    type: 'GET',
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
    	return true;
    } else {
    	$("#errorBody").html("Il corpo può contenere minimo 10 caratteri e al massimo 10.000 caratteri.");
		$("#errorBody").show();
        return false;
    }
}