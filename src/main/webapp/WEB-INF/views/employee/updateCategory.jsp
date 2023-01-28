<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="commonE" tagdir="/WEB-INF/tags/common/employee" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Lego Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <c:url var="actionUrl" value="/admin/categories/update"/>
    <commonE:header-employee/>

    <div class="row mx-3 my-4">
        <div class="col-12 col-sm-6 col-md-5 col-lg4">
            <form:form method="post" action="${actionUrl}" modelAttribute="category">
                    <div class="mb-3">
                        <label for="name" class="form-label" hidden>Name</label>
                        <form:input path="id" type="hidden" class="form-control" id="id" name="id" value="${category.id}"/>
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <form:input path="name" type="text" class="form-control" id="name" name="name" value="${category.name}"/>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <form:input path="description" type="text" class="form-control" id="description" name="description" value="${category.description}"/>
                    </div>
                <button type="submit" class="btn btn-dark">Submit</button>
            </form:form>
        </div>
    </div>

    <common:footer/>
</body>
</html>
