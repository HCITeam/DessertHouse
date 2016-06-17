/**
 * 会员卡信息
 */

 $("#modify-integral").on("click",function(){
 	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/ToCash",
                data:{},
                success:function(result,textStatus){
                   var isSuccess=result.success;
                   var message=result.message;
                   if (isSuccess==1) {
                   	var balance=result.balance;
                   	var integral=result.integral;
                   	$("#span-integral").html(integral);
                   	$("#span-balance").html(balance);
                   }else{
                   	alert(message);
                   }
                }
            });
 });

 $("#modify-bankcard").on("click",function(){
 	var bankcard=$("#bankcard").val();
 	if (bankcard==""||bankcard==" ") {
 		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请输入银行卡号</div>");
		return;
 	}
 	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/ChangeBankcard",
                data:{'bankcard':bankcard},
                success:function(result,textStatus){
                   // var isSuccess=result.success;
                   var message=result.message;
                   	alert(message);
                   
                }
            });
 });

  $("#modify-recharge").on("click",function(){
  	var bankcard=$("#bankcard").val();
 	if (bankcard==""||bankcard==" ") {
 		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>您未绑定银行卡，请到店里充值</div>");
		return;
 	}
 	$(".modal-wrapper").show();
 	$("body").css("overflow","hide");
 });

$(".close-btn").on("click",function(){
   $(".modal-wrapper").hide();
   $("body").css("overflow","auto");
});

$("#confirm-recharge").on("click",function(){
	var amount=$("#amount").val();
	var bankcard=$("#bankcard").val();
	if (amount=="") {
		$(".dialog-message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请输入金额</div>");
		return;
	}
	if (isNaN(amount)) {
		$(".dialog-message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请输入数字</div>");
		return;
	}
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/CardRecharge",
                data:{'bankcard':bankcard,'amount':amount},
                success:function(result,textStatus){
                   // var isSuccess=result.success;
                	if(result.success==1){
                		var amount_old = $("#span-balance").html();
                		var amount_new = parseInt(amount_old);
                		$("#span-balance").html(amount_new);
                	}else{
                		var message=result.message;
                       	alert(message);	
                	}
                   	$(".modal-wrapper").hide();
                    $("body").css("overflow","auto");
                }
            });
});