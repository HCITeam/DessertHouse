$(document).on("click","#modify-memberid",function(){
	var id=$("#m_id").val();
	if(id==""||isNaN(id)){
		$(".message").html("<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入错误，请重新输入</div>");
		return;
	}
//	alert(id);
	$("#input-id").val(id);
	$("#user-id").submit();
});

$(document).on("click","#tab-first",function(){
	if ($("#tab-second").hasClass("tab-btn-active")) {
		$("#tab-second").removeClass("tab-btn-active");
		$("#page-second").hide();
	}

	if ($("#tab-third").hasClass("tab-btn-active")) {
		$("#tab-third").removeClass("tab-btn-active");
		$("#page-third").hide();
	}

	$("#tab-first").removeClass();
	$("#tab-first").addClass("tab-btn tab-btn-active");
	$("#page-first").show();
});

$(document).on("click","#tab-second",function(){
	if ($("#tab-first").hasClass("tab-btn-active")) {
		$("#tab-first").removeClass("tab-btn-active");
		$("#page-first").hide();
	}

	if ($("#tab-third").hasClass("tab-btn-active")) {
		$("#tab-third").removeClass("tab-btn-active");
		$("#page-third").hide();
	}

	$("#tab-second").removeClass();
	$("#tab-second").addClass("tab-btn tab-btn-active");
	$("#page-second").show();
});

$(document).on("click","#tab-third",function(){
	if ($("#tab-first").hasClass("tab-btn-active")) {
		$("#tab-first").removeClass("tab-btn-active");
		$("#page-first").hide();
	}

	if ($("#tab-second").hasClass("tab-btn-active")) {
		$("#tab-second").removeClass("tab-btn-active");
		$("#page-second").hide();
	}

	$("#tab-third").removeClass();
	$("#tab-third").addClass("tab-btn tab-btn-active");
	$("#page-third").show();
});



