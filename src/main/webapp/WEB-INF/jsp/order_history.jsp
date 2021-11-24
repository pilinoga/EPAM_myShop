<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
    <style>
        table {


        margin-left: 60px;
              }
        p {
        padding: 0;
        margin-left: 210px;
              }
             </style>
</head>
<body>
  <fmt:setLocale value="${sessionScope.language}"/>
  <fmt:setBundle basename="language" var="loc"/>
<jsp:include page ="blocks/header.jsp"></jsp:include>
<div align="left" class= "bg-light">
<h3> <p><font color = "#3594B4"> <fmt:message bundle="${loc}" key="language.orders"/> </ font> </p></h3>
        <table border="0" cellpadding="7">
            <tr>
                <th>№</th>
                <th><fmt:message bundle="${loc}" key="language.cost"/></th>
                <th><fmt:message bundle="${loc}" key="language.date"/></th>
                <th><fmt:message bundle="${loc}" key="language.status"/></th>
                <th></th>
                <th></th>

            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><c:out value="${order.id}" /></td>
                    <td><c:out value="${order.price}"/></td>
                    <td><c:out value="${order.orderDate}"/></td>
                    <td><c:if test="${order.status == 'true'}"> <c:out value="Оплачен"/></c:if>
                    <c:if test="${order.status == 'false'}"> <c:out value="Не оплачен"/></c:if></td>
                <td><a href="shop?command=about_order&id_order=${order.id}&page=${requestScope['page']}" class="nav-link active"> <fmt:message bundle="${loc}" key="language.more"/></a></td>
                <td><c:if test="${(about != null) && (order.id == id_order)}">
                 <tr>
                  <th></th>
                   <th></th>
                   <th></th>
                    <th></th>
                    <th></th>
                  <th><h5> <font color = "#3594B4"> <fmt:message bundle="${loc}" key="language.orderNumber"/>  ${id_order} </ font></h5></th>
                  <th><fmt:message bundle="${loc}" key="language.product"/></th>
                  <th><fmt:message bundle="${loc}" key="language.description"/></th>
                  <th><fmt:message bundle="${loc}" key="language.price"/></th>
                 </tr>
                 <c:forEach var="product" items="${products}">
                 <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><c:out value="${product.name}" /></td>
                  <td><c:out value="${product.description}"/></td>
                  <td><c:out value="${product.price}" /><td>
                  <td></td>
                 </tr>
             </c:forEach>
             </c:if></td>
             </tr>
            </c:forEach>
             </table>
<nav aria-label="pagination">
      <ul class="pagination justify-content-center">
      <c:if test="${requestScope['page']>1}">
        <li class="page-item">
        <a class="page-link" href="shop?command=order_history&page=${requestScope['page']-1}">Previous</a>
        </li>
        </c:if>
        <li class="page-item"><a class="page-link" href="shop?command=order_history&page=1">1</a></li>
        <li class="page-item"><a class="page-link" href="shop?command=order_history&page=2">2</a></li>
        <c:if test="${requestScope['page']<2}">
        <li class="page-item">
          <a class="page-link" href="shop?command=order_history&page=${requestScope['page']+1}">Next</a>
        </li>
        </c:if>
      </ul>
</nav>
</div>
</body>
<jsp:include page="blocks/footer.jsp"></jsp:include>
</html>

