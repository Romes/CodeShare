<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<h1>Codeshare</h1>



<div id="insert" style="float:left; width:30%; max-width: 30%;">
	<%@include file="form.jsp"%>
</div>

<div id="search" style="float:right; width:70%;">
	<form action="<c:url value="/codes"/>" method="post">
	
		 <div class="actionSearch">
		   Palavra-chave:
    <input type="text" name="code.name" value="${code.tags}"/>
    		<button type="submit">Buscar</button>
  		</div>
	
	</form>




	<table>
	  <tr>
	   <th>name</th>
	   <th>tags</th>
	   <th>lang</th>
	   <th>snippet</th>
	   <th></th>
	   <th></th>
	   <th></th>
	  </tr>
	
	<c:forEach items="${codeList}" var="code">
	  <tr>
	      <td>
	      ${code.name}
	    </td>
	      <td>
	      ${code.tags}
	    </td>
	      <td>
	      ${code.lang}
	    </td>
	      <td>
	      ${code.snippet}
	    </td>
	      <td><a href="<c:url value="/codes/${code.id}"/>">show</a></td>
	    <td><a href="<c:url value="/codes/${code.id}/edit"/>">edit</a></td>
	    <td>
	      <form action="<c:url value="/codes/${code.id}"/>" method="post">
	    	  <input type="hidden" name="_method" value="delete"/>
	    	  <button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
		    </form>
		  </td>
	    </tr>
	</c:forEach>
	</table>
	
	<br />
	<a href="<c:url value="/codes/new"/>">New Code</a> 

</div>


</body>
