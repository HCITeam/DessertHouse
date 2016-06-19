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
		int area=0;
		if(success==1){
			area=(int)sc.getAttribute("area");
		}
	%>
</head>
<body>
	
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" id="nav-btn-active" href="javascript:void(0)">个人资料</a>
			<a class="nav-btn" href="/Desserthouse/ShowCardInfo">账户管理</a>
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
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">个人资料</a>
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar">
					<a class="btn tool-btn setNotShow" id="tool-btn-cancel" href="javascript:void(0)">取消会员卡</a>
					<div class="clear"></div>
				</div>
				<!-- <div style="height: 1px"></div> -->
				<form class="info-form recharge-content">
				<ul>
					<%if(success==0){   
						%><li><span>称呼:</span><input class="setBackWhite" type="text" id="compellation" placeholder="用于派送预定商品"></li>
						<li><span>性别:</span> 男<input type="radio" name="gender" value="1">女<input type="radio" name="gender" value="0" checked="checked"></li>
						<li><span>生日:</span><input class="setBackWhite" type="date" id="birthday" placeholder="生日"></li>
						<li><span>送货地址:</span><input class="setBackWhite" type="text" id="address" placeholder="送货地址"></li>
						<li><span>联系电话:</span><input class="setBackWhite" type="text" id="phone" placeholder="联系方式"></li>
					<%}else{ 
						String compellation = (String) sc.getAttribute("compellation");
						String birthday = (String) sc.getAttribute("birthday");
						int gender = (int) sc.getAttribute("gender");
						String address = (String) sc.getAttribute("address");
						String phone = (String) sc.getAttribute("phone");
						%><li><span>称呼:</span><input class="setBackWhite" type="text" id="compellation" placeholder="<%=compellation%>"></li>
						<%if(gender==1){
						%><li><span>性别:</span> 男<input class="setBackWhite" type="radio" name="gender" value="1" checked="checked">女<input type="radio" name="gender" value="0"></li>
						<%}else { %>
						<li><span>性别:</span> 男<input class="setBackWhite" type="radio" name="gender" value="1">女<input type="radio" name="gender" value="0" checked="checked"></li>
						<%} %>
						<li><span>生日:</span><input class="setBackWhite" type="date" id="birthday" placeholder="<%=birthday%>"></li>
						<li><span>送货地址:</span>
						<select class="select-info setBackWhite" id="address-select">
							<option value="0">玄武区</option>
							<option value="1">鼓楼区</option>
							<option value="2">建邺区</option>
							<option value="3">秦淮区</option>
							<option value="4">雨花台区</option>
							<option value="5">浦口区</option>
							<option value="6">栖霞区</option>
							<option value="7">江宁区</option>
							<option value="8">六合区</option>
							<option value="9">溧水区</option>
							<option value="10">高淳区</option>
						</select>
						<input class="setBackWhite" type="text" id="address" placeholder="<%=address%>"></li>
						<li><span>联系电话:</span><input type="text" id="phone" placeholder="<%=phone%>"></li>
					<%} %>
						<li><input class="btn modify-btn" value="修改"></li>
					</ul>
				        <div class="message"></div>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
    <script src="../js/memberinfo.js"></script>
    <script type="text/javascript">
      $("#address-select").val(<%=area%>);
    </script>
</body>
</html>