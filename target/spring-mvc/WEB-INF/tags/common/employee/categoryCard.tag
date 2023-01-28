<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="category" type="com.library.facade.dto.CategoryDto" required="true" %>


<c:url var="actionUrl" value="/admin/categories/delete/${category.id}"/>
<div class="card m-2" style="width: 18rem">
    <div class="card-body" style="padding: 8px 0 0 0;" >
        <h5 style="padding: 0 8px 0 8px" class="card-title"><c:out value="${category.name}"/></h5>
        <div class="card-text" style="padding: 0 8px 0 8px; height: 80px">
            <c:out value="${category.description}"/>
        </div>
        <div class="card-footer">
            <a  class="btn btn-dark" href="<c:url value="/admin/categories/update/${category.id}"/>">Edit</a>
                <form:form method="post" action="${actionUrl}" cssStyle="float: right; margin-bottom: 0">
                    <button type="submit" class="btn btn-dark"
                            onclick="return confirm('You are about to delete the category ${category.name} which will make related products have no assigned category after.')">
                        Delete
                    </button>
                </form:form>
        </div>
    </div>

</div>