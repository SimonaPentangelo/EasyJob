/**
 * 
 */

function checkData() {
    var data = $("#dataID").val();
    var oggi = new Date();
    var dataRic = new Date(data);
    console.log(dataRic);
    console.log(oggi);
    if (data != null && isValidDate(dataRic) && dataRic.getFullYear().toString().length >= 4) 
    {
        if (oggi > dataRic) 
        {
        	$("#errorData").hide();
        	
            return true;
        } 
        else
        {
        	$("#errorData").html("La data inserita non rispetta il formato.");
    		$("#errorData").show();
        	return false;
        }
    } else {
    	
    	$("#errorData").html("La data inserita non rispetta il formato.");
		$("#errorData").show();
    	return false;
    }
}

function isValidDate(d) {
	  return d instanceof Date && !isNaN(d);
}