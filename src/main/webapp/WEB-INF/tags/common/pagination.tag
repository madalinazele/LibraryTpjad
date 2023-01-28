<%@ attribute name="currentQuery" %>
<%@ attribute name="currentPage" %>
<%@ attribute name="noOfPages" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav aria-label="...">
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <a class="page-link" href="<c:url value="/products?page=1"/>">First</a>
        </li>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active" aria-current="page"><a class="page-link" href="#">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <c:url value="${currentQuery}" var="pageUrl">
                        <c:param name="page" value="${i}"/>
                    </c:url>
                    <li class="page-item">
                        <a class="page-link" href="${pageUrl}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="${currentQuery}" var="lastPageUrl">
            <c:param name="page" value="${noOfPages}"/>
        </c:url>
        <li class="page-item">
            <a class="page-link" href="${lastPageUrl}">Last</a>
        </li>

    </ul>
</nav>

