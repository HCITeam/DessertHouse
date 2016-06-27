<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.commodity.InventoryRVO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Book</title>
<link rel="stylesheet" type="text/css" href="../css/reset.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../css/bootstrap-datepicker3.css">
<link rel="stylesheet" type="text/css" href="../css/main.css">
<link rel="stylesheet" type="text/css" href="../css/myCss.css">
<%
	ServletContext sc = request.getServletContext();
	String visited_store = (String) sc.getAttribute("visited");
	String[] storeName = (String[]) sc.getAttribute("store_name");
	int state=(int)session.getAttribute("state");
	List<InventoryRVO> firstList = (List<InventoryRVO>) sc.getAttribute("date_first");
	List<InventoryRVO> secondList = (List<InventoryRVO>) sc.getAttribute("date_second");
	List<InventoryRVO> thirdList = (List<InventoryRVO>) sc.getAttribute("date_third");
	String date1 = (String)sc.getAttribute("date_one");
	String date2 = (String)sc.getAttribute("date_two");
	String date3 = (String)sc.getAttribute("date_three");
	
	
    int success = (int) sc.getAttribute("success");
	String message=(String)sc.getAttribute("message");
	String compellation="";
	String birthday="";
	int gender=0;
	String address="";
	String phone="";
	int area=0;
	if(success==1){
		area=(int)sc.getAttribute("area");
		compellation = (String) sc.getAttribute("compellation");
		birthday = (String) sc.getAttribute("birthday");
		gender = (int) sc.getAttribute("gender");
		address = (String) sc.getAttribute("address");
		phone = (String) sc.getAttribute("phone");
	}
%>
<script>
	var visited = "<%=visited_store%>";
	var m_state=<%=state%>;
</script>

</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">

			<%
				for (int i = 0; i < storeName.length; i++) {
					if(storeName[i].equals(visited_store)){
						%>
						<a class="nav-btn nav-btn-active" href="javascript:void(0)"
							id="<%="nav-" + storeName[i]%>"><%=storeName[i]%></a>
					<% }else{%>
						<a class="nav-btn" href="javascript:void(0)"
							id="<%="nav-" + storeName[i]%>"><%=storeName[i]%></a>
					<%}%>
			<%
				}
			%>
		</div>

		<a class="large-manage-btn" href="/Desserthouse/ShowMemberInfo"> <span>个人管理</span> <img
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
			<a class="tab-btn tab-btn-active" href="javascript:void(0)"
				id="tab-firstday"><%
 					String[] date = date1.split("-");
 					String show_date = date[1] + "-" + date[2];
 					out.print(show_date);
 				%></a> 
				<a class="tab-btn" href="javascript:void(0)" id="tab-secondday"><%
					date = date2.split("-");
					show_date = date[1] + "-" + date[2];
					out.print(show_date);
				%></a> 
				<a class="tab-btn" href="javascript:void(0)" id="tab-thirdday"><%
					date = date3.split("-");
					show_date = date[1] + "-" + date[2];
					out.print(show_date);
					//System.out.println(show_date);
				%></a>
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar setShow">
					<a class="btn tool-btn" id="tool-btn-cart" href="javascript:void(0)">购物车</a>
					<input type="text" class="date-input" placeholder="预定日期">
					<div class="clear"></div>
				</div>
				<%
					if ((firstList==null)||(firstList.size() <= 0)){
				%>
				<table class="book-table" id="table-first" border="0">
					<tr class="tableTr">
						<th width="280px">名称</th>
						<th width="150px">价格</th>
						<th width="150px">剩余数量</th>
						<th width="150px">加入购物车</th>
					</tr>
					<tr class="tableBottomTr">
						<td colspan="6">非常抱歉,此日期尚不支持预定</td>
					</tr>
				</table>
				<%
					} else {
				%>
				<table class="book-table" id="table-first" border="0">
					<tr class="tableTr">
						<th width="280px">名称</th>
						<th width="150px">价格</th>
						<th width="150px">剩余数量</th>
						<th width="150px">加入购物车</th>
					</tr>
					<%
						for (int i = 0; i < firstList.size(); i++) {
					%>
					<tr class="tableBottomTr">
						<td class="dessert-name-td"><%=firstList.get(i).getP_name()%></td>
						<td class="dessert-price-td"><%=firstList.get(i).getPrice()%></td>
						<td class="dessert-num-td"><%=firstList.get(i).getP_num()%></td>
						<td><a class="store-btn-delete"
							id="<%=firstList.get(i).getS_date() + "-" + i%>"><img
								src="../img/delete.png"></a></td>
					</tr>
					<%
						    }
						}
					%>
				</table>

				<%
					if ((secondList==null)||(secondList.size() <= 0)) {
				%>
					<table class="book-table" id="table-second" border="0"
					style="display: none">
					<tr class="tableTr">
						<th width="280px">名称</th>
						<th width="150px">价格</th>
						<th width="150px">剩余数量</th>
						<th width="150px">加入购物车</th>
					</tr>
					<tr class="tableBottomTr">
						<td colspan="6">非常抱歉,此日期尚不支持预定</td>
					</tr>
				</table>
				<%
					} else {
				%>
				<table class="book-table" id="table-second" border="0"
					style="display: none">
					<tr class="tableTr">
						<th width="280px">名称</th>
						<th width="150px">价格</th>
						<th width="150px">剩余数量</th>
						<th width="150px">加入购物车</th>
					</tr>
					<%
						for (int i = 0; i < secondList.size(); i++) {
					%>
					<tr  class="tableBottomTr">
						<td class="dessert-name-td"><%=secondList.get(i).getP_name()%></td>
						<td class="dessert-price-td"><%=secondList.get(i).getPrice()%></td>
						<td class="dessert-num-td"><%=secondList.get(i).getP_num()%></td>
						<td><a class="store-btn-delete"
							id="<%=secondList.get(i).getS_date() + "-" + i%>"><img
								src="../img/delete.png"></a></td>
					</tr>
					<%
						}
						}
					%>
				</table>

				<%
					if ((thirdList==null)||(thirdList.size() <= 0)) {
				%>
				<table class="book-table" id="table-third" border="0"
					style="display: none">
					<tr class="tableTr">
						<th width="280px">名称</th>
						<th width="150px">价格</th>
						<th width="150px">剩余数量</th>
						<th width="150px">加入购物车</th>
					</tr>
					<tr class="tableBottomTr">
						<td colspan="6">非常抱歉,此日期尚不支持预定</td>
					</tr>
				</table>
				<%
					} else {
				%>
				<table class="book-table" id="table-third" border="0"
					style="display: none">
					<tr class="tableTr">
						<th width="280px">名称</th>
						<th width="150px">价格</th>
						<th width="150px">剩余数量</th>
						<th width="150px">加入购物车</th>
					</tr>
					<%
						for (int i = 0; i < thirdList.size(); i++) {
					%>
					<tr class="tableBottomTr">
						<td class="dessert-name-td"><%=thirdList.get(i).getP_name()%></td>
						<td class="dessert-price-td"><%=thirdList.get(i).getPrice()%></td>
						<td class="dessert-num-td"><%=thirdList.get(i).getP_num()%></td>
						<td><a class="store-btn-delete"
							id="<%=thirdList.get(i).getS_date() + "-" + i%>"><img
								src="../img/delete.png"></a></td>
					</tr>
					<%
						}
						}
					%>
				</table>
			</div>
		</div>
			<form action="/Desserthouse/StoreInventory" method="post" id="store-inventory">
				<input  maxlength='10' type="text" id="store-name" name="store_name"
					style="display: none;"> 
				<input type="text"
					id="date" name="s_date" style="display: none;">
			</form>

	</div>

	<div class="clear"></div>
	<div class="modal-wrapper" style="display: none;">
		<div class="dialog cart-dialog" id="large-cart-dialog">
			<div id="dialogDiv">
				<div class="dialog-title">购物车</div>
				<a class="close-btn" href="javascript:void(0)"><b></b></a>
				<div class="cart-table">
					<table class="book-table" id="book-table-cart" border="1">
						<tr>
							<th>日期</th>
							<th>店面</th>
							<th>名称</th>
							<th>价格</th>
							<th>剩余数量</th>
							<th>预定数量</th>
						</tr>
					</table>
				</div>
			</div>
			<a class="btn confirm-btn" href="javascript:void(0)" id="goSend">确认预订</a>
			<div style="display:none" id="sendDiv">
				<div class="dialog-title">送货方式</div>
				<div style="text-align:center;font-size:20px;margin-top:10px;">
					自取<input type="radio" name="gender" value="1" id="setSelf" checked="checked">送货<input id="setSend" type="radio" name="gender" value="0">
				</div>
				<table style="margin-top:0px;" class="book-table" id="book-table-cart" border="1">
						<tr id="locationTr" style="display:none">
							<th>地址</th>
							<td><input  maxlength='20' style="width:400px;" class="setBackWhite" type="text" id="saddress" placeholder="送货地址" value="<%=address%>"></td>
						</tr>
						<tr>
							<th>联系人</th>
							<td><input maxlength='10' style="width:400px;" class="setBackWhite" type="text" id="sname" placeholder="名称" value="<%=compellation%>"></td>
						</tr>
						<tr>
							<th>联系方式</th>
							<td><input onkeypress="setOnlyNumber()"  maxlength='15' style="width:400px;" class="setBackWhite" type="text" id="sphone" placeholder="电话号码" value="<%=phone%>"></td>
						</tr>
						<tr>
							<td colspan="2" id="doer">
								<div class="showBorder xrBut" id="doSend">确定</div>
								<div class="showBorder xrBut" id="doSendBack">返回</div>
							</td>
						</tr>
				</table>
			</div>
			<div class="message"></div>
			<div class="clear"></div>
		</div>
	</div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap-datepicker.js"></script>
	<script src="../js/book.js"></script>
	<script src="../js/myJS.js"></script>
</body>
</html>