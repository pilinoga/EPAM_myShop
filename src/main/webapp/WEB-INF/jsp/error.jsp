<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
    <title>Error</title>
        <style>
          body {
           text-align: center;}

        </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="language" var="loc"/>
    <h3 align="center"><fmt:message bundle="${loc}" key="language.error"/> </h3>
    <p class="center-img">
      <img src="images/error.jpg">
    </p>
</body>
</html>