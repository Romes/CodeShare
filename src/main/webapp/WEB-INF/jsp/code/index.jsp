<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



</head>

<body>

<h1>Codeshare</h1>



<div id="insert" >
	<%@include file="form.jsp"%>
</div>

<div id="search" >
	<form action="<c:url value="/codes/search"/>" method="post">
	
		 <div class="actionSearch">
		   Palavra-chave:
   			<input type="text" name="search" value="${searchTag.searchTag}"/>
    		<button type="submit">Buscar</button>
  		</div>
  		
	
	</form>
	
	<div class="blank" ></div>
	<div id="searchTitle" >
		${searchResult }
	</div>

	<div class="blank" ></div>

	<div id="table">
		<table>
<!--		  <tr>-->
	<!--	   <th>name</th>-->
	<!--	   <th>tags</th>-->
	<!--	   <th>lang</th>-->
	<!--	   <th>snippet</th>-->
	<!--	   <th></th>-->
	<!--	   <th></th>-->
	<!--	   <th></th>-->
<!--		  </tr>-->
		
		<c:forEach items="${codeList}" var="code">
		  <tr>
		  	  <td>
			  	  <div class="searchResult">
			  	  	<a href="<c:url value="/codes/${code.id}/edit"/>">${code.name} ( ${code.lang} )</a>
			  	  	
			  	  	<br/>
			  	  	 ${code.tags}
			  	  	 <br/>
			  	  </div>
			  
	<!--		  	<a href="<c:url value="/codes/${code.id}/edit"/>">${code.name}</a>-->
			  </td>
	<!--	      <td>-->
	<!--	      ${code.tags}-->
	<!--	    </td>-->
	<!--	      <td>-->
	<!--	      ${code.lang}-->
	<!--	    </td>-->
		    
	<!--	    <td>-->
	<!--	      <form action="<c:url value="/codes/${code.id}"/>" method="post">-->
	<!--	    	  <input type="hidden" name="_method" value="delete"/>-->
	<!--	    	  <button type="submit" onclick="return confirm('Are you sure?')">destroy</button>-->
	<!--		    </form>-->
	<!--		  </td>-->
		    </tr>
		</c:forEach>
		</table>
	</div>

</div>


</body>
