<%--
  Created by IntelliJ IDEA.
  User: amosandreica
  Date: 3/9/22
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="commonE" tagdir="/WEB-INF/tags/common/employee" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add product</title>
    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<c:url var="actionUrl" value="/admin/products/add"/>
<commonE:header-employee/>
<c:if test= "${not empty errorMessage}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>${errorMessage}</strong> Be sure to choose an image when creating a product.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

<div class="row mx-3 my-4">
    <div class="col-12 col-sm-6 col-md-5 col-lg-4">
        <h1>Add a new product</h1>
        <br>
        <form:form method="post" action="${actionUrl}" modelAttribute="product" enctype='multipart/form-data'>
            <div class="mb-3">
                <form:label path="name" class="form-label">Name</form:label>
                <form:input type="text" path="name" class="form-control" required="true" />
            </div>
            <div class="mb-3">
                <form:label path="manufacturerPartNumber" class="form-label">Manufacturer Part No.</form:label>
                <form:input type="number" path="manufacturerPartNumber" class="form-control" required="true"/>
            </div>
            <form:label path="category">Select a category:&nbsp;&nbsp;&nbsp;</form:label>
            <form:select path="category.id">
                <c:forEach items="${categories}" var="category">
                    <form:option value="${category.id}">${category.name}</form:option>
                </c:forEach>
            </form:select>
            <br/><br/>
            <div class="mb-3">
                <form:label path="stock" class="form-label">Stock</form:label>
                <form:input type="number" path="stock" class="form-control" required="true"/>
            </div>
            <div class="mb-3">
                <form:label path="price" class="form-label">Price</form:label>
                <form:input type="number" step="any" min="0" path="price" class="form-control" required="true"/>
            </div>
            <div class="mb-3">
                <form:label path="description" class="form-label">Description</form:label>
                <form:input type="text" path="description" class="form-control" required="true"/>
            </div>
            <div class="mb-3">
                <form:label path="imageFile" class="form-label">Upload a picture</form:label>
                <form:input type="file" path="imageFile" class="form-control" accept="image/*" required="true"/>
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

