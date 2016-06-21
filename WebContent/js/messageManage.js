$("#unread").on("click",function(){
	if ($("#read").hasClass("tab-btn-active")) {
		$("#read").removeClass("tab-btn-active");
		$("#pass-table").hide();
	}
	if ($("#delete").hasClass("tab-btn-active")) {
		$("#delete").removeClass("tab-btn-active");
		$("#delete-table").hide();
	}
	
	$("#unread").removeClass();
	$("#unread").addClass("tab-btn tab-btn-active");
	$("#plan-table").empty();
	$("#plan-table").append("<tr class='tableTr'><th width='280px'>日期</th><th width='280px'>内容</th><th width='280px'>已读</th><th width='100px'>删除</th></tr>");
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/MesUnreadGet",
                data:{ 'name':"xcc"},
                success:function(result,textStatus){
                    var cart_list=result.message;
                    var p=0;
                    $.each(cart_list,function(idx,item){
                    	$("#plan-table").append("<tr><td >"+item.draftdate+"</td><td >"+item.content+"</td><td><a class=\"unreadmessage-btn-read\" id=\""+item.id + "-read\"><img src=\"../img/check transparent.png\"></a></td><td><a class=\"unreadmessage-btn-delete\" id=\""+item.id + "-delete\"><img src=\"../img/delete2.png\"></a></td></tr>");
                    	p++;
                    });
                    if(p==0)
                    {
                    	$("#plan-table").append("<tr><td colspan='6'>暂无未读消息</td></tr>");
                    }
                }
	
});
	$("#plan-table").show();
	$("#delete-table").hide();
	$("#pass-table").hide();
	$("body").css("overflow","hide");
});

$("#read").on("click",function(){
	if ($("#unread").hasClass("tab-btn-active")) {
		$("#unread").removeClass("tab-btn-active");
		$("#plan-table").hide();
	}
	if ($("#delete").hasClass("tab-btn-active")) {
		$("#delete").removeClass("tab-btn-active");
		$("#delete-table").hide();
	}
	$("#read").removeClass();
	$("#read").addClass("tab-btn tab-btn-active");
	$("#pass-table").empty();
	$("#pass-table").append("<tr class='tableTr'><th width='280px'>日期</th><th width='280px'>内容</th><th width='100px'>删除</th></tr>");
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/MesReadGet",
                data:{ 'name':"xcc"},
                success:function(result,textStatus){
                    var cart_list=result.message;
                    var p=0;
                    $.each(cart_list,function(idx,item){
                    	$("#pass-table").append("<tr><td >"+item.draftdate+"</td><td >"+item.content+"</td><td><a class=\"readmessage-btn-delete\" id=\""+item.id + "-delete\"><img src=\"../img/delete2.png\"></a></td></tr>");
                    	p++;
                    });
                    if(p==0)
                    {
                    	$("#pass-table").append("<tr><td colspan='6'>暂无已读消息</td></tr>");
                    }
                }
	
});
	$("#pass-table").show();
	$("#delete-table").hide();
	$("#plan-table").hide();
	$("body").css("overflow","hide");
});
$("#delete").on("click",function(){
	if ($("#read").hasClass("tab-btn-active")) {
		$("#read").removeClass("tab-btn-active");
		$("#pass-table").hide();
	}
	if ($("#unread").hasClass("tab-btn-active")) {
		$("#unread").removeClass("tab-btn-active");
		$("#plan-table").hide();
	}
	$("#delete").removeClass();
	$("#delete").addClass("tab-btn tab-btn-active");
	$("#delete-table").empty();
	$("#delete-table").append("<tr class='tableTr'><th width='280px'>日期</th><th width='280px'>内容</th><th width='280px'>彻底删除</th><th width='100px'>还原</th></tr>");
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/MesDeleteGet",
                data:{ 'name':"xcc"},
                success:function(result,textStatus){
                    var cart_list=result.message;
                    var p=0;
                    $.each(cart_list,function(idx,item){
                    	$("#delete-table").append("<tr><td >"+item.draftdate+"</td><td >"+item.content+"</td><td><a class=\"deletemessage-btn-delete\" id=\""+item.id + "-read\"><img src=\"../img/delete2.png\"></a></td><td><a class=\"deletemessage-btn-return\" id=\""+item.id + "-delete\"><img src=\"../img/return.png\"></a></td></tr>");
                    	p++;
                    });
                    if(p==0)
                    {
                    	$("#delete-table").append("<tr><td colspan='6'>暂无已删除消息</td></tr>");
                    }
                }
	
});
	$("#delete-table").show();
	$("#plan-table").hide();
	$("#pass-table").hide();
	$("body").css("overflow","hide");
});



$(document).on("click",".unreadmessage-btn-read",function(){
	var button_id=$(this).attr("id");
	var id=button_id.split("-")[0];
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/ReadMessage",
        data:{'id':id},
        success:function(result,textStatus){
            	var isSuccess=result.success;
            	if(isSuccess){
            		$("#"+button_id).children("img").attr("src","../img/check.png");
            		$("#"+button_id).attr("disable","true");
            	}else{
            		alert(result.message);
            	}
        }
    });
});

$(document).on("click",".unreadmessage-btn-delete",function(){
	var button_id=$(this).attr("id");
	var id=button_id.split("-")[0];
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/MessageDel",
        data:{'id':id},
        success:function(result,textStatus){
            	var isSuccess=result.success;
            	if(isSuccess){
            		$("#"+button_id).children("img").attr("src","../img/check.png");
            		$("#"+button_id).attr("disable","true");
            	}else{
            		alert(result.message);
            	}
        }
    });
	
});
$(document).on("click",".readmessage-btn-delete",function(){
	var button_id=$(this).attr("id");
	var id=button_id.split("-")[0];
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/MessageDel",
        data:{'id':id},
        success:function(result,textStatus){
            	var isSuccess=result.success;
            	if(isSuccess){
            		$("#"+button_id).children("img").attr("src","../img/check.png");
            		$("#"+button_id).attr("disable","true");
            	}else{
            		alert(result.message);
            	}
        }
    });
	
});

$(document).on("click",".deletemessage-btn-delete",function(){
	var button_id=$(this).attr("id");
	var id=button_id.split("-")[0];
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/MesEmptyOne",
        data:{'id':id},
        success:function(result,textStatus){
            	var isSuccess=result.success;
            	if(isSuccess){
            		$("#"+button_id).children("img").attr("src","../img/check.png");
            		$("#"+button_id).attr("disable","true");
            	}else{
            		alert(result.message);
            	}
        }
    });
	
});


