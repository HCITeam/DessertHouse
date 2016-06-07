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
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">计划管理</a>
		</div>

		<a class="manage-btn" href="/Desserthouse/Logout"> <span>登出</span> <img
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
				<div class="tool-bar">
					<a class="btn tool-btn" id="tool-btn-plan" href="javascript:void(0)">新增计划</a>
					<div class="clear"></div>
				</div>
				
				<table class="book-table" id="plan-table" border="1">
					<tr>
						<th>日期</th>
						<th>店面</th>
						<th>商品名</th>
						<th>数量</th>
						<th>价格</th>
						<th>编辑</th>
						<th>删除</th>
					</tr>
					<%
						for (int i = 0; i < impassList.size(); i++) {
					%>
					<tr>
						<td><%=impassList.get(i).getDate()%></td>
						<td><%=store.get(impassList.get(i).getS_id())%></td>
						<td><%=impassList.get(i).getP_name()%></td>
						<td><input type="text" class="short-input-td" id="<%=impassList.get(i).getId() + "-num"%>" value="<%=impassList.get(i).getP_num() %>"></td>
						<td><input type="text" class="short-input-td" id="<%=impassList.get(i).getId() + "-price"%>" value="<%=impassList.get(i).getPrice() %>"></td>
					    <td><a class="plan-btn-edit" id="<%=impassList.get(i).getId() + "-edit"%>"><img
								src="../img/edit.png"></a></td>
						<td><a class="plan-btn-delete" id="<%=impassList.get(i).getId()  + "-delete"%>"><img
								src="../img/delete.png"></a></td>
					</tr>
					<%
						    }
					%>
				</table>
				<div class="message"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="modal-wrapper" style="display: none;">
		<div class="dialog cart-dialog">
			<div class="dialog-title">生产计划</div>
			<a class="close-btn" href="javascript:void(0)"><b></b></a>
			<form class="plan-form">
			    <ul>
						<li><input type="date" class="long-input-td" id="p_date" placeholder="2016/03/01"></li>
						<li><select class="short-input-td" id="s_id">
						<%for(Map.Entry<Integer,String> entry:store.entrySet()){ %>
							<option value="<%=entry.getKey()%>"><%=entry.getValue() %></option>
						<%} %>
						</select></li>
						<li><select class="long-input-td" id="p_name">
							<option value="南瓜派">南瓜派</option>
							<option value="草莓派">草莓派</option>
							<option value="起司蛋糕">起司蛋糕</option>
							<option value="姜饼小人">姜饼小人</option>
							<option value="菠萝包">菠萝包</option>
							<option value="奶酪蛋糕">奶酪蛋糕</option>
							<option value="提拉米苏">提拉米苏</option>
							<option value="切片面包">切片面包</option>
							<option value="蛋挞">蛋挞</option>
						</select></li>
						<li><input type="text" class="short-input-td" id="p_num" placeholder="商品数量"></li>
						<li><input type="text" class="short-input-td" id="price" placeholder="单价"></li>
						<li><a class="btn confirm-btn" href="javascript:void(0)">添加</a></li>
			    </ul>
			</form>
			<div class="form-message"></div>
			<div class="clear"></div>
		</div>
	</div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/headServer.js"></script>
</body>
</html>