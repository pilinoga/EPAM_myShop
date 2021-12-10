<%--
  Created by IntelliJ IDEA.
  User: kirill
<%--  Date: 26.10.2021--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/Author.tld" prefix="myTag" %>

<body>
   <fmt:setLocale value="${sessionScope.language}"/>
   <fmt:setBundle basename="language" var="loc"/>
   <footer class="py-3 my-2">
      <ul class="nav justify-content-center border-bottom pb-0 mb-0">
         <li class="nav-item">
            <a href="shop" class="nav-link " >
               <fmt:message bundle="${loc}" key="language.footerMain"/>
            </a>
         </li>
      </ul>
      <font color ="black">
         <center>
            <myTag:author year="2021" author="pilinoga"/>
         </center>
      </font>
   </footer>
<body>