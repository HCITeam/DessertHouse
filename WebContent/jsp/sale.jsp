<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.commodity.InventoryRVO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Sale</title>
	<link rel="stylesheet" href="../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../css/reset.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/myCss.css">
	<%
		ServletContext sc = request.getServletContext();
	    List<InventoryRVO> list=(List<InventoryRVO>)sc.getAttribute("inventory_list");
	%>
</head>
<body>
	
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">销售</a>
			<a class="nav-btn" href="/Desserthouse/jsp/recharge.jsp">充值</a>
		</div>
		<a class="manage-btn" href="/Desserthouse/Logout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>

	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" id="tab-card" href="javascript:void(0)">会员卡</a>
			<a class="tab-btn" id="tab-cash" href="javascript:void(0)">现金</a>
		</div>
	
		
		<div class="content">
			<div class="wrapper">
			<div style="height: 1px"></div>
				<table class="book-table" id="vip-table" border="0">
					<tr class="tableTr">
						<th width="280px">商品</th>
						<th width="100px">单价</th>
						<th width="100px">数量</th>
						<th width="100px">总价</th>
						<th width="100px">操作</th>
					</tr>
					<tr class="tableBottomTr">
						<td><select id="new-pname-card" width="250px">
						<%for(int i=0;i<list.size();i++) {%>
  							<option value ="<%=list.get(i).getPrice()%>"><%=list.get(i).getP_name()%></option>
  							<%} %>
						</select></td>
						<td></td>
					    <td><input id="new-good-num" class="showBorder" type="text" width="100px" value=""></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<div class="wrapper-left">
					<table class="sale-table" border="1">
					<tr>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
					</tr>
					<!-- <tr>
						<td>马卡龙</td>
						<td>10</td>
						<td>4</td>
					</tr> -->
				</table>
				</div>
				<div class="wrapper-right">
					<form class="info-form" id="card-form">
					<ul>
						<li><span>商品名:</span>
						<select id="pname-card">
						<%for(int i=0;i<list.size();i++) {%>
  							<option value ="<%=list.get(i).getPrice()%>"><%=list.get(i).getP_name()%></option>
  							<%} %>
						</select>
						</li>
						<li><span>数量:</span><input type="text" class="long-input-td" id="pnum-card" placeholder="商品数"></li>
							<li><span>会员卡:</span><input type="text" class="long-input-td" id="mid" placeholder="卡号"></li>
							<li><span>密码:</span><input type="password" class="long-input-td" id="mpass" placeholder="密码"></li>
							<li><span>余额:</span><span id="balance-card"></span></li>
							<li><span>总价:</span><span id="total-card"></span></li>
							<li><span>积分:</span><span id="integral-card"></span></li>
							<li><span><input class="btn confirm-btn reset-btn" type="button" value="重置"></span>
							<span><input class="btn confirm-btn" id="commit-card" type="button" value="结账"></span></li>
					</ul>
				</form>
				
				<form class="info-form" id="cash-form" style="display:none">
					<ul>
						<li><span>商品名:</span>
						<select id="pname-cash">
						<%for(int i=0;i<list.size();i++) {%>
  							<option value ="<%=list.get(i).getPrice()%>"><%=list.get(i).getP_name()%></option>
  							<%} %>
						</select>
						</li>
						<li><span>数量:</span><input id="pnum-cash" type="text" value="1"></li>
						<li><span>总价:</span><span id="amount-cash">0</span></li>
						<li><span>总额:</span><input id="total-cash" type="text" placeholder="收取金额"></li>
						<li><span>找零:</span><span id="charge-cash"></span></li>
						<li><span><input class="btn confirm-btn reset-btn" type="button" value="重置"></span>
						<span><input class="btn confirm-btn" id="commit-cash" type="button" value="结账"></span></li>
					</ul>
				</form>
				</div>
			</div>
		</div>
	</div>
	<form action="/Desserthouse/Sale" method="post" id="form-reset">
		<input type="text" id="input-id" name="id" style="display: none;"> 
	</form>
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
    <script src="../js/sale.js"></script>
</body>
</html>