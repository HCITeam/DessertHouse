$("#tool-btn-check").click(function(){
	var month=$("#month").val();
	
	$("#input-month-m").val(month);
	$("#form-statistics").submit();
});

$("#sale-record").click(function(){
	var month=2;
	var id=1;
	
	$("#input-month-s").val(month);
	$("#input-sid-s").val(id);
	$("#form-record").submit();
});