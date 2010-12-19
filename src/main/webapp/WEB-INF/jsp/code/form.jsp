<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		 ${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="<c:url value="/codes"/>" method="post">
  
  <c:if test="${not empty code.id}">
    <input type="hidden" name="code.id" value="${code.id}"/>
    <input type="hidden" name="_method" value="put"/>
  </c:if>

  <div class="field">
    Name:<br />
    <input type="text" name="code.name" value="${code.name}"/>
  </div>
  <div class="field">
    Tags:<br />
    <input type="text" name="code.tags" value="${code.tags}"/>
  </div>
  <div class="field">
    Lang:<br />
    <input type="text" name="code.lang" value="${code.lang}"/>
  </div>
  <div class="field">
    Snippet:<br />
    <textarea rows="10" cols="30" name="code.snippet" style="max-width: 90%;" >${code.snippet }</textarea>
<!--    <input type="text" name="code.snippet" value="${code.snippet}" />-->
  </div>
  <div class="actions">
    <button type="submit">send</button>
  </div>
</form>

<a href="<c:url value="/codes"/>">Back</a>

