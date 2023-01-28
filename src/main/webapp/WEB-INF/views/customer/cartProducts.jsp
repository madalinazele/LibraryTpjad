<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="commonC" tagdir="/WEB-INF/tags/common/customer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<html>
<head>
    <title>Lego Shop</title>

    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>
<c:url var="actionUrl" value="/checkout"/>
<commonC:header-client/>

<h2 class="text-center m-3">All products in cart</h2>

<div class="container">
    <c:forEach var="cartEntryDto" items="${cartDto.cartEntryDtoList}" varStatus="status">
        <commonC:productCart cartEntryDto="${cartEntryDto}"/>
    </c:forEach>
    <h5> Total price : ${cartDto.totalPrice} RON</h5>

<a class="btn btn-dark" href="<c:url value="/checkout"/>">Submit</a>

<common:footer/>

<%-- Javascript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>

