<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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

<commonC:header-client/>

<common:pagination noOfPages="${noOfPages}" currentPage="${currentPage}" currentQuery="${currentQuery}"/>
<h2 class="text-center m-3">All products</h2>

<div class="container-fluid" style="position: relative">

    <div class="row">
        <div class="col-2">
            <commonC:filterComponent facets="${facets}"/>
        </div>

        <div class="col-10">
            <div class="row">
                <c:forEach var="processedProduct" items="${processedProducts}" varStatus="status">
                    <div class="col-4">
                        <commonC:productCardClient product="${processedProduct}"/>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</div>

<common:pagination noOfPages="${noOfPages}" currentPage="${currentPage}"/>

<common:footer/>

<%-- Javascript --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>
