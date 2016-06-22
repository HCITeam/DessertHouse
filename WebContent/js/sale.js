var amount=0.0;
var integral=0;
var grade=0;
var nid=0;

$(document).on("click","#tab-card",function(){
	if ($("#tab-cash").hasClass("tab-btn-active")) {
		$("#tab-cash").removeClass("tab-btn-active");
		$("#cash-form").hide();
	}

	$("#tab-card").removeClass();
	$("#tab-card").addClass("tab-btn tab-btn-active");
	$("#card-form").show();
});

$(document).on("click","#tab-cash",function(){
	if ($("#tab-card").hasClass("tab-btn-active")) {
		$("#tab-card").removeClass("tab-btn-active");
		$("#card-form").hide();
	}

	$("#tab-cash").removeClass();
	$("#tab-cash").addClass("tab-btn tab-btn-active");
	$("#cash-form").show();
});

$(document).on("change","#pnum-card",function(){
	var price = $("#pname-card").val();
	var	p_name = $("#pname-card option:checked").text();
	var p_num =  $(this).val();
//	
	$(".sale-table").append("<tr>"+
		                          "<td>"+p_name+"</td>"+
		                          "<td>"+price+"</td>"+
		                          "<td>"+p_num+"</td>"+
		                    "</tr>");
	amount=amount+Number(price)*Number(p_num);//计算总价
	var temp=(amount/10.0)*grade;
	integral=parseInt(temp);
	$("#total-card").html(amount);
	$("#integral-card").html(integral);
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddCommodity",
                data:{'p_name':p_name,'p_num':p_num,'price':price},
                success:function(result,textStatus){
//                   alert(result.success);
                }
            });
});

$("#addGood").click(function()
{
	var price = $("#newPrice").val();
	var	p_name = $("#new-pname-card option:checked").text();
	var p_num = $("#newNum").val();
	var p_allPrice=price*p_num;
	nid++;
	$("#lastLine").before("<tr id='"+nid+"-tr' class='tableBottomTr'>\
						<td id='"+nid+"-name'>"+p_name+"</td>\
						<td>"+price+"</td>\
					    <td>"+p_num+"</td>\
						<td>"+p_allPrice+"</td>\
						<td><img id='"+nid+"-del' class='store-btn-delete delGood' src='../img/delete2.png'></td>\
						</tr>");
	
	amount=amount+Number(price)*Number(p_num);//计算总价
	var temp=(amount/10.0)*grade;
	integral=parseInt(temp);
	$("#allnum").html(amount);
	//$("#integral-card").html(integral);
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddCommodity",
                data:{'p_name':p_name,'p_num':p_num,'price':price},
                success:function(result,textStatus){
//                   alert(result.success);
                }
            });
});

$(document).on("click",".delGood",function(){
	var id=$(this).attr("id").split("-")[0];
	$("#"+id+"-tr").hide(500);
});

$(document).on("change","#pnum-cash",function(){
	var price = $("#pname-cash").val();
	var	p_name = $("#pname-cash option:checked").text();
	var p_num =  $(this).val();
	$(".sale-table").append("<tr>"+
		                          "<td>"+p_name+"</td>"+
		                          "<td>"+price+"</td>"+
		                          "<td>"+p_num+"</td>"+
		                    "</tr>");
	amount=amount+Number(price)*Number(p_num);//计算总价
	$("#amount-cash").html(amount);
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/AddCommodity",
                data:{'p_name':p_name,'p_num':p_num,'price':price},
                success:function(result,textStatus){
                   alert(result.success);
                }
            });
});


$(document).on("change","#mpass",function(){
	var id = $("#mid").val();
	var password =  $(this).val();
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/CheckMember",
                data:{'id':id,'password':password},
                success:function(result,textStatus){
                   if (result.success==1) {
                   	  $("#balance-card").html(result.balance);
                   	  grade=result.grade;
                   	  if (result.state!=1) {
                   	  	alert(result.message);
                   	  }
                   }else{
                   	alert(result.message);
                   }
                }
            });
});


$(document).on("change","#total-cash",function(){
	var total = $(this).val();
	if (total<amount) {
		alert("金额不足");
	}else{
		var charge=total-amount;
		$("#charge-cash").html(charge);
	}
});


$(".close-btn").on("click",function(){
	   $(".modal-wrapper").hide();
	   $("body").css("overflow","auto");
	});

$("#doSale").click(function()
{
	$(".modal-wrapper").show();
	$("body").css("overflow","hide");
});

$("#commit-card").click(function(){
//	alert("ddd");
//	return;	
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/PayByCard",
                data:{},
                success:function(result,textStatus){    
                   	alert(result.message);
                   }

            });
});

$("#commit-cash").click(function(){
	
	$.ajax({
                type:"POST",
                url:"/Desserthouse/api/PayByCash",
                data:{},
                success:function(result,textStatus){    
                   	alert(result.message);
                   }

            });
});

$(".reset-btn").click(function(){
	$("#input-id").val("");
	$("#form-reset").submit();
});
