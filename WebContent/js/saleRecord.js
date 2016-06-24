/**
 * 
 */
var nowIn=1;
$("#left").click(function()
{
	if(nowIn==1)return;
	nowIn=1;
	$("#showLable").hide(200);
	$("#left").css("color","grey");
	$("#right").css("color","#6495ED");
	var data = {
			labels : ["派","蛋糕","饼","面包","蛋挞","卷","饮料"],
			datasets : [
				{
					fillColor : "rgba(220,220,220,0.5)",
					strokeColor : "rgba(220,220,220,1)",
					pointColor : "rgba(220,220,220,1)",
					pointStrokeColor : "#fff",
					data : [65,59,90,81,56,55,40]
				},
				{
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "rgba(151,187,205,1)",
					pointStrokeColor : "#fff",
					data : [28,48,40,19,96,27,100]
				}
			]
		}
		var ctx = document.getElementById("myChart").getContext("2d");
		new Chart(ctx).Radar(data);
});

$("#right").click(function()
{
	if(nowIn==2)return;
	nowIn=2;
	$("#showLable").show(200);
	$("#right").css("color","grey");
	$("#left").css("color","#6495ED");
	var data = [
	        	{
	        		value : 70,
	        		color: "#D97041"
	        	},
	        	{
	        		value : 90,
	        		color: "green"
	        	},
	        	{
	        		value : 64,
	        		color: "#21323D"
	        	},
	        	{
	        		value : 58,
	        		color: "#9D9B7F",
	        		label:"123"
	        	}
	        ]
		var ctx = document.getElementById("myChart").getContext("2d");
		new Chart(ctx).PolarArea(data);
});
$("#tool-btn-check").click(function(){
	var month=$("#month").val();
	var id=$("#s_id").val();
	
	$("#input-month-s").val(month);
	$("#input-sid-s").val(id);
	$("#form-record").submit();
});

$("#member-record").click(function(){
	var month=2;
	
	$("#input-month-m").val(month);
	$("#form-statistics").submit();
});

$("#sendMessageDo").click(function()
{
	$("#showMap").slideUp(200);
	$("#sendMessageDo").slideUp(200);
	$("#sender").slideDown(200);
});

$("#showMap").click(function(){
	   $(".modal-wrapper").show();
	   $("body").css("overflow","hidden");
});

$("#sendMessageBack").click(function()
{
	$("#sendMessageDo").slideDown(200);
	$("#showMap").slideDown(200);
	$("#sender").slideUp(200);
});
$(".close-btn").on("click",function(){
	   $(".modal-wrapper").hide();
	   $("body").css("overflow","auto");
	});
$("#sendMessage").click(function(){
	if($(this).attr("disabled")=="disabled") return;
	var message = $("#input-message").val();
	var s_name = $("#server").val();
	if(message=="")
	{
		$("#input-message").focus();
		$("#input-message").attr("placeholder","请输入消息");
		return;
	}
	$("#sendMessage").html("<div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div>");
	$("#sendMessage").attr("disabled","disabled");
	console.log("here");

	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/SendMessage",
        data:{
        	'content': message,
        	'name': s_name
        	},
        success:function(result,textStatus){
        	$("#sendMessage").html("发送");
        	$("#sendMessage").removeAttr("disabled");
            	var isSuccess=result.success;
            	$("#sendOver").html(result.message);
            	$("#sendOver").slideDown(200);
            	$("#sender").slideUp(200);
            	setTimeout(function()
            	{
            		$("#sendOver").slideUp(200);
                	$("#sender").slideDown(200);
            	},600); 
        }
    });
})