<%--
  Created by IntelliJ IDEA.
  User: Hammer
  Date: 14/03/2022
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="customer" tagdir="/WEB-INF/tags/common/customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register new account</title>
    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<c:url var="actionUrl" value="/register"/>

<customer:header-client/>

<div class="mx-auto p-3" style="width:60%">
    <hr class="mt-3 mb-3"/>
    <h1 class="text-primary text-center">Register a new user account</h1>
    <h5 class="text-muted text-center">Don't have an account? Create one right here, it only takes a minute!</h5>
    <hr class="mt-3 mb-3"/>
    <br>
    <div class="mx-auto card" style="width:50%">
        <div class="card-body">


            <form:form method="post" action="${actionUrl}" modelAttribute="user">

                <c:if test="${not empty errorMessage}">
                    <div class="aria-errormessage=">${errorMessage}</div>
                </c:if>

                <div class="form-group mt-3">
                    <form:label path="name" class="form-label">Name</form:label>
                    <form:errors path="name" cssClass="error" element="div"/>
                    <form:input type="text" path="name" class="form-control" required="true"/>
                </div>
                <div class="form-group mt-3">
                    <form:label path="email" class="form-label">Email</form:label>
                    <form:errors path="email" cssClass="error" element="div"/>
                    <form:input type="email" path="email" class="form-control" required="true"/>
                </div>
                <div class="row mt-3">
                    <form:label path="password" class="form-label">Password</form:label>
                    <form:errors path="password" cssClass="error" element="div"/>

                        <%-- TODO JI-27:    !!  BUG  !!     --%>
                    <form:errors path="matchingPassword" cssClass="error"/>
                        <%-- TODO JI-27: Properly implement form error for Object<UserDto>, not field path! --%>

                    <div class="col">
                        <form:input type="password" path="password" class="form-control" placeholder="Input password"
                                    required="true"/>
                    </div>
                    <div class="col">
                        <form:input type="password" path="matchingPassword" class="form-control"
                                    placeholder="Confirm password" required="true"/>
                    </div>
                </div>
                <div class="form-group mt-3">
                    <form:label path="address" class="form-label">Address</form:label>
                    <form:errors path="address" cssClass="error" element="div"/>
                    <form:input type="text" path="address" class="form-control" required="true"/>
                </div>
                <div class="form-group mt-3">
                    <form:label path="phoneNo" class="form-label">Phone number</form:label>
                    <form:errors path="phoneNo" cssClass="error" element="div"/>
                    <form:input type="text" path="phoneNo" class="form-control" required="true"/>
                </div>
                <div class="form-group mt-4 card-footer">
                    <button type="submit" class="btn btn-dark w-100">Create account</button>
                </div>
            </form:form>

        </div>
    </div>
</div>
<%-- Javascript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>

</html>

