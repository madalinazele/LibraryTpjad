<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="product" type="com.library.facade.dto.ProductCustomerDto" required="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url var="actionUrl" value="/admin/products/delete/${product.id}"/>

<div class="card" style="width: 20rem; margin-bottom: 15px;">
    <img src="<c:url value="${product.imagePath}"/>">
    <div class="card-body">
        <h5 class="card-title"><c:out value="${product.name}"/></h5>
        <div>Description: <c:out value="${product.description}"/></div>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item">
            <div>Price: <c:out value="${product.price}"/></div>
        </li>
        <li class="list-group-item">
            <div>
                <c:choose>
                    <c:when test="${product.stock > 0}">
                        Stock: <b style="color:darkgreen;">${product.stock}</b> pieces
                    </c:when>
                    <c:otherwise>
                        Stock: <b style="color:red;">${product.stock}</b>
                    </c:otherwise>
                </c:choose>
            </div>
        </li>
        <li class="list-group-item">
            <div>Code: <c:out value="${product.manufacturerPartNumber}"/></div>
        </li>
        <li class="list-group-item">
            <c:choose>
                <c:when test="${empty product.category}">
                    Category : <b style="color:red;">No category</b>
                </c:when>
                <c:otherwise>
                    Category : <b style="color:darkgreen;">${product.category.name}</b>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>

    <div style="margin-bottom: 10px; margin-left: 10px; margin-top: 10px; margin-right: 10px" ; width: 18rem;>

        <a style="margin-bottom: 15px;" class="btn btn-dark"
           href="<c:url value="/admin/products/update/${product.id}"/>">Update</a>
        <form:form method="post" action="${actionUrl}">
            <button type="submit" class="btn btn-dark"
                    onclick="return confirm('You are about to delete the product ${product.name}. Are you sure?')">
                Delete
            </button>
        </form:form>
        <a class="btn btn-dark" href="<c:url value="/admin/products/details/${product.id}"/>">More details</a>

    </div>
</div>