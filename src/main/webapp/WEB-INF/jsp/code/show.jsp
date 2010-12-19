<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>

<p>
  <b>Name:</b>
   ${code.name}
</p>
<p>
  <b>Tags:</b>
   ${code.tags}
</p>
<p>
  <b>Lang:</b>
   ${code.lang}
</p>
<p>
  <b>Snippet:</b>
   ${code.snippet}
</p>

<a href="<c:url value="/codes/${code.id}/edit"/>">Edit</a>
<a href="<c:url value="/codes"/>">Back</a>

</body>
