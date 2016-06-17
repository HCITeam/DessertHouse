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
   if($("#"+id+"-name").attr("disabled")=="disabled")
   {
	   $("#"+id+"-name").removeAttr("disabled");
	   $("#"+id+"-name").css("box-shadow","0px 0px 2px 2px #ff8488");
	   $("#"+id+"-addr").removeAttr("disabled");
	   $("#"+id+"-addr").css("box-shadow","0px 0px 2px 2px #ff8488");
	   $("#"+id+"-tel").removeAttr("disabled");
	   $("#"+id+"-tel").css("box-shadow","0px 0px 2px 2px #ff8488");
   }
   else
   {
	   $("#"+id+"-name").attr("disabled","true");
	   $("#"+id+"-name").css("box-shadow","0px 0px 0px 0px #ff8488");
	   $("#"+id+"-addr").attr("disabled","true");
	   $("#"+id+"-addr").css("box-shadow","0px 0px 0px 0px #ff8488");
	   $("#"+id+"-tel").attr("disabled","true");
	   $("#"+id+"-tel").css("box-shadow","0px 0px 0px 0px #ff8488");
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
	                    	//alert(result.message);
	                }
	            });
   }
  
});

$(document).on("click",".store-btn-delete",function(){
   var button_id=$(this).attr("id");
   var id=button_id.split("-")[0];//取得id
   $("#"+button_id+"").parent().parent().hide(500);
  // alert(id);
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/DeleteStore",
                data:{'s_id':id},
                success:function(result,textStatus){
                    	if (result.success==1) {
                    		
                    	}
                }
            });
});





function addClick()
{
	$("#addStoreLine").html("<td class='short-input-td'>新增</td>\
			<td><input type='text' class='showBorder' id='newStoreName' placeholder='请输入店名'></td>\
			<td><input type='text' class='showBorder' id='newStoreLocation' placeholder='请输入地址'></td>\
			<td><input type='text' class='showBorder' id='newStoreTel' placeholder='请输入联系电话'></td>\
		    <td><a id='newStoreAdd'><img class='delImg'\
					src='../img/check.png'></a></td>\
			<td><a id='newStoreDel'><img class='delImg'\
					src='../img/delete2.png'></a></td>");

	$("#newStoreDel").click(function()
	{
		$("#addStoreLine").html("<td colspan='6'> <div class='addBut' id='addStoreBut'>+</div></td>");
		$("#addStoreBut").click(addClick);
	});
	$("#newStoreAdd").click(addStore);
	
	//$(".modal-wrapper").show();
	//$("body").css("overflow","hide");
}

$("#addStoreBut").click(addClick);

function addStore()
{
	var name=$("#newStoreName").val();
    var addr=$("#newStoreLocation").val();
    var tel=$("#newStoreTel").val();
    if(name=="")
    {
    	document.getElementById('newStoreName').focus();
    	$("#newStoreName").attr("placeholder","店名不能为空");
    }
    else if(addr=="")
    {
    	document.getElementById('newStoreLocation').focus();
    	$("#newStoreLocation").attr("placeholder","地址不能为空");
    }
    else if(tel=="")
    {
    	document.getElementById('newStoreTel').focus();
    	$("#newStoreTel").attr("placeholder","联系电话不能为空");
    }
    else if (tel==""||name==""||addr=="") {
		$(".form-message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}
    else 
    	{
    		$("#addStoreLine").html("<td colspan='6'><div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div></td>");
    		$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddStore",
                data:{'store_name':name,'address':addr,'phone':tel},
                success:function(result,textStatus){
                    	//alert(result.message);
                    	//alert(result.s_id);
                	//window.location = "/Desserthouse/ManageStore";
                		$("#addStoreLine").remove();
                    	$("#store-table").append("<tr  class='tableBottomTr'>"+
                    		"<td>"+result.s_id+"</td>"+
                    		"<td><input disabled='true' type=\"text\" class=\"short-input-td\" id=\""+result.s_id+"-name\" value=\""+name+"\"></td>"+
                    		"<td><input disabled='true' type=\"text\" class=\"long-input-td\" id=\""+result.s_id+"-addr\" value=\""+addr+"\"></td>"+
                    		"<td><input disabled='true' type=\"text\" class=\"long-input-td\" id=\""+result.s_id+"-tel\" value=\""+tel+"\"></td>"+
                    		"<td><a class='store-btn-edit' id=\""+result.s_id+"-edit\"><img class='delImg' src=\"../img/edit.png\"></a></td>"+
                    		"<td><a class='store-btn-delete' id=\""+result.s_id+"-delete\"><img class='delImg' src=\"../img/delete2.png\"></a></td>"+
                    		"</tr>" +
                    		"<tr class='tableBottomTr' id='addStoreLine'>"+
                    		"<td colspan='6'> <div class='addBut' id='addStoreBut'>+</div></td>"+
                    		"</tr>");
                    	$("#employee-add").show();
                    	$("body").css("overflow","hide");
                		$("#addStoreBut").click(addClick);
                }
                ,error:function(data)
                {
                	$("#addStoreLine").html("<td colspan='6'> <div class='addBut' id='addStoreBut'>+</div></td>");
            		$("#addStoreBut").click(addClick);
                	alert(data.responseText);
                	//$("body").html(data.responseText);
                }
            });
    	}
}


