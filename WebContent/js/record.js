//第一个表格
$("#tab-first").on("click",function(){
	if ($("#tab-second").hasClass("tab-btn-active")) {
		$("#tab-second").removeClass("tab-btn-active");
		$("#table-second").hide();
	}

	if ($("#tab-third").hasClass("tab-btn-active")) {
		$("#tab-third").removeClass("tab-btn-active");
		$("#table-third").hide();
	}

	$("#tab-first").removeClass();
	$("#tab-first").addClass("tab-btn tab-btn-active");
	$("#table-first").show();
});
//第二个表格
$("#tab-second").on("click",function(){
	if ($("#tab-first").hasClass("tab-btn-active")) {
		$("#tab-first").removeClass("tab-btn-active");
		$("#table-first").hide();
	}

	if ($("#tab-third").hasClass("tab-btn-active")) {
		$("#tab-third").removeClass("tab-btn-active");
		$("#table-third").hide();
	}

	$("#tab-second").removeClass();
	$("#tab-second").addClass("tab-btn tab-btn-active");
	$("#table-second").show();
});
//第三个表格
$("#tab-third").on("click",function(){
	if ($("#tab-second").hasClass("tab-btn-active")) {
		$("#tab-second").removeClass("tab-btn-active");
		$("#table-second").hide();
	}

	if ($("#tab-first").hasClass("tab-btn-active")) {
		$("#tab-first").removeClass("tab-btn-active");
		$("#table-first").hide();
	}

	$("#tab-third").removeClass();
	$("#tab-third").addClass("tab-btn tab-btn-active");
	$("#table-third").show();
});