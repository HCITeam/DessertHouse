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
	if($(this).attr("disabled")=="disabled") return;
	var message = $("#input-message").val();
	var s_name = $("#server").val();
	if(message=="")
	{
		$("#input-message").focus();
		$("#input-message").attr("placeholder","请输入消息");
		return;
	}
	$("#sendMessage").html("<div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div>");
	$("#sendMessage").attr("disabled","disabled");
	console.log("here");

	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/SendMessage",
        data:{
        	'content': message,
        	'name': s_name
        	},
        success:function(result,textStatus){
        	$("#sendMessage").html("发送");
        	$("#sendMessage").removeAttr("disabled");
            	var isSuccess=result.success;
            	$("#sendOver").html(result.message);
            	$("#sendOver").slideDown(200);
            	$("#sender").slideUp(200);
            	setTimeout(function()
            	{
            		$("#sendOver").slideUp(200);
                	$("#sender").slideDown(200);
            	},600); 
        }
    });
})