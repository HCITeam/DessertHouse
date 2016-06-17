/**
 * 管理服务员界面
 */
$(document).on("click",".store-btn-edit",function()
{
	var name=$(this).attr("id").split("-")[0];
	var idId="#"+name+"-id";
	var typeId="#"+name+"-type";
	var passwordId="#"+name+"-password";
	if($(this).attr("disabled")=="disabled") return;
	if($(passwordId).attr("disabled")=="disabled")
	{
		$(idId).html("读取中");
		//$(idId).html("<select disabled='disabled' id='"+name+"-editId'><option value='0'>读取中</option></select>");
		$(typeId).html("<select id='"+name+"-editType'><option value='2'>总店服务员</option><option value='3'>分店服务员</option></select>");
		$(passwordId).removeAttr("disabled");
		$(passwordId).addClass("showBorder");
		$("#"+name+"-edit").attr("disabled","disabled");
		$.ajax({
	        type:"GET",
	        url:"/Desserthouse/api/ListStore",
	        success:function(result,textStatus){
	        	if($(passwordId).attr("disabled")=="disabled") return;
	        	var stores=result['store_list'];
	        	$(idId).html("<select disabled='disabled' id='"+name+"-editId'><option value='0'>读取中</option></select>");
	        	$("#"+name+"-editId").html("");
	        	$.each(stores,function(index,store)
	        	{
	        		var id=store.id;
	        		var nameS=store.name;
	        		$("#"+name+"-editId").append("<option value='"+id+"'>"+nameS+"</option>");
	        	});
	        	
	        	$("#"+name+"-edit").removeAttr("disabled");
	        	$("#"+name+"-editId").removeAttr("disabled");
	        }
	        ,error:function(data)
	        {
	        	$("body").html(data.responseText);
	        }
	    });
	}
	else
	{
		
		var changeId=$("#"+name+"-editId").val();
		var changeType=$("#"+name+"-editType").val();
		var pass=$(passwordId).val();
		if(changeType==2)$(idId).html("总店");
		else $(idId).html($("#"+name+"-editId").find("option:selected").text());
		$(typeId).html($("#"+name+"-editType").find("option:selected").text());
		$(passwordId).attr("disabled","disabled");
		$(passwordId).removeClass("showBorder");
		$(passwordId).val("");
		$.ajax({
            type:"POST",
            url:"/Desserthouse/api/UpdateEmployee",
            data:{'s_id':changeId,'name':name,'work_type':changeType,'password':pass},
            success:function(result,textStatus){
                	//alert(result.message);
            }
            ,error:function(data)
            {
            	$("body").html(data.responseText);
            }
        });
		
	}
	
	//$("#edit-name").html(name);
    //$("#employee-edit").show();
	//$("body").css("overflow","hide");
});

function addClick()
{
	
	$("#addStoreLine").html("<td><input type='text' class='showBorder' id='add-name' placeholder='请输入用户名'></td>\
							<td id='add-readid'>读取中</td>\
							<td><select id='add-type'><option value='2'>总店服务员</option><option value='3'>分店服务员</option></select></td>\
							<td><input type='text' class='showBorder' id='add-password' placeholder='在此输入密码'></td>\
							<td><a id='newStoreAdd'><img class='delImg'\
							src='../img/check.png'></a></td>\
							<td><a id='newStoreDel'><img class='delImg'\
							src='../img/delete2.png'></a></td>");

	$.ajax({
        type:"GET",
        url:"/DessertHouse/api/ListStore",
        data:{},
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
	
	$.ajax({
        type:"GET",
        url:"/Desserthouse/api/ListStore",
        success:function(result,textStatus){
        	var stores=result['store_list'];
        	$("#add-readid").html("<select id='add-s-id'><option value='0'>读取中</option></select>");
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

function addEmployee()
{
	var name=$("#add-name").val();
    var s_id=$("#add-s-id").val();
    var type=$("#add-type").val();
    var passwored=$("#add-password").val();
    var pass=$("#add-password").val();

    if(name=="")
    {
    	document.getElementById('add-name').focus();
    	$("#add-name").attr("placeholder","用户名不能为空");
    	return;
    }
    else if(pass=="")
    {
    	document.getElementById('add-password').focus();
    	$("#add-password").attr("placeholder","密码不能为空");
    	return;
    }
    else if (s_id==""||name==""||type==""||pass==""||passwored=="") {
		$("#confirm-add").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入不能为空</div>");
		return;
	}

    else if (passwored!=pass) {
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
                    	$("#store-table").append("<tr class='tableBottomTr'>"+
                    		"<td id='"+name+"'-name'>"+name+"</td>"+
                    		"<td id='"+name+"'-id'>"+result.store_name+"</td>"+
                    		"<td id='"+name+"'-type'>"+result.work_type+"</td>"+
                    		"<td><input disabled='true' type='text'  id='"+name+"'-password' placeholder='在此输入新密码'></td>"+
                    		"<td><a class=\"store-btn-edit\" id=\""+name+"-edit\"><img src=\"../img/edit.png\"></a></td>"+
                    		"<td><a class=\"store-btn-delete\" id=\""+name+"-delete\"><img src=\"../img/delete2.png\"></a></td>"+
                    		"</tr>"+
                    		"<tr class='tableBottomTr' id='addStoreLine'>"+
                    		"<td colspan='6'> <div class='addBut' id='addStoreBut'>+</div></td>"+
                    		"</tr>");
//                    	$("#employee-add").show();
//                    	$("body").css("overflow","hide");
                    	$("#addStoreLine").html("<td colspan='6'> <div class='addBut' id='addStoreBut'>+</div></td>");
                		$("#addStoreBut").click(addClick);
                },error:function(data)
                {
                	$("#addStoreLine").html("<td colspan='6'> <div class='addBut' id='addStoreBut'>+</div></td>");
            		$("#addStoreBut").click(addClick);
                	//$("body").html(data.responseText);
                }
            });
}

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
