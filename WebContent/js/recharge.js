/**
 * 
 */
$(".modify-btn").click(function(){
	var id=$("#mid").val();
	var amount=$("#amount").val();
	if(id==""||amount==""){
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/CashRecharge",
        data:{'id':id,'amount':amount},
        success:function(result,textStatus){
           alert(result.message);
        }
    });
});