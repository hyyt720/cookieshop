<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>客户修改</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<jsp:include page="/admin/header.jsp" />

	<br><br>

	<form class="form-horizontal" action="/admin/user_edit" method="post">
		<input type="hidden" name="id" value="${u.id}">
		<div class="form-group">
			<label class="col-sm-1 control-label">用户名</label>
			<div class="col-sm-5">${u.username}</div>
		</div>
		<div class="form-group">
			<label class="col-sm-1 control-label">邮箱</label>
			<div class="col-sm-5">${u.email}</div>
		</div>
		<div class="form-group">
			<label for="inputName" class="col-sm-1 control-label">收货人</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="inputName" name="name" value="${u.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputPhone" class="col-sm-1 control-label">电话</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="inputPhone" name="phone" value="${u.phone}">
			</div>
		</div>
		<div class="form-group">
			<label for="inputAddress" class="col-sm-1 control-label">地址</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="inputAddress" name="address" value="${u.address}">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交修改</button>
			</div>
		</div>
	</form>

	<!-- 用于显示错误信息的span，需要在服务器端或客户端添加逻辑来填充 -->
	<span style="color:red;" id="errorMessage"></span>

</div>
</body>
</html>