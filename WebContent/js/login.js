$(document).ready(function (){
	// $('.welcome-macarons').css("display","none");
	// $('.welcome p').css("display","none");

	// setTimeout(function() {
	// 	$('.welcome-macarons').fadeIn(100);
	// },10);
	// setTimeout(function() {
	// 	$('.welcome p').fadeIn(100);
	// },11);
	$(".register-wrapper").hide();
	if(sessionStorage.getItem("lastLogOrReg")=="reg")
	{
		$(".login-wrapper").hide();
		$(".register-wrapper").show();
	}
	setTimeout(function ()
	{
	    $('.top-part').animate(
	    {
	        top: "-=65%"
	    }, 1000,function() {
	    	$('.welcome').remove();
	    });

	}, 2000);
	setTimeout(function ()
	{
	    $('.bottom-part').animate(
	    {
	        top: "+=35%"
	    }, 1000);

	}, 1999);
	

});

$("#a-register").click(function() {
	sessionStorage.setItem("lastLogOrReg","reg");
	$(".login-wrapper").hide();
	$(".register-wrapper").show(600);
	$("#message").hide();
});

$("#a-login").click(function() {
	sessionStorage.setItem("lastLogOrReg","log");
	$(".login-wrapper").show(600);
	$(".register-wrapper").hide();
});

$("#login").click(function() 
{
	playWait("#login");
	$("#user-login").submit();
});

function playWait(widget)
{
	$(widget).html("<div class='loadShow'><img class='loadImg' src='./img/load.png' alt='O'></div>");
	$(widget).attr("disabled", true);
}

$("#signup")
		.click(
				function() {
					var username = $("#name").val();
					var password = $("#password").val();
					var passwordTwice = $("#password-second").val();
					if (username == "") {
						$("#message").show(600);
						$("#message")
								.html(
										"<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>用户名不能为空</div>");
						return;
					}
					if (password == "") {
						$("#message").show(600);
						$("#message")
								.html(
										"<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>密码不能为空</div>");
						return;
					}
					if (passwordTwice == "") {
						$("#message").show(600);
						$("#message")
								.html(
										"<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请再次输入密码</div>");
						return;
					}
					if (password != passwordTwice) {
						$("#message").show(600);
						$("#message")
								.html(
										"<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入密码不一致，请再次确认</div>");
						return;
					}
					$("#signup-name").val(username);
					$("#signup-password").val(password);
					playWait("#signup");
					$("#user-signup").submit();
				});
/*$("#login")
.mouseover(
		function() 
		{
			var username = $("#name").val();
			var password = $("#password").val();
			if (username == "") {
				//$("#login").attr("disabled", true);
				$("#name").css("border","3px solid red");
			}
			if (password == "") {
				//$("#login").attr("disabled", true);
				$("#password").css("border","3px solid red");
			}
		});
$("#login")
.mouseout(
		function() 
		{
			$("#name").css("border","none");
			$("#password").css("border","none");
		});*/