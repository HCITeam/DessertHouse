<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.commodity.SaleRecordRVO"%>
<%@ page import="dessert.rvo.employee.EmploeeInfoResultVO" %>
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
<script src="../js/jquery-2.1.4.min.js"></script>

<script src="../js/Chart.js"></script>
<%
	ServletContext sc = request.getServletContext();
	Map<Integer, String> store = (Map<Integer, String>) sc.getAttribute("store_list");
	List<SaleRecordRVO> list = (List<SaleRecordRVO>) sc.getAttribute("sale_record");
	List<EmploeeInfoResultVO> servers = (List<EmploeeInfoResultVO>) sc.getAttribute("servers");
	

	sc.removeAttribute("store_list");
	sc.removeAttribute("sale_record");
	sc.removeAttribute("servers");
	
	int month=(int) sc.getAttribute("month");
	int s_id=(int) sc.getAttribute("s_id");
	
	sc.removeAttribute("store_list");
	sc.removeAttribute("sale_record");
	sc.removeAttribute("servers");
	sc.removeAttribute("month");
	sc.removeAttribute("s_id");
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="/Desserthouse/ManagePlan">计划审批</a>
		    <a class="nav-btn nav-btn-active" id="sale-record" href="javascript:void(0)">销售统计</a>
		</div>

		<a class="manage-btn" href="/Desserthouse/EmployeeLogout"> <span>登出</span> <img
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
				<table class="book-table" id="plan-table" border="0">
					<tr  class="tableTr">
						<th  width="280px">商品名</th>
						<th  width="280px">数量</th>
						<th  width="280px">总价</th>
						<th  width="280px">类型</th>
					</tr>
					<%
						for (int i = 0; i < list.size(); i++) {
					%>
					<tr  class="tableBottomTr">
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
						if(list.size()<=0)
						{
					%>
					<tr class="tableBottomTr" id="addStoreLine">
						<td colspan="4"> 暂无销售情况</td>
					</tr>
					<%		
						}
					%>
				</table>
				<div style="text-align:center">
					<td colspan="4"> <div class="btn btn-default"id="showMap">查看图表</div></td>
					<div class="btn btn-default" style='margin-left:200px' id="sendMessageDo">发送消息</div>
					<div id="sender">
						发送消息
						<input maxlength='55' type="text" id="input-message" />
						给
						<select id="server">
						    <%for(EmploeeInfoResultVO server:servers){%>
						    	<option value="<%=server.getName() %>"><%=server.getName() %></option>
						    <%}%>    
						</select>
						<div class="btn btn-default" id="sendMessage">发送</div>
						<div class="btn btn-default" id="sendMessageBack">取消</div>
					</div>
					<div id="sendOver" style="color:green;font-size:22px">发送完成</div>
					<script>
						$("#sendOver").hide();
						$("#sender").hide();
					</script>
				</div>
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
	<div class="modal-wrapper" style="display: none;">
		<div class="dialog cart-dialog" id="large-cart-dialog">
			
			<div class="dialog-title"></div>
			<a class="close-btn" href="javascript:void(0)"><b></b></a>
			<div class="" style="padding-left:20px;height:380px;margin-top:-50px;overflow:hidden;">
				<div id="left" style="font-size:40px;float:left;color:grey;padding-top:200px;">◀</div>
				<div style="float:left" class="sleepShow">
					<canvas id="myChart" width="650" height="320" style="margin-top:50px;"></canvas>  
				</div>
				<div id="right" style="font-size:40px;float:left;color:#6495ED;padding-top:200px;">▶</div>
			</div>
			
			<div id="showLable" style="text-align:center;"><nobr style="color:#D97041">■</nobr>:第一分店<nobr style="color:#9D9B7F">■</nobr>:第二分店<nobr style="color:green">■</nobr>:第三分店<nobr style="color:#21323D">■</nobr>:第四分店</div>
		</div>
	</div>
	<script>
	$("#showLable").hide(200);
	var data = {
			labels : ["派","蛋糕","饼","面包","蛋挞","卷","饮料"],
			datasets : [
				{
					fillColor : "rgba(220,220,220,0.5)",
					strokeColor : "rgba(220,220,220,1)",
					pointColor : "rgba(220,220,220,1)",
					pointStrokeColor : "#fff",
					data : [65,59,90,81,56,55,40]
				},
				{
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "rgba(151,187,205,1)",
					pointStrokeColor : "#fff",
					data : [28,48,40,19,96,27,100]
				}
			]
		}
		var ctx = document.getElementById("myChart").getContext("2d");
		new Chart(ctx).Radar(data);
	</script>	
	<script src="../js/bootstrap.js"></script>
	<script src="../js/saleRecord.js"></script>
</body>
</html>