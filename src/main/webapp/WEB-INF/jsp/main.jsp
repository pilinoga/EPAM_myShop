<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 09.10.2021
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page ="blocks/header.jsp"></jsp:include>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
    <title>Main</title>

</head>
<body >
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language" var="loc"/>
<div class="position-relative text-center bg-light">
    <div class="col-md-5 p-lg-1 mx-auto my-1">
      <h3 class="display-12 fw-normal">myShop</h3>
      <p class="lead fw-normal"><fmt:message bundle="${loc}" key="language.mainPrint"/></p>
    </div>
<div class="position-relative text-center bg-light">
    <p><img src="https://www.apple.com/v/iphone-13-pro/d/images/overview/appleone-continuity/continuity__dgqzd54dz6ky_large.jpg"</p>
    <div class="col-md-5 p-lg-1 mx-auto my-1">
      <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/shop?command=show_products&page=1"><fmt:message bundle="${loc}" key="language.toCatalog" /></a>

 </div>


<jsp:include page="blocks/footer.jsp"></jsp:include>
</body>

</html>
