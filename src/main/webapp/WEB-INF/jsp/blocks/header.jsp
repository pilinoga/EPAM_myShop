<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language" var="loc"/>
<div th:fragment = "header">
  <header class="d-flex flex-wrap justify-content-center py-3 mb-1 border-bottom">
    <a href="shop" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
      <svg class="bi me-2" width="40" height="30"><use xlink:href="#bootstrap"></use></svg>
      <span class="fs-4"> myShop </span>
    </a>
  <h6>
    <ul class="nav nav-pills">
      <li class="nav-item"><a href="shop" class="nav-link " ><fmt:message bundle="${loc}" key="language.main"/></a></li>
      <li class="nav-item"><a href="${pageContext.request.contextPath}/shop?command=show_products&page=1" class="nav-link"><fmt:message bundle="${loc}" key="language.catalog"/></a></li>
      <c:if test="${sessionScope.role.name == 'unknown'}">
       <a href="shop?command=go_to_login" class="nav-link"><fmt:message bundle="${loc}" key="language.logIn"/></a></c:if>
      <li class="nav-item"><a href="shop?command=about" class="nav-link"><fmt:message bundle="${loc}" key="language.about"/></a></li>
        <c:if test="${sessionScope.role.name != 'unknown'}">
      <a href="shop?command=logout" class="nav-link"> <fmt:message bundle="${loc}" key="language.logout"/></a></c:if>

      <c:if test="${sessionScope.role.name != 'unknown'}">
            <a href="shop?command=go_to_profile" class="nav-link"><fmt:message bundle="${loc}" key="language.account"/></a></c:if>

    </ul>
  </h6>
  </header>
</div>
</body>