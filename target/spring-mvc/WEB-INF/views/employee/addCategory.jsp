<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="commonE" tagdir="/WEB-INF/tags/common/employee" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <title>${not empty title ? title : "Add a Category"}</title>
    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<c:url var="actionUrl" value="/admin/categories/add"/>
<commonE:header-employee/>
<div class="row mx-3 my-4">
    <div class="col-12 col-sm-6 col-md-5 col-lg-4">
        <form:form method="post" action="${actionUrl}" modelAttribute="category">
            <div class="mb-3">
                <label for="categoryName" class="form-label">Category</label>
                <form:input type="text" class="form-control" required="true" id="categoryName" name="name" path="name"/>
            </div>
            <div class="mb-3">
                <label for="categoryDescription" class="form-label">Description</label>
                <form:input type="text" class="form-control" required="true" id="categoryDescription" name="description" path="description"/>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</div>

<%-- Javascript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>