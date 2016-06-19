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

$("#sendMessage").click(function(){
	console.log("here");

	var message = $("#input-message").val();
	var s_name = $("#server").val();
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/SendMessage",
        data:{
        	'content': message,
        	'name': s_name
        	},
        success:function(result,textStatus){
            	var isSuccess=result.success;
            	if(isSuccess){
            		alert(result.message);
            	}else{
            		alert(result.message);
            	}
        }
    });
})