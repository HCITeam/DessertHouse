<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Person</title>
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../css/reset.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/myCss.css">
	<%
		ServletContext sc = request.getServletContext();
	    int success = (int) sc.getAttribute("success");
		String message=(String)sc.getAttribute("message");
	%>
</head>
<body>
	
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="/Desserthouse/ShowMemberInfo">个人资料</a>
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">账户管理</a>
			<a class="nav-btn" href="/Desserthouse/MemberRecord">账单</a>
		</div>
		<a class="large-manage-btn" href="/Desserthouse/ShowInventory"> <span>预定商品</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>

    <div class="toolkit">
		<a href="/Desserthouse/Logout">登出<img
			src="../img/goto.png" alt="进入" id="img-goto"></a>
	</div>

	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">账号信息</a>
		</div>
		<div class="content">
			<div class="wrapper">
				<div style="height: 1px"></div>
				<form class="info-form recharge-content">
				<ul>
					<%
					if(success==0){   
						%><li><span>卡号:</span><span> 暂无数据</span></li>
						<li><span>用户名:</span><span> 暂无数据</span></li>
						<li><span>等级:</span><span> 暂无数据</span></li>
						<li><span>积分:</span><span id="span-integral"> 暂无数据</span></li>
						<li><span>状态:</span><span> 暂无数据</span></li>
						<li><span>余额:</span><span id="span-balance"> 暂无数据</span></li>
						<li><span>累计:</span><span> 暂无数据</span></li>
						<li><span>银行卡:</span><input maxlength='15' type="text" id="bankcard" placeholder="请输入银行卡号"></li>
						<li><input maxlength='15' class="btn modify-btn" id="modify-bankcard" value="绑定银行卡"></li>
						<li><input maxlength='5' class="btn modify-btn" id="modify-recharge" value="充值"></li>
					<%}else{
						String id = (String) sc.getAttribute("id");
						String name = (String) sc.getAttribute("name");
						String balance=(String) sc.getAttribute("balance");
						String state = (String) sc.getAttribute("state");
						String total=(String) sc.getAttribute("total");
						int grade = (int) sc.getAttribute("grade");
						int integral = (int) sc.getAttribute("integral");
						String bankcard = (String) sc.getAttribute("bankcard");
						%><li><span>卡号:</span><span class='span-right'> <%=id %></span></li>
						<li><span>用户名:</span><span class='span-right'> <%=name %></span></li>
						<li><span>等级:</span><span class='span-right'> <%=grade %></span></li>
						<li><span>积分:</span><span  class='span-right' id="span-integral"> <%=integral %></span><input maxlength='5' class="btn confirm-btn " id="modify-integral" value="兑现"></li>
						<li><span>状态:</span><span class='span-right'> <%=state %></span></li>
						<li><span>余额:</span><span class='span-right' id="span-balance"> <%=balance %></span></li>
						<li><span>累计:</span><span class='span-right'> <%=total %></span></li>
						<%if(bankcard.equals(" ")){ 
						%><li><span>银行卡:</span><input maxlength='15' type="text" id="bankcard" placeholder="请输入银行卡号"></li>
						<li><span><input maxlength='15' class="btn confirm-btn" id="modify-bankcard" value="绑定"></span>
						<%
						}else{ %><li><span>银行卡:</span><input maxlength='15' type="text" class="long-input-td" id="bankcard" value="<%=bankcard %>"></li>
						<li><span><div class="btn confirm-btn cardBottom-btn" id="modify-bankcard">修改</div></span>
			             <%}%>
						<span><div class="btn confirm-btn cardBottom-btn" id="modify-recharge">充值</div></span></li>
					<%} %>
					</ul>
					<div class="message"></div>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="modal-wrapper" style="display: none;">
		<div class="dialog recharge-dialog">
			<div class="dialog-title">充值</div>
			<a class="close-btn" href="javascript:void(0)"><b></b></a>
			<div style="height: 80px"></div>
			<div class="amount-input">
				<input maxlength='5' type="text" class="short-input-td" id="amount" placeholder="至少200方可激活">
				
				<a class="btn confirm-btn" id="confirm-recharge" href="javascript:void(0)">确认</a>
			</div>
			<div class="dialog-message"></div>
			<div class="clear"></div>
		</div>
	</div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
    <script src="../js/cardinfo.js"></script>
</body>
</html>