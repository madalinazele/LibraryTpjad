<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="commonE" tagdir="/WEB-INF/tags/common/employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View categories</title>
    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<commonE:header-employee/>


<div class="container">
    <h2 class="text-center m-3">Categories</h2>
    <div style="margin-left: 10px">
        <a class="btn btn-dark" href="<c:url value="/admin/categories/add"/>">Add new category</a>
    </div>

    <div class="row">
        <c:forEach var="category" items="${categories}">
            <div class="col-3">
                <commonE:categoryCard category="${category}"/>
            </div>
        </c:forEach>
    </div>

</div>

<%-- Javascript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>