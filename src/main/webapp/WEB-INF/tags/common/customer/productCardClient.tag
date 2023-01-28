<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="product" type="com.library.facade.dto.ProductCustomerDto" required="true" %>

<c:url var="actionUrl" value="/cart/add/${product.id}"/>

<div class="card" style="width: 25rem; margin-bottom: 15px;">
    <img src="<c:url value="${product.imagePath}"/>">
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
            <div>
                <c:choose>
                    <c:when test="${empty product.category}">
                        Category : <b style="color:red;">No category</b>
                    </c:when>
                    <c:otherwise>
                        Category : <b style="color:darkgreen;">${product.category.name}</b>
                    </c:otherwise>
                </c:choose>
            </div>
        </li>
    </ul>

    <div style="margin: 10px;">

        <a class="btn btn-dark" href="<c:url value="/products/details/${product.id}"/>">More details</a>

        <%--@elvariable id="cart" type="com.library.facade.dto.CartEntryDto"--%>
        <form method="post" action="${actionUrl}">
            <button type="submit" class="btn btn-dark">Add to cart</button>
            <label for="quantity">Quantity:</label>
            <select name="quantity" id="quantity" style="float: right; margin-top: 4px;">
                <c:forEach begin="1" end="10" var="val">
                    <option value="${val}">${val}</option>
                </c:forEach>
            </select>
        </form>

    </div>
</div>