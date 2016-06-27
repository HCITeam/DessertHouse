<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Sale</title>
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../css/reset.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/myCss.css">
</head>
<body>
	
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="/Desserthouse/Sale">销售</a>
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">充值</a>
		</div>
		<a class="manage-btn" href="/Desserthouse/EmployeeLogout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>

	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" id="tab-card" href="javascript:void(0)">充值</a>
		</div>
		<div class="content">
			<div class="wrapper">
			<div style="height: 1px"></div>
				<form class="info-form recharge-content">
					<ul>
					 	<li><span>卡号:</span><input onkeypress='setOnlyNumber()' maxlength='15' class="myInput" type="text" id="mid" placeholder="会员卡号"></li>
						<li><span>金额:</span><input onkeypress='setOnlyNumber()' maxlength='5' class="myInput" type="text" id="amount" placeholder="充值金额"></li>
						<li><div class="btn modify-btn">充   值</div></li>
					</ul>
					<div class="message"></div>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
    <script src="../js/recharge.js"></script>
    <script src="../js/myJS.js"></script>
</body>
</html>