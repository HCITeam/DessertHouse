/**
 * 管理店面界面
 */
$("#tool-btn-store").click(function(){
	$(".modal-wrapper").show();
	$("body").css("overflow","hide");
});

$(".close-btn").on("click",function(){
   $(".modal-wrapper").hide();
   $("body").css("overflow","auto");
});

$(document).on("click",".store-btn-edit",function(){
   var button_id=$(this).attr("id");
   var id=button_id.split("-")[0];//取得id
   var name=$("#"+id+"-name").val();
   var addr=$("#"+id+"-addr").val();
   var tel=$("#"+id+"-tel").val();

   if (tel==""||name==""||addr=="") {
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/UpdateStore",
                data:{'s_id':id,'store_name':name,'address':addr,'phone':tel},
                success:function(result,textStatus){
                    	alert(result.message);
                }
            });
});

$(document).on("click",".store-btn-delete",function(){
   var button_id=$(this).attr("id");
   var id=button_id.split("-")[0];//取得id
   alert(id);
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/DeleteStore",
                data:{'s_id':id},
                success:function(result,textStatus){
                    	alert(result.message);
                    	if (result.success==1) {
                    		$("#"+button_id+"").parent().parent().remove();
                    	}
                }
            });
});

$(".confirm-btn").on("click",function(){
	var name=$("#name").val();
    var addr=$("#addr").val();
    var tel=$("#tel").val();
    if (tel==""||name==""||addr=="") {
		$(".form-message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddStore",
                data:{'store_name':name,'address':addr,'phone':tel},
                success:function(result,textStatus){
                    	alert(result.message);
                    	//alert(result.s_id);
                    	$("#store-table").append("<tr>"+
                    		"<td>"+result.s_id+"</td>"+
                    		"<td><input type=\"text\" class=\"short-input-td\" id=\""+result.s_id+"-name\" value=\""+name+"\"></td>"+
                    		"<td><input type=\"text\" class=\"long-input-td\" id=\""+result.s_id+"-addr\" value=\""+addr+"\"></td>"+
                    		"<td><input type=\"text\" class=\"long-input-td\" id=\""+result.s_id+"-tel\" value=\""+tel+"\"></td>"+
                    		"<td><a class=\"plan-btn-edit\" id=\""+result.s_id+"-edit\"><img src=\"../img/edit.png\"></a></td>"+
                    		"<td><a class=\"plan-btn-delete\" id=\""+result.s_id+"-delete\"><img src=\"../img/delete.png\"></a></td>"+
                    		"</tr>");
                    	$("#employee-add").show();
                    	$("body").css("overflow","hide");
                }
            });
});/**
 * 
 */