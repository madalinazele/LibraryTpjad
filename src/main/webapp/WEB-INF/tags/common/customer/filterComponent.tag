<%@ attribute name="facets" type="java.util.List" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="baseUrl" value="/products"/>

<div class="card sticky-top">

    <c:forEach items="${facets}" var="facet">
        <article class="card-group-item">
            <header class="card-header">
                <h6 class="title">${facet.name}</h6>
            </header>
            <div class="filter-content">
                <div class="card-body">
                    <c:forEach items="${facet.values}" var="facetValue">
                        <label class="form-check">
                            <c:url var="nextUrl" value="${facetValue.url}"/>
                            <input class="form-check-input" type="checkbox" value="" onchange="window.location.href='${nextUrl}'" ${facetValue.selected == true ? "checked" : ""}>
                            <span class="form-check-label">
                                ${facetValue.name} (${facetValue.count})
                            </span>
                        </label>
                    </c:forEach>
                </div>
            </div>
        </article>
    </c:forEach>
</div>