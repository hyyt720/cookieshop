<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>订单列表</title>
  <meta charset="utf-8"/>
  <link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">
  <jsp:include page="header.jsp"></jsp:include>
  <table class="table table-bordered table-hover">

    <tr>
      <th width="25%">ID</th>
      <th width="25%">商品名</th>
      <th width="25%">售出总数</th>
      <th width="25%">总营业额</th>
    </tr>


    <c:forEach items="${p.list }" var="oc">
      <tr>
        <td><p>${oc.id }</p></td>
        <td><p>${oc.name }</p></td>
        <td><p>${oc.amount }</p></td>
        <td><p>${oc.total }</p></td>
      </tr>
    </c:forEach>


  </table>
  <br>
  <jsp:include page="/page.jsp">
    <jsp:param value="/admin/order_count" name="url"/>
  </jsp:include>
  <br>
</div>
</body>
</html>


