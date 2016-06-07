<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.commodity.SaleRecordRVO"%>
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
	List<SaleRecordRVO> list = (List<SaleRecordRVO>) sc.getAttribute("sale_record");
	int month=(int) sc.getAttribute("month");
	int s_id=(int) sc.getAttribute("s_id");
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="/Desserthouse/ManagePlan">计划审批</a>
		    <a class="nav-btn" id="member-record" href="javascript:void(0)">会员统计</a>
		    <a class="nav-btn nav-btn-active" id="sale-record" href="javascript:void(0)">销售统计</a>
		</div>

		<a class="manage-btn" href="/Desserthouse/Logout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">销售情况</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar">
					<a class="btn tool-btn" id="tool-btn-check" href="javascript:void(0)">查询</a>
					<select id="s_id">
						<%for(Map.Entry<Integer,String> entry:store.entrySet()){ %>
						    <%if(entry.getKey()==s_id){ %>
							    <option value="<%=entry.getKey()%>" selected="selected"><%=entry.getValue() %></option>
						    <%}else{ %>
						       <option value="<%=entry.getKey()%>"><%=entry.getValue() %></option>
						    <%} %>
						<%} %>
						</select>
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
				<%
					if (list.size() <= 0) {
				%>
				<form class="info-form">
					<ul>
						<li><span>当前没有销售情况</span></li>
					</ul>
				</form>
				<%
					} else {
				%>
				<table class="book-table" id="plan-table" border="1">
					<tr>
						<th>商品名</th>
						<th>数量</th>
						<th>总价</th>
						<th>类型</th>
					</tr>
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<tr>
						<td><%=list.get(i).getP_name()%></td>
						<td><%=list.get(i).getP_num()%></td>
						<td><%=list.get(i).getAmount()%></td>
						<%if(list.get(i).getType()==1){ %>
						<td>线下购买</td>
						<%}else{ %>
						<td>线上预定</td>
						<%} %>
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
	<script src="../js/saleRecord.js"></script>
</body>
</html>