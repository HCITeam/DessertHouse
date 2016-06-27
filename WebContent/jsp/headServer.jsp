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
<link rel="stylesheet" type="text/css" href="../css/myCss.css">
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
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">计划管理</a>
			<a class="nav-btn" id="message-manage" href="/Desserthouse/ManageMessage">消息管理</a>
		</div>

		<a class="manage-btn" href="/Desserthouse/EmployeeLogout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" id="impass" href="javascript:void(0)">未通过计划</a> 
			<a class="tab-btn" id="pass" href="javascript:void(0)">已通过计划</a> 
			<a class="tab-btn" id="delete" href="javascript:void(0)">已删除计划</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar">
					<a class="btn tool-btn" id="tool-btn-plan" href="javascript:void(0)">新增计划</a>
					<div class="clear"></div>
				</div>
				
				<table class="book-table" id="plan-table" border="0">
					<tr class="tableTr">
						<th width="350px">日期</th>
						<th width="250px">店面</th>
						<th width="280px">商品名</th>
						<th width="180px">数量</th>
						<th width="180px">价格</th>
						<th width="100px">编辑</th>
						<th width="100px">删除</th>
					</tr>
					<%
						for (int i = 0; i < impassList.size(); i++) {
					%>
					<tr class="tableBottomTr">
						<td><%=impassList.get(i).getDate()%></td>
						<td><%=store.get(impassList.get(i).getS_id())%></td>
						<td><%=impassList.get(i).getP_name()%></td>
						<td><input maxlength='5' disabled="disabled" type="text" class="short-input-td" id="<%=impassList.get(i).getId() + "-num"%>" value="<%=impassList.get(i).getP_num() %>"></td>
						<td><input maxlength='5' disabled="disabled" type="text" class="short-input-td" id="<%=impassList.get(i).getId() + "-price"%>" value="<%=impassList.get(i).getPrice() %>"></td>
					    <td><a class="store-btn-edit" id="<%=impassList.get(i).getId() + "-edit"%>"><img
								src="../img/edit.png"></a></td>
						<td id="<%=impassList.get(i).getId() + "-delTd"%>"><a class="store-btn-delete" id="<%=impassList.get(i).getId()  + "-delete"%>"><img
								src="../img/delete2.png"></a></td>
					</tr>
					<%
						    }
					%>
					
					<tr class="tableBottomTr" id="addStoreLine">
						<td colspan="7"> <div class="addBut" id="addStoreBut">+</div></td>
					</tr>
				</table>
				<table class="book-table" id="pass-table" border="0" style="display: none">
					<tr class="tableTr">
					    <th width="350px">日期</th>
						<th width="250px">店面</th>
						<th width="280px">商品名</th>
						<th width="180px">数量</th>
						<th width="180px">价格</th>
						<th width="100px">删除</th>
						
					</tr>
					<tr>
						<td colspan="6"><div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div></td>
					</tr>
				</table>
				<table class="book-table" id="delete-table" border="0" style="display: none">
					<tr class="tableTr">
					    <th width="350px">日期</th>
						<th width="250px">店面</th>
						<th width="280px">商品名</th>
						<th width="180px">数量</th>
						<th width="180px">价格</th>
						<th width="180px">彻底删除</th>
						<th width="100px">还原</th>
						
					</tr>
					<tr>
						<td colspan="7"><div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div></td>
					</tr>
				</table>
				
				<div class="message"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
    <script src="../js/myJS.js"></script>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/headServer.js"></script>
</body>
</html>