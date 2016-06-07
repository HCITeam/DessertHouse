/**
 * 
 */
$("#tool-btn-check").click(function(){
	var month=$("#month").val();
	var id=$("#s_id").val();
	
	$("#input-month-s").val(month);
	$("#input-sid-s").val(id);
	$("#form-record").submit();
});

$("#member-record").click(function(){
	var month=2;
	
	$("#input-month-m").val(month);
	$("#form-statistics").submit();
});