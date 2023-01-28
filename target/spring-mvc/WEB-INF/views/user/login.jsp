<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="customer" tagdir="/WEB-INF/tags/common/customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<c:url var="actionUrl" value="/login"/>

<customer:header-client/>

<div class="mx-auto p-3" style="width:50%">
    <div class="col-12 col-sm-6 col-md-5 col-lg-4" style="width: 100%">

        <hr class="mt-3 mb-3"/>
        <h1 class="text-primary text-center">Login</h1>
        <h5 class="text-muted text-center">Don't have an account? Create one right <a class="nav-link" href="<c:url value="/register"/>">here!</a></h5>
        <hr class="mt-3 mb-3"/>
        <br>

        <c:if test="${error}">
            <div class="alert alert-danger" role="alert">
                Invalid credentials! Please try again!
            </div>
        </c:if>

        <div class="mx-auto card" style="width: 80%">
            <div class="card-body">

                <form method="post" action="${actionUrl}">
                    <div class="form-group mt-3">
                        <label class="form-label">Email</label>
                        <input name="username" type="text" class="form-control" id="username" required="true"/>
                    </div>
                    <div class="form-group mt-3">
                        <label class="form-label">Password</label>
                        <input name="password" type="password" class="form-control" id="password" required="true"/>
                    </div>
                    <div class="form-group mt-4 card-footer">
                        <button type="submit" class="btn btn-dark w-100">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<common:footer/>

<%-- Javascript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
