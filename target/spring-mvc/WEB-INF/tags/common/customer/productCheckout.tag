<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="cartEntryDto" type="com.library.facade.dto.CartEntryDto" required="true" %>

<div class="card" style="width:25rem; margin-bottom: 15px;">
    <img src="<c:url value="images/${cartEntryDto.productCustomerDto.imagePath}"/>"/>

    <div class="card-body">
        <h5 class="card-title"><c:out value="${cartEntryDto.productCustomerDto.name}"/></h5>
    </div>

    <ul class="list-group list-group-flush">
        <li class="list-group-item">
            <div>Price <c:out value="${cartEntryDto.productCustomerDto.price} RON"/></div>
        </li>
        <li class="list-group-item">
            <div>Quantity <c:out value="${cartEntryDto.quantity}"/></div>
        </li>
    </ul>

</div>