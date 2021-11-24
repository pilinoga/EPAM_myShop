<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 30.10.2021
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>About</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="blocks/header.jsp"></jsp:include>
<div style="text-align: center;" class= "bg-light">
    Система Интернет-магазин. Администратор осуществляет ведение каталога товаров. Клиент делает и оплачивает заказ на
    товары.
</div>
<jsp:include page="blocks/footer.jsp"></jsp:include>
</body>
</html>
