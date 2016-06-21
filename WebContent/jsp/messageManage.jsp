<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.message.MessageInfoResultVO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage</title>
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="../css/myCss.css">

<%
	ServletContext sc = request.getServletContext();
	
    List<MessageInfoResultVO> unreadList = (List<MessageInfoResultVO>) sc.getAttribute("unread_message");
    List<MessageInfoResultVO> readList = (List<MessageInfoResultVO>) sc.getAttribute("read_message");
    List<MessageInfoResultVO> deleteList = (List<MessageInfoResultVO>) sc.getAttribute("delete_message");
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
		    <a class="nav-btn" id="plan-manage" href="/Desserthouse/E_ManagePlan">计划管理</a>
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">消息管理</a>
		    
		</div>

		<a class="manage-btn" href="/Desserthouse/Logout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" id="unread" href="javascript:void(0)">未读消息</a>
			<a class="tab-btn" id="read" href="javascript:void(0)">已读消息</a> 
			<a class="tab-btn" id="delete" href="javascript:void(0)">已删除消息</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div style="height: 1px"></div>
				<table class="book-table" id="plan-table" border="0">
					<tr class="tableTr">
						<th width="280px">日期</th>
						<th width="280px">内容</th>
						<th width="100px">已读</th>
						<th width="100px">删除</th>
						
					</tr>
				</table>
				<table class="book-table" id="pass-table" border="0" style="display: none">
					<tr class="tableTr">
					    <th width="280px">日期</th>
						<th width="280px">内容</th>
						<th width="100px">删除</th>
						
					</tr>
				</table>
				<table class="book-table" id="delete-table" border="0" style="display: none">
					<tr class="tableTr">
					    <th width="280px">日期</th>
						<th width="280px">内容</th>
						<th width="180px">彻底删除</th>
						<th width="100px">还原</th>
						
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/messageManage.js"></script>
</body>
</html>