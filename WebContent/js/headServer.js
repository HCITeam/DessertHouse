/**
 * 总服务员界面
 */
$("#tool-btn-plan").click(function(){
	$(".modal-wrapper").show();
	$("body").css("overflow","hide");
});

$(".close-btn").on("click",function(){
   $(".modal-wrapper").hide();
   $("body").css("overflow","auto");
});

$(document).on("click",".plan-btn-edit",function(){
   var button_id=$(this).attr("id");
   var id=button_id.split("-")[0];//取得计划id
   if (isNaN(id)) {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>ddd</div>");
		return;
	}
   var num=$("#"+id+"-num").val();
   var price=$("#"+id+"-price").val();
   if (num==""||isNaN(num)) {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请重新输入数量</div>");
		return;
	}
	if (price=="") {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>价格不能为空</div>");
		return;
	}
//	alert(id);
//	alert(num);
//	alert(price);
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/UpdatePlan",
                data:{'p_id':id,'p_num':num,'price':price},
                success:function(result,textStatus){
                    	alert(result.message);
                }
            });
});

$(document).on("click",".plan-btn-delete",function(){
   var button_id=$(this).attr("id");
   var id=button_id.split("-")[0];//取得计划id
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/DeletePlan",
                data:{'p_id':id},
                success:function(result,textStatus){
                    	alert(result.message);
                }
            });
});

$(".confirm-btn").on("click",function(){
//	alert($("#p_date").val());
//	var temp_date=$("#p_date").val().split("/");
//	alert(temp_date);
	var s_id=$("#s_id").val();
	var p_name=$("#p_name").val();
	var p_num=$("#p_num").val();
	var price=$("#price").val();
	var p_date=$("#p_date").val();
	if (s_id==""||p_name==""||p_num==""||price=="") {
		$(".form-message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}
//	var p_date=temp_date[0];
//	+"-"+temp_date[1]+"-"+temp_date[2];
	alert(p_date);
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddPlan",
                data:{'p_date':p_date,'s_id':s_id,'p_name':p_name,'p_num':p_num,'price':price},
                success:function(result,textStatus){
                    	alert(result.message);
                    	$("#plan-table").append("<tr>"+
                    		"<td>"+p_date+"</td>"+
                    		"<td>"+result.store_name+"</td>"+
                    		"<td>"+p_name+"</td>"+
                    		"<td><input type=\"text\" class=\"short-input-td\" id=\""+result.p_id+"-num\" value=\""+p_num+"\"></td>"+
                    		"<td><input type=\"text\" class=\"short-input-td\" id=\""+result.p_id+"-price\" value=\""+price+"\"></td>"+
                    		"<td><a class=\"plan-btn-edit\" id=\""+result.p_id+"-edit\"><img src=\"../img/edit.png\"></a></td>"+
                    		"<td><a class=\"plan-btn-delete\" id=\""+result.p_id+"-delete\"><img src=\"../img/delete.png\"></a></td>"+
                    		"</tr>");
                }
            });
});