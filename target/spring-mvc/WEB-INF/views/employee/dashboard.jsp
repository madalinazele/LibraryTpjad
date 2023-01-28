<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="commonE" tagdir="/WEB-INF/tags/common/employee" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Lego Shop</title>

    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>

<commonE:header-employee/>

<div class="container">
    <div class="d-flex mt-5">

        <div class="card m-2" style="width: 15rem;">
            <div class="card-body">
                <h5 class="card-title">Sell a new product</h5>
                <p class="card-text">Here you can add a new LEGO set to be sold.</p>
            </div>
            <div class="card-footer">
                <a href="<c:url value="/admin/products/add"/>" class="btn btn-primary">Add product</a>
            </div>
        </div>

        <div class="card m-2" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Manage store products</h5>
                <p class="card-text">See and manage all current LEGO sets for sale in the store.</p>
            </div>
            <div class="card-footer">
                <a href="<c:url value="/admin/products"/>" class="btn btn-primary">Manage products</a>
            </div>
        </div>

        <div class="card m-2" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Add new category</h5>
                <p class="card-text">Add a custom category to be used in classifying LEGO sets.</p>
            </div>
            <div class="card-footer">
                <a href="<c:url value="/admin/categories/add"/>" class="btn btn-primary">Add category</a>
            </div>
        </div>

        <div class="card m-2" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Edit current categories</h5>
                <p class="card-text">See and manage current available product categories.</p>
            </div>
            <div class="card-footer">
                <a href="<c:url value="/admin/categories"/>" class="btn btn-primary">Manage categories</a>
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
