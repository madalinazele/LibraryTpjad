<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="commonC" tagdir="/WEB-INF/tags/common/customer" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="actionUrl" value="/cart/add/${product.id}"/>

<html>
<head>
    <title>Product Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <commonC:header-client/>

    <div class="card" style="width: 20rem; margin: 15px;">
        <img src="<c:url value="${product.imagePath}"/>" alt="No picture for this product.">
        <div class="card-body">
            <h5 class="card-title"><c:out value="${product.name}"/></h5>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">
                <div>Price: <c:out value="${product.price} RON"/></div>
            </li>
            <li class="list-group-item">
                <div>
                    <c:choose>
                        <c:when test="${product.stock > 0}">
                            Stock: <b style="color:darkgreen;">In Stock</b>
                        </c:when>
                        <c:otherwise>
                            Stock: <b style="color:red;">Out Of Stock</b>
                        </c:otherwise>
                    </c:choose>
                </div>
            </li>
            <li class="list-group-item">
                <div>Code: <c:out value="${product.manufacturerPartNumber}"/></div>
            </li>
            <li class="list-group-item">
                <div>Category: <c:out value="${product.category.name}"/></div>
            </li>
        </ul>
    </div>

    <div style="width: 250px;">
        <c:choose>
            <c:when test="${product.stock eq 0}">
                <button type="submit" class="btn btn-dark" disabled>Add to cart</button>
            </c:when>
            <c:otherwise>
                <form method="post" action="${actionUrl}">
                    <button type="submit" class="btn btn-dark">Add to cart</button>
                    <label for="quantity">Quantity:</label>
                    <select name="quantity" id="quantity" style="float: right; margin-top: 4px;">
                        <c:choose>
                            <c:when test="${product.stock le 10}">
                                <c:set var="quantity" value="${product.stock}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="quantity" value="${10}"/>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach begin="1" end="${quantity}" var="val">
                            <option value="${val}">${val}</option>
                        </c:forEach>
                    </select>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="container" style="margin-left: 0;">
        <hr>
        <h3>What you need to know about the product</h3>
        <p><c:out value="${product.description}"/></p>
        <hr>
        <h3>Reviews</h3>
    </div>

    <common:footer/>
</body>
</html>
