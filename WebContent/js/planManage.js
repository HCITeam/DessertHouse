$("#impass").on("click",function(){
	if ($("#pass").hasClass("tab-btn-active")) {
		$("#pass").removeClass("tab-btn-active");
		$("#pass-table").hide();
	}
	
	$("#impass").removeClass();
	$("#impass").addClass("tab-btn tab-btn-active");
	$("#plan-table").show();
	sessionStorage.removeItem("planLastSee");
});

$("#pass").on("click",function(){
	if ($("#impass").hasClass("tab-btn-active")) {
		$("#impass").removeClass("tab-btn-active");
		$("#plan-table").hide();
	}
	sessionStorage.setItem("planLastSee","pass");
	$("#pass").removeClass();
	$("#pass").addClass("tab-btn tab-btn-active");
	$("#pass-table").show();
});

var lastSee=sessionStorage.getItem("planLastSee");
if(lastSee=="pass")
{
	if ($("#impass").hasClass("tab-btn-active")) {
		$("#impass").removeClass("tab-btn-active");
		$("#plan-table").hide();
	}
	$("#pass").removeClass();
	$("#pass").addClass("tab-btn tab-btn-active");
	$("#pass-table").show();
}

$(document).on("click",".plan-btn-pass",function(){
	var button_id=$(this).attr("id");
	var p_id=button_id.split("-")[0];
	var hider=$(this).parent().parent();
	$(this).parent().html("<div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div>");
	$.ajax({
        type:"POST",
        url:"/Desserthouse/api/PassPlan",
        data:{'p_id':p_id},
        success:function(result,textStatus){
        	hider.hide(500);
        }
    });
});

$("#sale-record").click(function(){
	var month=2;
	var id=1;
		
	$("#input-month-s").val(month);
	$("#input-sid-s").val(id);
	$("#form-record").submit();
});

$("#member-record").click(function(){
	var month=2;
	
	$("#input-month-m").val(month);
	$("#form-statistics").submit();
});