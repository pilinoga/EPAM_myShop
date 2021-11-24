<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 29.10.2021
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page ="blocks/header.jsp"></jsp:include>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
    <style>
          table {
           margin-left: 30px;

          }
          p {

            margin-left: 210px;
                        }
         </style>
</head>
<body>
 <fmt:setLocale value="${sessionScope.language}"/>
  <fmt:setBundle basename="language" var="loc"/>
<div align="left" class= "bg-light">

             <c:if test="${action != null}" >
            <h7> <p align="center"> <font color = "red">    <c:out value="${action }"/> </ font> </c:if></p></h7>
            <h2> <p> <font color = "#3594B4"> <fmt:message bundle="${loc}" key="language.products"/> </ font> </p></h2>
        <table border="0" cellpadding="5">

            <tr>
                <th></th>
                <th><fmt:message bundle="${loc}" key="language.title"/></th>
                <th><fmt:message bundle="${loc}" key="language.description"/></th>
                <th><fmt:message bundle="${loc}" key="language.price"/></th>
                <th></th>

            </tr>

            <c:forEach var="product" items="${products}">
                <tr>

                   <td width="50"> <img src="https://content2.onliner.by/catalog/device/main/b9fefc8c8f96dbc21492792f3a1a502d.jpeg" width="65" height="75" <td>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.description}"/></td>
                    <td><c:out value="${product.price}" /></td>
                    <td><a href="shop?command=about_product&id_product=${product.id}&page=${requestScope['page']}" class="nav-link active"><fmt:message bundle="${loc}" key="language.more"/></a>
                    </td>
                    <td><c:if test="${(sessionScope.role.name == 'customer') && (customer != null)}">
                    <td><a href="shop?command=add_to_order&id=${product.id}&page=${requestScope['page']}" class="nav-link active"> <fmt:message bundle="${loc}" key="language.addToOrder"/></a></c:if></td>
                    <td><c:if test="${sessionScope.role.name == 'admin'}">
                    <a href="shop?command=go_to_edit_product&id=${product.id}&name=${product.name}&description=${product.description}&price=${product.price}" class="nav-link active"><fmt:message bundle="${loc}" key="language.correct"/></a></c:if></td>
                    <td width="650"><c:if test="${(specification != null) && (product.id == id_product)}">
                              <c:out value="${specification}" />  </c:if></td>

            </c:forEach>
              </tr>

         </table>
<nav aria-label="pagination">
      <ul class="pagination justify-content-center">
      <c:if test="${requestScope['page']>1}">
        <li class="page-item">
        <a class="page-link" href="shop?command=show_products&page=${requestScope['page']-1}">Previous</a>
        </li>
        </c:if>
        <li class="page-item"><a class="page-link" href="shop?command=show_products&page=1">1</a></li>
        <li class="page-item"><a class="page-link" href="shop?command=show_products&page=2">2</a></li>
        <c:if test="${requestScope['page']<2}">
        <li class="page-item">
          <a class="page-link" href="shop?command=show_products&page=${requestScope['page']+1}">Next</a>
        </li>
        </c:if>
      </ul>
</nav>
    </div>

</body>
<jsp:include page="blocks/footer.jsp"></jsp:include>
</html>
