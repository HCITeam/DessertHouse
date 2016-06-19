/**
 * 
 */
$(".modify-btn").click(function(){
	if($(this).attr("disabled")=="disabled") return;
	var id=$("#mid").val();
	var amount=$("#amount").val();
	if(id==""||amount==""){
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}
	
	$(".modify-btn").html("<div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div>");
	$(".modify-btn").attr("disabled","disabled");
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/CashRecharge",
        data:{'id':id,'amount':amount},
        success:function(result,textStatus){
        	$(".modify-btn").html("充值");
        	$(".modify-btn").removeAttr("disabled");
        	$("#mid").val("");
        	$("#amount").val("");
        	$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>"+result.message+"</div>");
        }
    });
});