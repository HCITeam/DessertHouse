<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.member.CardInfoResultVO" %>
<%@ page import="dessert.rvo.member.MemberRecordRVO" %>
<%@ page import="java.util.List" %>
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
	    CardInfoResultVO info=(CardInfoResultVO)sc.getAttribute("cardinfo");
	    List<MemberRecordRVO> consumeList=(List<MemberRecordRVO>)sc.getAttribute("consume_list");
		List<MemberRecordRVO> rechargeList=(List<MemberRecordRVO>)sc.getAttribute("recharge_list");
	%>
</head>
<body>
	
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" id="nav-btn-active" href="/Desserthouse/Sale">销售</a>
			<a class="nav-btn" href="javascript:void(0)">会员资料</a>
			<a class="nav-btn" href="/Desserthouse/jsp/recharge.jsp">充值</a>
		</div>
		<a class="manage-btn" href="/Desserthouse/Logout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>

	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" id="tab-first" href="javascript:void(0)">会员信息</a>
			<a class="tab-btn" id="tab-second"  href="javascript:void(0)">缴费信息</a>
			<a class="tab-btn" id="tab-third"  href="javascript:void(0)">消费信息</a>
		</div>
		<div class="content">
			<div class="wrapper">
				<div style="height: 1px"></div>
				<% if(info==null){%>
				<form class="info-form">
				   <ul>
				   	  <li><span>会员卡号:</span><input type="text" id="m_id" placeholder="请输入7位会员卡号"></li>
					  <li><input class="btn modify-btn" id="modify-memberid" value="查询"></li>		   
				      <li><div class=message></div></li>
				   </ul>
				</form>
				<%} else{%>
				<form class="info-form" id="page-first">
				   <ul>
				   	 <li><span>卡号:</span><span> <%=info.getId() %></span></li>
						<li><span>等级:</span><span> <%=info.getGrade() %></span></li>
						<li><span>积分:</span><span> <%=info.getIntegral() %></span></li>
						<li><span>状态:</span><span> <%=info.getStateString() %></span></li>
						<li><span>余额:</span><span> <%=info.getBalance() %></span></li>
						<li><span>累计:</span><span> <%=info.getTotal() %></span></li>
						<%if(info.getBackCard().equals(" ")){ 
						%><li><span>银行卡:</span><span>该用户未绑定银行卡</span></li>
						<%}else{ 
						%><li><span>银行卡:</span><span><%=info.getBackCard() %></span></li>
						<%}%>
					</ul>
				</form>
				
				<table class="book-table" id="page-second" border="1" style="display:none">
					<tr>
						<th>#</th>
						<th>日期</th>
						<th>金额</th>
						<th>描述</th>
					</tr>
					<%
						for (int i = 0; i < rechargeList.size(); i++) {
					%>
					<tr>
					    <td><img src="../img/posi.png"></td>			
						<td><%=rechargeList.get(i).getR_date() %></td>
						<td><%=rechargeList.get(i).getAmount() %></td>
						<td><%=rechargeList.get(i).getExplanation() %></td>
					</tr>
					
				     <%}%>
				</table>
				
				<table class="book-table" id="page-third" border="1" style="display:none">
					<tr>
						<th>#</th>
						<th>日期</th>
						<th>金额</th>
						<th>描述</th>
					</tr>
					<%
						for (int i = 0; i < consumeList.size(); i++) {
					%>
					<tr>
					    <td><img src="../img/neg.png"></td>			
						<td><%=consumeList.get(i).getR_date() %></td>
						<td><%=consumeList.get(i).getAmount() %></td>
						<td><%=consumeList.get(i).getExplanation() %></td>
					</tr>
				     <%}%>
				</table>
				<%} %>
			</div>
		</div>
	</div>
	<form action="/Desserthouse/AllInfo" method="post" id="user-id">
				<input type="text" id="input-id" name="id" style="display: none;"> 
			</form>
	<div class="clear"></div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
    <script src="../js/memberAllInfo.js"></script>
</body>
</html>