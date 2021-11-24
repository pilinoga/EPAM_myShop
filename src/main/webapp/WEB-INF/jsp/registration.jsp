
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page ="blocks/header.jsp"></jsp:include>
<head>
    <title>Registration</title>
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
       <style>

              p {
              padding: 0;
              margin-left: 580px;
                    }
                   </style>
  </head>
  <body>
  <fmt:setLocale value="${sessionScope.language}"/>
  <fmt:setBundle basename="language" var="loc"/>
  <div class= "bg-light">

   <c:if test="${error_login_unique != null}">
                  <h7><p><font color = "red"> <c:out value="${error_login_unique }" /></ font><p></h7></c:if>
   <c:if test="${error_login != null}">
                   <h6><p><font color = "red"> <c:out value="${error_login}" /></ font><p></h6></c:if>

  <font color = "#3594B4"> </ font>
          <form name="loginForm" method="POST" action="shop">
          <input type="hidden" name="command" value="registration" />
         <section class="vh-90" style="background-color: bg-light;">
           <div class="container py-4 h-20">
             <div class="row d-flex justify-content-center align-items-center h-40">
               <div class="col-12 col-md-5 col-lg-4 col-xl-4">
                 <div class="card shadow-2-strong" style="border-radius: 1rem;">
                   <div class="card-body p-3 text-center">

                     <h4 class="mb-3">
                     <fmt:message bundle="${loc}" key="language.signUp"/>
                     </h4>

                     <div class="form-outline mb-3">
                       <input type="text" name="login" class="form-control form-control-lg" placeholder="<fmt:message bundle="${loc}" key="language.enterLogin"/>" />
                       <label class="form-label" for="typeEmailX-2"><fmt:message bundle="${loc}" key="language.loginLimit"/></label>
                     </div>

                     <div class="form-outline mb-2">
                       <input type="password" name="password" class="form-control form-control-lg" placeholder="<fmt:message bundle="${loc}" key="language.enterPassword"/>" />
                       <label class="form-label" for="typePasswordX-2"><fmt:message bundle="${loc}" key="language.passwordLimit"/></label>
                     </div>

                      <button class="btn btn-primary" type="submit"><fmt:message bundle="${loc}" key="language.next"/></button>



                   </div>
                 </div>
               </div>
             </div>
           </div>
         </section>
         </form>
         <div class="col-md-0 p-lg-0 mx-auto my-1">
        <h6> <p> <a href="${pageContext.request.contextPath}/shop?command=go_to_login" class="nav-link active" >  <fmt:message bundle="${loc}" key="language.haveProfile"/></a></p></h6>
          </div>
   </div>

  </body>
  <jsp:include page="blocks/footer.jsp"></jsp:include>
  </html>