<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.plan.PlanInfoResultVO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Book</title>
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<%
	ServletContext sc = request.getServletContext();
	Map<Integer, String> store = (Map<Integer, String>) sc.getAttribute("store_list");
	List<PlanInfoResultVO> impassList = (List<PlanInfoResultVO>) sc.getAttribute("impass_plan");
	
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">计划审批</a>
		    <a class="nav-btn" href="javascript:void(0)">会员统计</a>
		</div>

		<a class="manage-btn" href="javascript:void(0)"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">未通过计划</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div style="height: 1px"></div>
				
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/planManage.js"></script>
</body>
</html>