<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dessert.rvo.member.MemberRecordRVO" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Person</title>
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../css/reset.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<%
		ServletContext sc = request.getServletContext();
	List<MemberRecordRVO> allList=(List<MemberRecordRVO>)sc.getAttribute("all_record");
	List<MemberRecordRVO> posiList=(List<MemberRecordRVO>)sc.getAttribute("posi_record");
	List<MemberRecordRVO> negList=(List<MemberRecordRVO>)sc.getAttribute("neg_record");
	%>
</head>
<body>
	
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="/Desserthouse/ShowMemberInfo">个人资料</a>
			<a class="nav-btn" href="/Desserthouse/ShowCardInfo">账户管理</a>
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">账单</a>
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
			<a class="tab-btn tab-btn-active" id="tab-first" href="javascript:void(0)">全部信息</a>
			<a class="tab-btn" id="tab-second" href="javascript:void(0)">缴费信息</a>
			<a class="tab-btn" id="tab-third" href="javascript:void(0)">消费信息</a>
		</div>
		<div class="content">
			<div class="wrapper">
				<div style="height: 1px"></div>
				<%
					if (allList.size() <= 0) {
				%>
				<form class="info-form" id="table-first">
					<ul>
						<li><span>非常抱歉</span></li>
						<li><span>您尚无记录</span></li>
					</ul>
				</form>
				<%
					} else {
				%>
				<table class="book-table" id="table-first" border="1">
					<tr>
						<th>操作</th>
						<th>日期</th>
						<th>金额</th>
						<th>描述</th>
					</tr>
					<%
						for (int i = 0; i < allList.size(); i++) {
					%>
					<tr>
					<%if(allList.get(i).getType()==0||allList.get(i).getType()==3) {%>
					     <td><img src="../img/posi.png"></td>			
					<%}else {%><td><img src="../img/neg.png"></td>
					<%} %>
						<td><%=allList.get(i).getR_date() %></td>
						<td><%=allList.get(i).getAmount() %></td>
						<td><%=allList.get(i).getExplanation() %></td>
					</tr>
					<%
				     }
			}%>
				</table>
				
				<%
					if (posiList.size() <= 0) {
				%>
				<form class="info-form" id="table-second" style="display: none">
					<ul>
						<li><span>非常抱歉</span></li>
						<li><span>您尚无记录</span></li>
					</ul>
				</form>
				<%
					} else {
				%>
				<table class="book-table" id="table-second" border="1" style="display: none">
					<tr>
						<th>操作</th>
						<th>日期</th>
						<th>金额</th>
						<th>描述</th>
					</tr>
					<%
						for (int i = 0; i < posiList.size(); i++) {
					%>
					<tr>
					    <td><img src="../img/posi.png"></td>			
						<td><%=posiList.get(i).getR_date() %></td>
						<td><%=posiList.get(i).getAmount() %></td>
						<td><%=posiList.get(i).getExplanation() %></td>
					</tr>
					<%
				     }
			}%>
				</table>
				
				<%
					if (negList.size() <= 0) {
				%>
				<form class="info-form" id="table-third" style="display: none">
					<ul>
						<li><span>非常抱歉</span></li>
						<li><span>您尚无记录</span></li>
					</ul>
				</form>
				<%
					} else {
				%>
				<table class="book-table" id="table-third" border="1" style="display: none">
					<tr>
						<th>操作</th>
						<th>日期</th>
						<th>金额</th>
						<th>描述</th>
					</tr>
					<%
						for (int i = 0; i < negList.size(); i++) {
					%>
					<tr>
					    <td><img src="../img/posi.png"></td>
						<td><%=negList.get(i).getR_date() %></td>
						<td><%=negList.get(i).getAmount() %></td>
						<td><%=negList.get(i).getExplanation() %></td>
					</tr>
					<%
				     }
			}%>
				</table>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
    <script src="../js/record.js"></script>
</body>
</html>