/**
 * 管理服务员界面
 */
$(document).on("click",".employee-btn-edit",function(){
	var name=$(this).attr("id").split("-")[0];
	$("#edit-name").html(name);
    $("#employee-edit").show();
	$("body").css("overflow","hide");
});

function addClick()
{
	$("#addStoreLine").html("<td><input type='text'  id='newStoreName' placeholder='请输入用户名'></td>\
							<td><select id='newStoreId'><option value='0'>无</option></select></td>\
							<td><select id='newStoreType'><option value='2'>总店服务员</option><option value='3'>分店服务员</option></select></td>\
							<td><a id='newStoreAdd'><img class='delImg'\
							src='../img/check.png'></a></td>\
							<td><a id='newStoreDel'><img class='delImg'\
							src='../img/delete2.png'></a></td>");

	$.ajax({
        type:"POST",
        url:"/DessertHouse/api/ListStore",
        success:function(result,textStatus)
        {
        	var stores=result['store_list'];
        	$.each(stores,function(index,store)
        	{
        		var id=store.id;
        		var name=store.name;
        		alert(id+":"+name);
        	});
        }
        ,error:function(XMLHttpRequest, textStatus, errorThrown)
        {
        	alert("error:"+textStatus+","+errorThrown);
        }
	});
	
	$("#newStoreAdd").click(addEmployee);
	$("#newStoreDel").click(function()
	{
		$("#addStoreLine").html("<td colspan='6'> <div class='addBut' id='addStoreBut'>+</div></td>");
		$("#addStoreBut").click(addClick);
	});
	//$(".modal-wrapper").show();
	//$("body").css("overflow","hide");
}

function addEmployee()
{
	var name=$("#newStoreName").val();
	var s_id=$("#newStoreId").val();
	var type=$("#newStoreType").val();
	var passwored="admin";
	var pass="admin";
	if(name=="")
	{
		document.getElementById('newStoreName').focus();
	    $("#newStoreName").attr("placeholder","店名不能为空");
	    return;
	}
	
	
	
	if (passwored!=pass) {
		$("#confirm-add").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>两次密码不一致</div>");
		return;
	}
	if(type==2){
		 s_id=0;
		 $("#add-s-id").val(s_id);
	}
	$("#addStoreLine").html("<td colspan='6'><div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div></td>");
	$.ajax({
	            type:"POST",
	            url:"/Desserthouse/api/AddEmployee",
	            data:{'s_id':s_id,'name':name,'work_type':type,'password':passwored},
	            success:function(result,textStatus){
	            		$("#addStoreLine").remove();
	//                    	alert(result.store_name);
	                	$("#store-table").append("<tr>"+
	                		"<td>"+name+"</td>"+
	                		"<td>"+result.store_name+"</td>"+
	                		"<td>"+result.work_type+"</td>"+
	                		"<td><a class=\"plan-btn-edit\" id=\""+name+"-edit\"><img src=\"../img/edit.png\"></a></td>"+
	                		"<td><a class=\"plan-btn-delete\" id=\""+name+"-delete\"><img src=\"../img/delete2.png\"></a></td>"+
	                		"</tr>");
	                	$("#employee-add").show();
	                	$("body").css("overflow","hide");
	                	$("#addStoreBut").click(addClick);
	            }
	            ,error:function(XMLHttpRequest, textStatus, errorThrown)
	            {
	            	$("#addStoreLine").html("<td colspan='6'> <div class='addBut' id='addStoreBut'>+</div></td>");
	        		$("#addStoreBut").click(addClick);
	        		$("#addStoreBut").click(addClick);
	            	alert(textStatus);
	            }
	});
}


$("#addStoreBut").click(addClick);

$("#tool-btn-employee").click(function(){
	$("#employee-add").show();
	$("body").css("overflow","hide");
});

$(".close-btn").on("click",function(){
   $(".modal-wrapper").hide();
   $("body").css("overflow","auto");
});

$(document).on("click","#confirm-edit",function(){
   var id=$(this).attr("id");
   var name=id.split("-")[0];
   $("#edit-name").html(name);
   var s_id=$("#edit-s-id").val();
   var type=$("#edit-type").val();

   if (s_id==""||name==""||type=="") {
		$("#form-edit").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/UpdateEmployee",
                data:{'s_id':s_id,'name':name,'work_type':type},
                success:function(result,textStatus){
                    	alert(result.message);
                }
            });
});

$(document).on("click",".employee-btn-delete",function(){
   var button_id=$(this).attr("id");
   var name=button_id.split("-")[0];//取得id
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/DeleteEmployee",
                data:{'name':name},
                success:function(result,textStatus){
                    	alert(result.message);
                    	if (result.success==1) {
                    		$("#"+button_id+"").parent().parent().remove();
                    	}
                }
            });
});

$(".confirm-btn").on("click",function(){
	var name=$("#add-name").val();
   var s_id=$("#add-s-id").val();
   var type=$("#add-type").val();
   var passwored=$("#add-password").val();
   var pass=$("#add-password-twice").val();
    if (s_id==""||name==""||type==""||pass==""||passwored=="") {
		$("#confirm-add").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}

	 if (passwored!=pass) {
		$("#confirm-add").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>两次密码不一致</div>");
		return;
	}
	 if(type==2){
		 s_id=0;
		 $("#add-s-id").val(s_id);
	 }
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddEmployee",
                data:{'s_id':s_id,'name':name,'work_type':type,'password':passwored},
                success:function(result,textStatus){
                    	alert(result.message);
//                    	alert(result.store_name);
                    	$("#store-table").append("<tr>"+
                    		"<td>"+name+"</td>"+
                    		"<td>"+result.store_name+"</td>"+
                    		"<td>"+result.work_type+"</td>"+
                    		"<td><a class=\"plan-btn-edit\" id=\""+name+"-edit\"><img src=\"../img/edit.png\"></a></td>"+
                    		"<td><a class=\"plan-btn-delete\" id=\""+name+"-delete\"><img src=\"../img/delete2.png\"></a></td>"+
                    		"</tr>");
                    	$("#employee-add").show();
                    	$("body").css("overflow","hide");
                }
            });
});/**
 * 
 */