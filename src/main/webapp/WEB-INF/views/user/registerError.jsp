<%--
  Created by IntelliJ IDEA.
  User: amosandreica
  Date: 3/16/22
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="commonC" tagdir="/WEB-INF/tags/common/customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <%-- Stylesheets --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<commonC:header-client/>

<div class="mx-auto p-3" style="width:60%">
    <hr class="mt-3 mb-3"/>
    <br>
    <div class="alert alert-danger" role="alert">
        ${errorMessage}
    </div>
</div>

</body>
</html>
