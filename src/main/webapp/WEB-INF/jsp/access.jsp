<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 31.10.2021
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
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
      <h1>
         <font color = "red">
         <fmt:message bundle="${loc}" key="language.access"/>
         </ font>
      </h1>
      <p class="center-img">
         <img src="images/access.png" >
      </p>
   </body>
</html>
