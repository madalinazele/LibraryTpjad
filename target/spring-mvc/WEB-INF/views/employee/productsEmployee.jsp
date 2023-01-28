<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="commonC" tagdir="/WEB-INF/tags/common/customer" %>
<%@ taglib prefix="commonE" tagdir="/WEB-INF/tags/common/employee" %>
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

<common:pagination noOfPages="${noOfPages}" currentPage="${currentPage}"/>


<div class="container">
    <h2 class="text-center m-3">All products</h2>
    <div>
        <a class="btn btn-dark" href="<c:url value="/admin/products/add"/>">Add new product</a>
    </div>

    <div class="row">
        <c:forEach var="processedProduct" items="${processedProducts}" varStatus="status">
            <div class="col-4">
                <commonE:productCard product="${processedProduct}"/>
            </div>
        </c:forEach>
    </div>
</div>

<common:footer/>

<%-- Javascript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
