<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page ="blocks/header.jsp"></jsp:include>
<head>
    <title>Statistic</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
    <style>
          table {
           margin-left: 500px;

          }
          p {
            margin-left: 550px;
                        }
         </style>
</head>
<body>
  <fmt:setLocale value="${sessionScope.language}"/>
  <fmt:setBundle basename="language" var="loc"/>
<div align="left" class= "bg-light">

      <h6> <font color = "#3F4344">   <fmt:message bundle="${loc}" key="language.numbCust"/> <c:out value= "${customers.size()}" /> </h6>
      <h6> <fmt:message bundle="${loc}" key="language.numbProduct"/> <c:out value= "${productsAll.size()}" /> </ font></h6>
<p></p>

            <h4> <p> <font color = "#3594B4">   <fmt:message bundle="${loc}" key="language.topSales"/> </ font> </p></h4>

        <table border="0" cellpadding="6">
            <tr>
                <th>  <fmt:message bundle="${loc}" key="language.title"/>   </th>
                <th>  <fmt:message bundle="${loc}" key="language.description"/>  </th>
                <th>  <fmt:message bundle="${loc}" key="language.price"/>  </th>
                <th>  <fmt:message bundle="${loc}" key="language.salesQuantity"/></th>

            </tr>

            <c:forEach var="product" items="${products}">
                <tr>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.description}"/></td>
                    <td><c:out value="${product.price}" /></td>
                    <td>
                    <c:forEach var="item" items="${items}">
                    <c:if test="${product.id == item.productId}">
                    <c:out value="${item.quantity}" />
                     </c:if>
                    </c:forEach>
                    </td>
              </tr>
            </c:forEach>
         </table>
<p></p>
        <h4> <p> <font color = "#BF2F2F">   <fmt:message bundle="${loc}" key="language.blockCustomers"/>:</ font> </p></h4>
<table border="0" cellpadding="7">
            <tr>
                <th>  <fmt:message bundle="${loc}" key="language.id"/></th>
                <th>  <fmt:message bundle="${loc}" key="language.fName"/></th>
                <th>  <fmt:message bundle="${loc}" key="language.lName"/></th>
            </tr>
<c:forEach var="customer" items="${customers}">
    <c:if test="${customer.block}">
           <tr>
                    <td><c:out value="${customer.id}" /></td>
                    <td><c:out value="${customer.firstName}"/></td>
                    <td><c:out value="${customer.lastName}"/></td>
           </tr>
</c:if>
</c:forEach>
</table>
                    <div class="col-md-10 p-lg-2 mx-auto my-0">

                                 <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/shop?command=go_to_profile"> <-   <fmt:message bundle="${loc}" key="language.account"/> </a>
                     </div>

    </div>

</body>

<jsp:include page="blocks/footer.jsp"></jsp:include>

</html>