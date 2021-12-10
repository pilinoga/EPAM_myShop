<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 31.10.2021
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page ="blocks/header.jsp"></jsp:include>
<head>
    <title>Add product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
         <style>
                    table {
                    padding: 0;
                    margin-left: 450px;
                          }
                    p {
                    padding: 0;
                    margin-left: 280px;
                          }
                         </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
  <fmt:setBundle basename="language" var="loc"/>
<div class= "bg-light">

<div class="container" >

                <c:if test="${error_product}">
               <h5><p><font color = "red"> <fmt:message bundle="${loc}" key="language.invalidData"/></ font></p></h5>
               </c:if>

             <c:if test="${add_product }">
             <h5><p><font color = "red"> <fmt:message bundle="${loc}" key="language.addToDB"/> </ font></p></h5>
             </c:if>

<h3> <p><font color = "#3594B4"><fmt:message bundle="${loc}" key="language.addProduct"/></ font> </p></h3>
<form name="editProfile" method="POST" action="shop">
<input type="hidden" name="command" value="add_new_product"/>

<div class="col-xl-7 col-lg-9 col-md-12 col-sm-12 col-12">
<div class="card h-60">
	<div class="card-body">
		<div class="row gutters">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
				<h6 class="mb-2 text-primary"><fmt:message bundle="${loc}" key="language.prodInfo"/></h6>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="firstName"><fmt:message bundle="${loc}" key="language.title"/></label>
					<input type="text" class="form-control" name="name" placeholder="${product.name}">
				</div>
			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            				<div class="form-group">
            					<label for="lastName"><fmt:message bundle="${loc}" key="language.description"/></label>
            					<input type="text" class="form-control" name="description" placeholder="${product.description}">
            				</div>
            			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
            				<div class="form-group">
            					<label for="balance"><fmt:message bundle="${loc}" key="language.price"/></label>
            					<input type="text" class="form-control" name="price" placeholder="${product.price}">
            				</div>
            			</div>
			<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
				<div class="form-group">
					<label for="eMail"><fmt:message bundle="${loc}" key="language.specification"/></label>
					<input type="text" class="form-control" name="specification" placeholder="${product.specification}">
				</div>
			</div>

		</div>




	</div>
</div>
		<div class="col-md-1 p-lg-2 mx-auto my-3">
        <input class="btn btn-primary" type="submit" value="<fmt:message bundle="${loc}" key="language.save"/>">
</div>
</div>
</div>

 </form>


    <div class="col-md-10 p-lg-2 mx-auto my-0">

              <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/shop?command=go_to_profile"> <fmt:message bundle="${loc}" key="language.cancel"/></a>
  </div>
  </div>

<body>
<jsp:include page="blocks/footer.jsp"></jsp:include>
</html>