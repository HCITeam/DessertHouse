<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.member.StatisticsRVO"%>
<%@ page import="java.text.NumberFormat" %>
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
	StatisticsRVO rvo = (StatisticsRVO) sc.getAttribute("statistics");
	int month=(int) sc.getAttribute("month");
	NumberFormat fmt = NumberFormat.getPercentInstance();  
    fmt.setMaximumFractionDigits(2);
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="/Desserthouse/ManagePlan">计划审批</a>
		    <a class="nav-btn nav-btn-active" id="member-record" href="javascript:void(0)">会员统计</a>
		    <a class="nav-btn" id="sale-record" href="javascript:void(0)">销售统计</a>
		</div>

		<a class="manage-btn" href="/Desserthouse/EmployeeLogout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">会员情况</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar">
					<a class="btn tool-btn" id="tool-btn-check" href="javascript:void(0)">查询</a>
					<select id="month">
					        <%if(month==2){ %>
							    <option value="2" selected="selected">二月</option>
					    		<option value="3">三月</option>
					    	<%}else{ %>
						       <option value="2">二月</option>
					    		<option value="3" selected="selected">三月</option>
						    <%} %>
					    
					</select>
					<div class="clear"></div>
				</div>
				<div style="height: 1px"></div>
	
				<table class="sale-table sale-little-table" border="1">
					<tr>
						<th>性别</th>
						<th>数量</th>
						<th>比例</th>
					</tr>
					<%
						for (int i = 0; i < rvo.getGenderString().length; i++) {
					%>
					<tr>
						<td><%=rvo.getGenderString()[i]%></td>
						<td><%=rvo.getGenderNum()[i]%></td>
						<td><%=fmt.format((double)rvo.getGenderNum()[i]/(double)rvo.getTotal())%></td>
					</tr>
					<%}%>
				</table>
				
				<table class="sale-table sale-little-table" border="1">
					<tr>
						<th>年龄段</th>
						<th>数量</th>
						<th>比例</th>
					</tr>
					<%
						for (int i = 0; i < rvo.getAgeNum().length; i++) {
					%>
					<tr>
						<td><%=rvo.getAgeString()[i]%></td>
						<td><%=rvo.getAgeNum()[i]%></td>
						<td><%=fmt.format((double)rvo.getAgeNum()[i]/(double)rvo.getTotal())%></td>
					</tr>
					<%}%>
				</table>
				
				<table class="sale-table sale-little-table" border="1">
					<tr>
						<th>地区</th>
						<th>数量</th>
						<th>比例</th>
					</tr>
					<%
						for (int i = 0; i < rvo.getAreaNum().length; i++) {
					%>
					<tr>
						<td><%=rvo.getAreaString()[i]%></td>
						<td><%=rvo.getAreaNum()[i]%></td>
						<td><%=fmt.format((double)rvo.getAreaNum()[i]/(double)rvo.getTotal())%></td>
					</tr>
					<%}%>
				</table>
				
				<table class="sale-table sale-little-table" border="1">
					<tr>
						<th>消费类型</th>
						<th>消费金额</th>
					</tr>
					<%
						for (int i = 0; i < rvo.getPurcharseNum().length; i++) {
					%>
					<tr>
						<td><%=rvo.getPurcharseString()[i]%></td>
						<td><%=rvo.getPurcharseNum()[i]%></td>
					</tr>
					<%}%>
				</table>
				
				<table class="sale-table sale-little-table" border="1">
					<tr>
						<th>卡状态</th>
						<th>数量</th>
						<th>比例</th>
					</tr>
					<%
						for (int i = 0; i < rvo.getStateNum().length; i++) {
					%>
					<tr>
						<td><%=rvo.getStateString()[i]%></td>
						<td><%=rvo.getStateNum()[i]%></td>
						<td><%=fmt.format((double)rvo.getStateNum()[i]/(double)rvo.getTotal())%></td>
					</tr>
					<%}%>
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
	<script src="../js/statistics.js"></script>
</body>
</html>