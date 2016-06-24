<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ch_Zn">
<head>
<meta charset="UTF-8">
<title>Dessert</title>
<link rel="stylesheet" href="./css/reset.css">
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/login.css">
<link rel="stylesheet" href="./css/myCss.css">

<script type="text/javascript" src="./js/jquery-2.1.4.min.js"></script>
</head>
<body>

	<div class="welcome" id="first-show">
		<div class="top-part">
			<img class="img-logo" src="./img/logo.png" alt="logo"> <img
				class="welcome-macarons" src="./img/macarons.png" alt="logo">
		</div>
		<div class="bottom-part">
			<p>“从这里, 开启甜蜜之旅”</p>
		</div>
	</div>
	<script>
	var showNum=sessionStorage.getItem("showNum");
	if(showNum==null)
	{
		sessionStorage.setItem("showNum","already");
		var lastVist=localStorage.getItem("lastVist");
		if(lastVist==null)
		{
			sessionStorage.setItem("lastLogOrReg","log");
		}
		else
		{
			window.location.href="./jsp/employeeLogin.jsp";
		}
	}
	else
	{
		myFirstShow=document.getElementById("first-show");
		myFirstShow.style.display="none";
		localStorage.removeItem("lastVist");
	}
	
	</script>
	<img src="./img/logo.png" alt="logo" class="img-logo">
	<div class="toolkit">
		<a href="./jsp/employeeLogin.jsp" id="worker-login">工作人员<img
			src="./img/goto.png" alt="进入" id="img-goto"></a>
	</div>
	<div class="center-block">
		<div class="wrapper">
			<p id="welcome">Welcome</p>
			<form action="/Desserthouse/Login" method="post" id="user-login">
				<input maxlength="20" type="text" class="form-control" id="name" name="name"
					placeholder="用户名/会员卡号" aria-describedby="sizing-addon1"> <input maxlength="20"
					type="password" class="form-control" id="password" name="password"
					placeholder="密码" aria-describedby="sizing-addon1">

				<div class="register-wrapper">
					<input maxlength="20" type="password" class="form-control" id="password-second"
						placeholder="再次输入密码" aria-describedby="sizing-addon1">
					<a href="#" id="a-login">登陆</a>
					<div class="btn btn-default" id="signup">注&nbsp&nbsp&nbsp册</div>
				</div>
				<div class="login-wrapper">
					<a href="#" id="a-register">注册</a> <div
						class="btn btn-default login-btn" id="login"
						>登&nbsp&nbsp&nbsp陆</div>
				</div>
			</form>
			<div id="message">
				<%
					String message = (String) request.getServletContext().getAttribute("message");
					if (message != null) {
						message="错误:"+message;
						request.getServletContext().removeAttribute("message");
				%>
				<div class="alert alert-danger alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true" style="font-size: 20">&times;</button>
					<%=message%>
				</div>

				<%
					}
				%>
			</div>
			<form action="/Desserthouse/Signup" method="post" id="user-signup">
				<input type="text" id="signup-name" name="name"
					style="display: none;"> <input type="text"
					id="signup-password" name="password" style="display: none;">
			</form>
		</div>
	</div>
	<img src="./img/login-dessert.png" alt="dessert" id="img-dessert">
	
<script src="./js/bootstrap.js"></script>
<script type="text/javascript" src="./js/login.js"></script>
</body>
<script>

</script>


</html>