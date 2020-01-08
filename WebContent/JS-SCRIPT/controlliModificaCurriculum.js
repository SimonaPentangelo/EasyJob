/**
 * 
 */

function checkCurriculum() {
	var curr = $("#curriculum");
	var name = curr.val();
	var ext = name.substring(name.length - 3);
    if (ext == "pdf") {
    	var sizeInMB = curr[0].size / Math.pow(1024,2)
        if(sizeInMB <= 10.00) {
        	$("#errorCurriculum").hide();
        	if(!$("#conferma").is(":disabled")) {
        		$("#conferma").attr("disabled", false);
        	}
        	return true;
        } else {
        	$("#conferma").attr("disabled", true);
        	$("#errorCurriculum").html("La dimensione non deve superare i 10MB.");
    		$("#errorCurriculum").show();
        	return false;
        }
    } else {
    	$("#conferma").attr("disabled", true);
    	$("#errorCurriculum").html("Il file deve essere in formato PDF.");
		$("#errorCurriculum").show();
        return false;
    }
}