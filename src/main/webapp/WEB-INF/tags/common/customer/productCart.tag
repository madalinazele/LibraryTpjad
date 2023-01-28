<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="cartEntryDto" type="com.library.facade.dto.CartEntryDto" required="true" %>


<c:url var="actionUrl" value="/cart/delete/${cartEntryDto.id}"/>

<div class="card" style="width:25rem; margin-bottom: 15px;">
    <img src="<c:url value="images/${cartEntryDto.productCustomerDto.imagePath}"/>"/>

    <div class="card-body">
        <h5 class="card-title">${cartEntryDto.productCustomerDto.name}</h5>
    </div>

    <ul class="list-group list-group-flush">
        <li class="list-group-item">
            <div>Price: ${cartEntryDto.productCustomerDto.price} RON</div>
        </li>
        <li class="list-group-item">
            <form method="POST" action="<c:url value="/cart/update/${cartEntryDto.id}"/>">
                <label for="quantity">
                    Quantity :
                </label>
                <input name="quantity" id="quantity" type="number" min="1" value="${cartEntryDto.quantity}">
                <button class="btn btn-dark" type="submit">Update</button>
            </form>
        </li>
    </ul>

    <form:form method="post" action="${actionUrl}" cssStyle="float: right; margin-bottom: 0">
        <button type="submit" class="btn btn-dark"
                onclick="return confirm('You are about to delete the product ${cartEntryDto.productCustomerDto.name} ?')">
            Delete
        </button>
    </form:form>

</div>