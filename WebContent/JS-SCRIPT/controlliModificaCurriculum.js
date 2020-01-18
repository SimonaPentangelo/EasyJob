/**
 * 
 */

function checkCurriculum() {
	var curr = $("#curriculum");
	var name = curr.val();
	var ext = name.substring(name.length - 3);
    if (name != null && ext == "pdf") {
    	var sizeInMB = curr[0].size / Math.pow(1024,2)
        if(sizeInMB <= 10.00) {
        	$("#errorCurriculum").hide();
        	
        	return true;
        } else {
        	
        	$("#errorCurriculum").html("La dimensione non deve superare i 10MB.");
    		$("#errorCurriculum").show();
        	return false;
        }
    } else {
    	
    	$("#errorCurriculum").html("Il file deve essere in formato PDF.");
		$("#errorCurriculum").show();
        return false;
    }
}