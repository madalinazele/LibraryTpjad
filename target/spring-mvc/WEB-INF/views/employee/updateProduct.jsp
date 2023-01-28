<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="commonE" tagdir="/WEB-INF/tags/common/employee" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE>
<html>
<head>
    <title>Lego Shop</title>

    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>

<body>
<c:url var="actionUrl" value="/admin/products/update"/>
<commonE:header-employee/>
<c:if test= "${not empty errorMessage}">
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>${errorMessage}</strong> Be sure to choose an image when updating a product.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
<div class="row mx-3 my-4">
    <div class="col-12 col-sm-6 col-md-5 col-lg-4">
        <form:form method="post" action="${actionUrl}" modelAttribute="product" enctype='multipart/form-data'>

            <div class="mb-3">
                <form:input path="id" type="hidden" class="form-control" id="id" value="${product.id}"/>
            </div>
            <div class="mb-3">
                <form:label path="name" class="form-label">Name</form:label>
                <form:input type="text" class="form-control" id="name" path="name"/>
            </div>
            <div class="mb-3">
                <form:label path="price" class="form-label">Price</form:label>
                <form:input type="number" step="any" min="0" class="form-control" id="price" path="price"/>
            </div>
            <div class="mb-3">
                <form:label path="stock" class="form-label">Stock</form:label>
                <form:input type="number" class="form-control" id="stock" path="stock"/>
            </div>
            <div class="mb-3">
                <form:label path="manufacturerPartNumber" class="form-label">Manufacturer</form:label>
                <form:input type="number"  class="form-control" path="manufacturerPartNumber"/>
            </div>
            Select a category:&nbsp;&nbsp;&nbsp;
            <form:select path="category.id">
                <c:forEach items="${categories}" var="category">
                    <form:option value="${category.id}">${category.name}</form:option>
                </c:forEach>
            </form:select>
            <br/><br/>
            <div class="mb-3">
                <form:label path="description" class="form-label">Description</form:label>
                <form:input type="text" class="form-control" path="description" required="true"/>
            </div>
            <div class="mb-3">
                <img src="<c:url value="${product.imagePath}"/>" style="height: auto; width: 200px">
                <form:label path="imageFile" class="form-label">Change the picture (will keep the current image if empty)</form:label>
                <form:input type="file" path="imageFile" accept="image/*" class="form-control"/>

            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</div>

<common:footer/>

<%-- Javascript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
