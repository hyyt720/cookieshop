<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"></jsp:include>

    <br>

    <ul role="tablist" class="nav nav-tabs">
        <li <c:if test="${type==0 }">class="active"</c:if> role="presentation"><a href="/admin/browsing_show">全部记录</a></li>
        <li <c:if test="${type==1 }">class="active"</c:if> role="presentation"><a href="/admin/browsing_show?type=1">浏览记录</a></li>
        <li <c:if test="${type==2 }">class="active"</c:if> role="presentation"><a href="/admin/browsing_show?type=2">购买记录</a></li>
    </ul>





    <br>

    <table class="table table-bordered table-hover">

        <tr>
            <th width="20%">ID</th>
            <th width="20%">用户</th>
            <th width="20%">访问商品名称</th>
            <th width="20%">时间</th>
            <th width="20%">操作</th>
        </tr>

        <c:forEach items="${p.list }" var="b">
            <tr>
                <td><p>${b.id }</p></td>
                <td><p>${b.username}</p></td>
                <td><p>${b.goods_name}</p></td>
                <td><p>${b.datetime}</p></td>
                <td>
                    <a class="btn btn-danger" href="/admin/browsing_delete?id=${b.id }&pageNumber=${p.pageNumber}&type=${type}">删除</a>
                </td>
            </tr>
        </c:forEach>


    </table>

    <br>
    <jsp:include page="/page.jsp">
        <jsp:param value="/admin/browsing_show" name="url"/>
        <jsp:param value="&type=${type }" name="param"/>
    </jsp:include>
    <br>
</div>
</body>
</html>
