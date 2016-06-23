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
<link rel="stylesheet" type="text/css" href="../css/myCss.css">

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
				<table class="book-table" id="plan-table" border="0">
					<tr class="tableTr">
						<th width="280px">日期</th>
						<th width="280px">店面</th>
						<th width="280px">商品名</th>
						<th width="100px">数量</th>
						<th width="100px">价格</th>
						<th width="100px">通过</th>
					</tr>
					<%
						for (int i = 0; i < impassList.size(); i++) {
					%>
					<tr class="tableBottomTr">
						<td><%=impassList.get(i).getDate()%></td>
						<td><%=store.get(impassList.get(i).getS_id())%></td>
						<td><%=impassList.get(i).getP_name()%></td>
						<td><%=impassList.get(i).getP_num() %></td>
						<td><%=impassList.get(i).getPrice() %></td>
					    <td><a class="plan-btn-pass" id="<%=impassList.get(i).getId() + "-pass"%>"><img class='autoMove'
								src="../img/check transparent.png"></a></td>
					</tr>
					<%
						    }

						if(impassList.size()<=0)
						{
					%>
					<tr class="tableBottomTr" id="addStoreLine">
						<td colspan="6"> 暂无未审批计划</td>
					</tr>
					<% 
						}
					%>
				</table>
				<table class="book-table" id="pass-table" border="0" style="display: none">
					<tr class="tableTr">
						<th width="280px">日期</th>
						<th width="280px">店面</th>
						<th width="280px">商品名</th>
						<th width="100px">数量</th>
						<th width="100px">价格</th>
					</tr>
					<%
						for (int i = 0; i < passList.size(); i++) {
					%>
					<tr class="tableBottomTr">
						<td><%=passList.get(i).getDate()%></td>
						<td><%=store.get(passList.get(i).getS_id())%></td>
						<td><%=passList.get(i).getP_name()%></td>
						<td><%=passList.get(i).getP_num() %></td>
						<td><%=passList.get(i).getPrice() %></td>
					</tr>
					<%
						}
						if(passList.size()<=0)
						{
					%>
					<tr class="tableBottomTr" id="addStoreLine">
						<td colspan="6"> 暂无已审批计划</td>
					</tr>
					<% 
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