<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 29.10.2021
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
    <style>
            table {
            padding: 0;
            margin-left: 80px;
                  }
            p {
            padding: 0;
            margin-left: 60px;
                  }
                 </style>
</head>
<body>
 <fmt:setLocale value="${sessionScope.language}"/>
  <fmt:setBundle basename="language" var="loc"/>
<jsp:include page ="blocks/header.jsp"></jsp:include>


<div align="left" class= "bg-light">
    <h6>
  <p align="left"> <font color = "#3F4344">  <fmt:message bundle="${loc}" key="language.blockedCustomers"/>
                                               </ font> </p>
    </h6>
<h3> <p> <font color = "#3594B4">  <fmt:message bundle="${loc}" key="language.customers"/> </ font></p></h3>
        <table border="0" cellpadding="5">
            <tr>
                <th><fmt:message bundle="${loc}" key="language.id"/></th>
                <th><fmt:message bundle="${loc}" key="language.fName"/></th>
                <th><fmt:message bundle="${loc}" key="language.lName"/></th>
                <th></th>
                </tr>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td><c:out value="${customer.id}" /></td>
                    <td><c:out value="${customer.firstName}"/></td>
                    <td><c:out value="${customer.lastName}"/></td>

                     <form name="block" method="POST" action="shop">
                      <input type="hidden" name="command" value="block"/>
                       <input type="hidden" name="page" value="${requestScope['page']}" />
                        <input type="hidden" name="id" value="${customer.id}" />
                        <td> <c:if test="${!customer.block}">
                           <div class="col-md-5 p-lg-1 mx-auto my-0">
                              <input class="btn btn-outline-secondary" type="submit" value="<fmt:message bundle="${loc}" key="language.block"/>">
                         </div> </td></c:if>
                        <c:if test="${customer.block}">
                        <div class="col-md-5 p-lg-1 mx-auto my-0">
                               <input class="btn btn-danger" type="submit" value="<fmt:message bundle="${loc}" key="language.unblock"/>">
                         </div> </td></c:if>
                </form>
                </tr>
            </c:forEach>
        </table>
    </div>
<nav aria-label="pagination">
      <ul class="pagination justify-content-center">
      <c:if test="${requestScope['page']>1}">
        <li class="page-item">
        <a class="page-link" href="shop?command=show_customers&page=${requestScope['page']-1}">Previous</a>
        </li>
        </c:if>
        <li class="page-item"><a class="page-link" href="shop?command=show_customers&page=1">1</a></li>
        <li class="page-item"><a class="page-link" href="shop?command=show_customers&page=2">2</a></li>
        <c:if test="${requestScope['page']<2}">
        <li class="page-item">
          <a class="page-link" href="shop?command=show_customers&page=${requestScope['page']+1}">Next</a>
        </li>
        </c:if>
      </ul>
</nav>

</body>
<jsp:include page="blocks/footer.jsp"></jsp:include>
</html>

