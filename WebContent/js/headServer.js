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

function addClick()
{
	$("#addStoreLine").html("<td><input type='date' class='long-input-td showBorder' id='p_date' placeholder='2016/03/01'></td>\
			<td id='add-readid'><div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div></td>\
			<td><select class='long-input-td showBorder' id='p_name'>\
							<option value='南瓜派'>南瓜派</option>\
							<option value='草莓派'>草莓派</option>\
							<option value='起司蛋糕'>起司蛋糕</option>\
							<option value='姜饼小人'>姜饼小人</option>\
							<option value='菠萝包'>菠萝包</option>\
							<option value='奶酪蛋糕'>奶酪蛋糕</option>\
							<option value='提拉米苏'>提拉米苏</option>\
							<option value='切片面包'>切片面包</option>\
							<option value='蛋挞'>蛋挞</option>\
						</select></td>\
			<td><input type='text' class='short-input-td showBorder' id='p_num' placeholder='商品数量'></td>\
			<td><input type='text' class='short-input-td showBorder' id='price' placeholder='单价'></td>\
		    <td><a id='newStoreAdd'><img class='delImg'\
					src='../img/check.png'></a></td>\
			<td><a id='newStoreDel'><img class='delImg'\
					src='../img/delete2.png'></a></td>");

	$("#newStoreDel").click(function()
	{
		$("#addStoreLine").html("<td colspan='7'> <div class='addBut' id='addStoreBut'>+</div></td>");
		$("#addStoreBut").click(addClick);
	});
	$("#newStoreAdd").click(addHead);
	
	$.ajax({
        type:"GET",
        url:"/Desserthouse/api/ListStore",
        success:function(result,textStatus){
        	var stores=result['store_list'];
        	$("#add-readid").html("<select id='add-s-id' class='showBorder'><option value='0'>读取中</option></select>");
        	$("#add-s-id").html("");
        	$.each(stores,function(index,store)
        	{
        		var id=store.id;
        		var name=store.name;
        		$("#add-s-id").append("<option value='"+id+"'>"+name+"</option>");
        	});
        }
        ,error:function(data)
        {
        	$("body").html(data.responseText);
        }
    });
	//$(".modal-wrapper").show();
	//$("body").css("overflow","hide");
}

$("#addStoreBut").click(addClick);


function addHead()
{
	var s_id=$("#add-s-id").val();
	var p_name=$("#p_name").val();
	var p_num=$("#p_num").val();
	var price=$("#price").val();
	var p_date=$("#p_date").val();
	if(s_id=="")
	{
		document.getElementById('add-s-id').focus();
    	$("#add-s-id").attr("placeholder","请输入");
    	return;
	}
	else if(p_name=="")
	{
		document.getElementById('p_name').focus();
    	$("#p_name").attr("placeholder","请输入");
    	return;
	}
	else if(p_num=="")
	{
		document.getElementById('p_num').focus();
    	$("#p_num").attr("placeholder","请输入");
    	return;
	}
	else if(price=="")
	{
		document.getElementById('price').focus();
    	$("#price").attr("placeholder","请输入");
    	return;
	}
	else if(p_date==""||p_date==" "||p_date==false||p_date=="false")
	{
		document.getElementById('p_date').focus();
    	$("#p_date").attr("placeholder","请输入");
    	return;
	}
	else if (s_id==""||p_name==""||p_num==""||price=="") 
	{
		return;
	}
	$("#addStoreLine").html("<td colspan='7'><div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div></td>");
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddPlan",
                data:{'p_date':p_date,'s_id':s_id,'p_name':p_name,'p_num':p_num,'price':price},
                success:function(result,textStatus){
                		$("#addStoreLine").remove();
                    	$("#plan-table").append("<tr>"+
                    		"<td>"+p_date+"</td>"+
                    		"<td>"+result.store_name+"</td>"+
                    		"<td>"+p_name+"</td>"+
                    		"<td><input type=\"text\" class=\"short-input-td\" id=\""+result.p_id+"-num\" value=\""+p_num+"\"></td>"+
                    		"<td><input type=\"text\" class=\"short-input-td\" id=\""+result.p_id+"-price\" value=\""+price+"\"></td>"+
                    		"<td><a class=\"plan-btn-edit\" id=\""+result.p_id+"-edit\"><img src=\"../img/edit.png\"></a></td>"+
                    		"<td><a class=\"plan-btn-delete\" id=\""+result.p_id+"-delete\"><img src=\"../img/delete2.png\"></a></td>"+
                    		"</tr>"+
                    		"<tr class='tableBottomTr' id='addStoreLine'>"+
                    		"<td colspan='7'> <div class='addBut' id='addStoreBut'>+</div></td>"+
                    		"</tr>");
                    	$("#addStoreLine").html("<td colspan='7'> <div class='addBut' id='addStoreBut'>+</div></td>");
                		$("#addStoreBut").click(addClick);
                },error:function(data)
                {
                	$("#addStoreLine").html("<td colspan='7'> <div class='addBut' id='addStoreBut'>+</div></td>");
            		$("#addStoreBut").click(addClick);
                }
            });
}

$(document).on("click",".store-btn-edit",function(){
   var button_id=$(this).attr("id");
   var id=button_id.split("-")[0];//取得计划id
   if($("#"+id+"-num").attr("disabled")=="disabled")
   {
	   $("#"+id+"-num").removeAttr("disabled");
	   $("#"+id+"-price").removeAttr("disabled");
	   $("#"+id+"-num").addClass("showBorder");
	   $("#"+id+"-price").addClass("showBorder");
	   return;
   }
   $("#"+id+"-num").attr("disabled","disabled");
   $("#"+id+"-price").attr("disabled","disabled");
   $("#"+id+"-num").removeClass("showBorder");
   $("#"+id+"-price").removeClass("showBorder");
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
                    	//alert(result.message);
                }
            });
});

$(document).on("click",".store-btn-delete",function(){
   var button_id=$(this).attr("id");
   var id=button_id.split("-")[0];//取得计划id
   $("#"+id+"-delTd").html("<div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div>");
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/DeletePlan",
                data:{'p_id':id},
                success:function(result,textStatus){
                	$("#"+id+"-delTd").parent().hide(500);
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