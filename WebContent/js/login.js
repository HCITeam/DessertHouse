$(document).ready(function (){
	// $('.welcome-macarons').css("display","none");
	// $('.welcome p').css("display","none");

	// setTimeout(function() {
	// 	$('.welcome-macarons').fadeIn(100);
	// },10);
	// setTimeout(function() {
	// 	$('.welcome p').fadeIn(100);
	// },11);
    
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
	
	$(".register-wrapper").hide();
});

$("#a-register").click(function() {
	$(".login-wrapper").hide();
	$(".register-wrapper").show(600);
});

$("#signup")
		.click(
				function() {
					var username = $("#name").val();
					var password = $("#password").val();
					var passwordTwice = $("#password-second").val();
					if (username == "") {
						$("#message")
								.html(
										"<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>用户名不能为空</div>");
						return;
					}
					if (password == "") {
						$("#message")
								.html(
										"<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>密码不能为空</div>");
						return;
					}
					if (passwordTwice == "") {
						$("#message")
								.html(
										"<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>请再次输入密码</div>");
						return;
					}
					if (password != passwordTwice) {
						$("#message")
								.html(
										"<div class=\"alert alert-danger alert-dismissable\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>输入密码不一致，请再次确认</div>");
						return;
					}
					$("#signup-name").val(username);
					$("#signup-password").val(password);
					$("#user-signup").submit();
				});