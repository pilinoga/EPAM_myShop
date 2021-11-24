<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="blocks/header.jsp"></jsp:include>
<head>
    <title>Basket</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
</head>
<body>
 <fmt:setLocale value="${sessionScope.language}"/>
  <fmt:setBundle basename="language" var="loc"/>
<c:if test="${products.size() < 1}">
<h4> <font color = "blue"> <fmt:message bundle="${loc}" key="language.emptyBasket"/> </ font></h4>
<h5> <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/shop?command=show_products&page=1"> <fmt:message bundle="${loc}" key="language.toCatalog"/> -></a></h5>
</c:if>
<c:if test="${products.size() > 0}">
<div align="center" class= "bg-light">
        <h3> <font color = "#3594B4">  <fmt:message bundle="${loc}" key="language.orderNumber"/>№  ${id_order} </ font></h3>
          <table border="0" cellpadding="6">
                      <c:if test="${message == null}">
                      <tr>
                          <th> <fmt:message bundle="${loc}" key="language.title"/></th>
                          <th> <fmt:message bundle="${loc}" key="language.description"/></th>
                          <th> <fmt:message bundle="${loc}" key="language.price"/></th>
                      </tr>
                      </c:if>
 <c:forEach var="product" items="${products}">

                <tr>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.description}"/></td>
                    <td><c:out value="${product.price}" /><td>
                <c:if test="${(message_pay == null) && (message_error == null) && (message_refuse == null) }">
                     <td> <a href="shop?command=remove_from_order&product_id=${product.id}" class="nav-link active"> <font color = "red"> <fmt:message bundle="${loc}" key="language.removeFrOrder"/> </ font> </a>   <td>
                </c:if>
                </tr>
  </c:forEach>
                    <tr>
                    <td>  <h5> <font color = "#082344"> <fmt:message bundle="${loc}" key="language.cost"/> ${order_price} </ font>  </h5>  <td>
                    <c:if test="${(message_pay == null) && (message_error == null)  && (message_refuse == null) }">

                    <td> <h4> <a href="shop?command=pay_order" class="nav-link active"> <fmt:message bundle="${loc}" key="language.pay"/></a> </h4><td>

                    <td> <h4> <a href="shop?command=refuse_order" class="nav-link active"> <font color = "red"> <fmt:message bundle="${loc}" key="language.refuse"/> </a> </ font> </h4><td></c:if>
                    </tr>
            <td><c:if test="${message_error != null}">
               <h5> <font color = "red"> <c:out value="${message_error }" /></ font> <h5> </c:if></td>
            <td><c:if test="${message_pay != null}">
            <h5> <font color = "red"> <c:out value="${message_pay }" /></ font> <h5> </c:if></td>
               </table>
        </c:if>
        </div>
<div align="center">
            <c:if test="${message_refuse != null}">
             <h4> <font color = "red"> <c:out value="${message_refuse }"  /></ font> </h4> </c:if>
    </div>
    </body>

<jsp:include page="blocks/footer.jsp"></jsp:include>
</html>