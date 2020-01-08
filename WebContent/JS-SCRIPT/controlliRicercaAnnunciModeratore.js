/**
 * 
 */

function checkData() {
    var data = $("#dataID");
    var oggi = new Date();
    var dataRic = new Date($(data).val());
    console.log(dataRic);
    console.log(oggi);
    if (dataRic.getFullYear().toString().length >= 4) 
    {
        if (oggi > dataRic) 
        {
        	$("#errorData").hide();
        	$("#conferma").attr("disabled", false);
            return true;
        } 
        else
        {
        	$("#conferma").attr("disabled", true);
        	$("#errorData").html("La data inserita non rispetta il formato.");
    		$("#errorData").show();
        	return false;
        }
    }
}