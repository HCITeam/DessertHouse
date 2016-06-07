<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.plan.PlanInfoResultVO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage</title>
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<%
	ServletContext sc = request.getServletContext();
	Map<Integer, String> store = (Map<Integer, String>) sc.getAttribute("store_list");
	List<PlanInfoResultVO> impassList = (List<PlanInfoResultVO>) sc.getAttribute("impass_plan");
	List<PlanInfoResultVO> passList = (List<PlanInfoResultVO>) sc.getAttribute("pass_plan");
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">计划审批</a>
		    <a class="nav-btn" id="member-record" href="javascript:void(0)">会员统计</a>
		    <a class="nav-btn" id="sale-record" href="javascript:void(0)">销售统计</a>
		</div>

		<a class="manage-btn" href="/Desserthouse/Logout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" id="impass" href="javascript:void(0)">未审批计划</a>
			<a class="tab-btn" id="pass" href="javascript:void(0)">已审批计划</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div style="height: 1px"></div>
				<%
					if (impassList.size() <= 0) {
				%>
				<form class="info-form">
					<ul>
						<li><span>当前没有未通过审批的计划项</span></li>
					</ul>
				</form>
				<%
					} else {
				%>
				<table class="book-table" id="plan-table" border="1">
					<tr>
						<th>日期</th>
						<th>店面</th>
						<th>商品名</th>
						<th>数量</th>
						<th>价格</th>
						<th>通过</th>
					</tr>
					<%
						for (int i = 0; i < impassList.size(); i++) {
					%>
					<tr>
						<td><%=impassList.get(i).getDate()%></td>
						<td><%=store.get(impassList.get(i).getS_id())%></td>
						<td><%=impassList.get(i).getP_name()%></td>
						<td><%=impassList.get(i).getP_num() %></td>
						<td><%=impassList.get(i).getPrice() %></td>
					    <td><a class="plan-btn-pass" id="<%=impassList.get(i).getId() + "-pass"%>"><img
								src="../img/check transparent.png"></a></td>
					</tr>
					<%
						    }
						}
					%>
				</table>
				
				<%
					if (passList.size() <= 0) {
				%>
				<form class="info-form">
					<ul>
						<li><span>当前已审批的计划项</span></li>
					</ul>
				</form>
				<%
					} else {
				%>
				<table class="book-table" id="pass-table" border="1" style="display: none">
					<tr>
						<th>日期</th>
						<th>店面</th>
						<th>商品名</th>
						<th>数量</th>
						<th>价格</th>
					</tr>
					<%
						for (int i = 0; i < passList.size(); i++) {
					%>
					<tr>
						<td><%=passList.get(i).getDate()%></td>
						<td><%=store.get(passList.get(i).getS_id())%></td>
						<td><%=passList.get(i).getP_name()%></td>
						<td><%=passList.get(i).getP_num() %></td>
						<td><%=passList.get(i).getPrice() %></td>
					</tr>
					<%
						    }
						}
					%>
				</table>
			</div>
		</div>
	</div>
	<form action="/Desserthouse/Statistics" method="post" id="form-statistics">
		<input type="text" id="input-month-m" name="month" style="display: none;"> 
	</form>
	<form action="/Desserthouse/StoreRecord" method="post" id="form-record">
		<input type="text" id="input-month-s" name="month" style="display: none;"> 
		<input type="text" id="input-sid-s" name="s_id" style="display: none;">
	</form>
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/planManage.js"></script>
</body>
</html>