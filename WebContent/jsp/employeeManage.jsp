<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dessert.rvo.employee.EmploeeInfoResultVO"%>
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
	Map<Integer, String> sotreList = (Map<Integer, String>) sc.getAttribute("store_list");
	List<EmploeeInfoResultVO> headList = (List<EmploeeInfoResultVO>) sc.getAttribute("head_server");
	List<EmploeeInfoResultVO> serverList = (List<EmploeeInfoResultVO>) sc.getAttribute("server");
%>
</head>
<body>
	<div class="left-bar">
		<div class="logo">
			<img src="../img/logo.png">
		</div>
		<div class="nav-btn-group">
			<a class="nav-btn" href="/Desserthouse/ManageStore">店面管理</a>
			<a class="nav-btn nav-btn-active" href="javascript:void(0)">店员管理</a>
		</div>

		<a class="manage-btn" href="/Desserthouse/Logout"> <span>登出</span> <img
			src="../img/signout.png">
			<div class="clear"></div>
		</a>
	</div>
	<div class="right-site">
		<div class="tab-btn-group">
			<a class="tab-btn tab-btn-active" href="javascript:void(0)">服务员管理</a> 
		</div>
		<div class="content">
			<div class="wrapper">
				<div class="tool-bar">
					<a class="btn tool-btn" id="tool-btn-employee" href="javascript:void(0)">添加服务员</a>
					<div class="clear"></div>
				</div>
				<table class="book-table" id="store-table" border="0">
					<tr class="tableTr">
						<th width="280px">用户名</th>
						<th width="280px">所属分店</th>
						<th width="280px">工作类型</th>
						<th width="280px">密码</th>
						<th width="100px">修改</th>
						<th width="100px">删除</th>
					</tr>
					<%
						for (int i = 0; i < headList.size(); i++) {
					%>
					<tr class="tableBottomTr">
						<td id="<%=headList.get(i).getName() + "-name"%>"><%=headList.get(i).getName()%></td>
						<td id="<%=headList.get(i).getName() + "-id"%>">总店</td>
						<td id="<%=headList.get(i).getName() + "-type"%>"><%=headList.get(i).getTypeString()%></td>
						<td><input disabled="true" type="text"  id="<%=headList.get(i).getName() + "-password"%>" placeholder='在此输入新密码'></td>
						<td><a class="store-btn-edit" id="<%=headList.get(i).getName() + "-edit"%>"><img
								src="../img/edit.png"></a></td>
						<td><a class="store-btn-delete" id="<%=headList.get(i).getName()  + "-delete"%>"><img
								src="../img/delete2.png"></a></td>
					</tr>	
					<%
						    }
					%>
					
					<%
						for (int i = 0; i < serverList.size(); i++) {
					%>
					<tr class="tableBottomTr">
						<td id="<%=serverList.get(i).getName() + "-name"%>"><%=serverList.get(i).getName()%></td>
						<td id="<%=serverList.get(i).getName() + "-id"%>"><%=sotreList.get(serverList.get(i).getS_id()) %></td>
						<td id="<%=serverList.get(i).getName() + "-type"%>"><%=serverList.get(i).getTypeString()%></td>
						<td><input disabled="true" type="text"  id="<%=serverList.get(i).getName() + "-password"%>" placeholder='在此输入新密码'></td>
						<td><a class="store-btn-edit" id="<%=serverList.get(i).getName() + "-edit"%>"><img
								src="../img/edit.png"></a></td>
						<td><a class="store-btn-delete" id="<%=serverList.get(i).getName()  + "-delete"%>"><img
								src="../img/delete2.png"></a></td>
					</tr>
					<%
						    }
					%>
					
					<tr class="tableBottomTr" id="addStoreLine">
						<td colspan="6"> <div class="addBut" id="addStoreBut">+</div></td>
					</tr>
				</table>
				<div class="message"></div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="modal-wrapper" id="employee-edit" style="display: none;">
		<div class="dialog cart-dialog">
			<div class="dialog-title">修改</div>
			<a class="close-btn" href="javascript:void(0)"><b></b></a>
			<form class="info-form">
			    <ul>
						<li><span id="edit-name"></span></li>
						<li><select id="edit-s-id">
						<option value="0">无</option>
						<%for(Map.Entry<Integer,String> entry:sotreList.entrySet()){ %>
							<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
						<%} %>
						</select>
						</li>
						<li><select id="edit-type">
						     <option value="2">总店服务员</option>
						     <option value="3">分店服务员</option>
						</select>
						</li>
			    </ul>
			</form>
			<a class="btn confirm-btn" id="confirm-edit" href="javascript:void(0)">添加</a>
			<div class="clear"></div>
			<div class="form-message" id="form-edit"></div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="modal-wrapper" id="employee-add" style="display: none;">
		<div class="dialog cart-dialog">
			<div class="dialog-title">新增</div>
			<a class="close-btn" href="javascript:void(0)"><b></b></a>
			<form class="info-form">
			    <ul>
						<li><input type="text" class="long-input-td" id="add-name" placeholder="用户名"></li>
						<li><select id="add-s-id">
						<option value="0">无</option>
						<%for(Map.Entry<Integer,String> entry:sotreList.entrySet()){ %>
							<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
						<%} %>
						</select>
						</li>
						<li><select id="add-type">
						     <option value="2">总店服务员</option>
						     <option value="3">分店服务员</option>
						</select>
						<li><input type="password" class="long-input-td" id="add-password" placeholder="请输入密码"></li>
						<li><input type="password" class="long-input-td" id="add-password-twice" placeholder="请再次输入密码"></li>
			    </ul>
			</form>
			<a class="btn confirm-btn" id="confirm-add" href="javascript:void(0)">添加</a>
			<div class="form-message" id="form-add"></div>
			<div class="clear"></div>
		</div>
	</div>
	<script src="../js/jquery-2.1.4.min.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/employeeManage.js"></script>
</body>
</html>