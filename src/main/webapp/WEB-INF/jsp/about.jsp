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
      <jsp:include page="blocks/header.jsp"></jsp:include>
   <head>
      <title>About</title>
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
   </head>
   <body>
   <fmt:setLocale value="${sessionScope.language}"/>
   <fmt:setBundle basename="language" var="loc"/>
<div class="position-relative text-center bg-light">
    <div class="col-md-8 p-lg-2 mx-auto my-1">
        <p class="lead fw-normal"><fmt:message bundle="${loc}" key="language.aboutPrint"/></p>
    <p><img src="images/about.jpg" width="670" height="350"</p>
       </div>
</div>
      <jsp:include page="blocks/footer.jsp"></jsp:include>
   </body>
</html>
