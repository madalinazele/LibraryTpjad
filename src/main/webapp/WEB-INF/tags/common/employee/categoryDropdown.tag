<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="categories" required="true" type="com.library.facade.dto.CategoryDto"%>

<div>
  Select a category:&nbsp;&nbsp;&nbsp;
  <select name="category">
    <c:forEach items="${categories}" var="category">
      <option value="${category.id}">${category.name}</option>
    </c:forEach>
  </select>
  <br/><br/>
</div>