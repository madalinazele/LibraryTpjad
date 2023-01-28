<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common/customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.library.core.model.PaymentMethod" %>

<html>
<head>
    <title>Lego Shop</title>

    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>

<body>
<c:url var="actionUrl" value="/checkout/submit"/>
<common:header-client/>

<div class="row mx-3 my-4">
    <div class="col-12 col-sm-6 col-md-5 col-lg4">

        <div class="mx-auto card" style="width: 80%">
            <div class="card-body">
                <form:form method="post" modelAttribute="checkout" action="${actionUrl}">
                    <div class="form-group mt-3">
                        <form:label class="form-label" path="name">Name</form:label>
                        <form:input type="text" path="name" class="form-control" id="name" required="true"/>
                    </div>
                    <div class="form-group mt-3">
                        <form:label class="form-label" path="address">Address</form:label>
                        <form:input path="address" type="text" class="form-control" id="address" required="true"/>
                    </div>
                    <div class="form-group mt-3">
                        <form:label class="form-label" path="phoneNo">Phone number</form:label>
                        <form:input path="phoneNo" type="text" class="form-control" id="phoneNo" required="true"/>
                    </div>

                    <div class="card-body">
                        <form:select path="paymentMethod" items="${PaymentMethod.values()}"/>
                    </div>

                    <div class="card" style="width: 20rem; margin: 15px;">
                        <div class="row">
                            <c:forEach var="cartEntryDto" items="${cart.cartEntryDtoList}">
                                <div class="row">
                                    <common:productCheckout cartEntryDto="${cartEntryDto}"/>
                                </div>
                            </c:forEach>

                            <div class="form-group mt-3">
                                <h5>Total Price:</h5>
                                <form:input type="text" path="totalPrice" class="form-control" id="totalPrice" readonly="true"/>
                            </div>
                        </div>
                    </div>

                    <div class="form-group mt-4 card-footer">
                        <button type="submit" class="btn btn-dark w-100">Submit</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
