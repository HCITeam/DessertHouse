<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ch_Zn">
<head>
<meta charset="UTF-8">
<title>Dessert</title>
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/login.css">
<link rel="stylesheet" href="../css/myCss.css">

<script src="../js/jquery-2.1.4.min.js"></script>
</head>
<script>
	localStorage.setItem("lastVist","employee");
	
</script>
<body>

	<img src="../img/logo.png" alt="logo" class="img-logo">
	<div>
		<a href="../index.jsp" id="worker-login">普通会员<img
			src="../img/goto.png" alt="进入" id="img-goto"></a>
	</div>

	<div class="center-block">
		<div class="wrapper">
			<p id="welcome">Welcome</p>
			<form action="/Desserthouse/EmploeeLogin" method="post" id="workerPost">
				<input maxlength='10' type="text" class="form-control" id="name" name="name"
					placeholder="工作人员名" aria-describedby="sizing-addon1"> <input maxlength='10'
					type="password" class="form-control" id="password" name="password"
					placeholder="密码" aria-describedby="sizing-addon1">

					<br/>
				<div class="login-wrapper">
					<div class="btn btn-default login-btn" type="submit" id="workerLogin"
						>登&nbsp&nbsp&nbsp录</div>
				</div>
			</form>
			<div id="message">
				<%
					String message = (String) request.getServletContext().getAttribute("message");
					if (message != null) {
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
		</div>
	</div>
	<img src="../img/login-dessert.png" alt="dessert" id="img-dessert">

	<script src="../js/bootstrap.js"></script>
	<script src="../js/loginworker.js"></script>
</body>
</html>