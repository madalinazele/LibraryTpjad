<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="commonE" tagdir="/WEB-INF/tags/common/employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE>
<html>
<head>
    <title>Lego Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<c:url var="actionUrl" value="/admin/products/details/${product.id}"/>

<commonE:header-employee/>

<form:form method="post" action="${actionUrl}" modelAttribute="product" enctype='multipart/form-data'>

    <div class="mb-3">
        <h2 class="text-center m-5">Description and reviews</h2>

        <div class="mb-5">
            <h4>What you need to know about the product</h4>

            <p><c:out value="${product.description}"/></p>
        </div>
    </div>

    <h4>Reviews</h4>

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
