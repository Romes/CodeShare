<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="<c:url value='/javascripts/jquery.min.js'/>"></script>
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
    Nome:
<!--    <br />-->
    <input type="text" name="code.name" value="${code.name}" class="inputField"/>
  </div>
  <div class="field">
    Tags:
<!--    <br />-->
    <input type="text" name="code.tags" value="${code.tags}" class="inputField"/>
  </div>
  <div class="field">
    Linguagem:
<!--    <br />-->
    
    <input type="radio"  name="langs" value="Java" <c:if test="${code.lang eq \"Java\" }"> checked </c:if> onclick="javascript:changeLang('Java');"> JAVA 
    <input type="radio" name="langs" value="C" <c:if test="${code.lang eq \"C\" }"> checked </c:if> onclick="javascript:changeLang('C');"> C 
    <input type="radio" name="langs" value="Ruby" <c:if test="${code.lang eq \"Ruby\" }"> checked </c:if> onclick="javascript:changeLang('Ruby');"> RUBY 
	<input type="radio" name="langs" value="Python" <c:if test="${code.lang eq \"Python\" }"> checked </c:if> onclick="javascript:changeLang('Python');"> Python

<br />
<!--	<select name="code.langs" >-->
<!--	  <option value="Java" onclick="" >Java</option>-->
<!--	  <option value="C">C</option>-->
<!--	  <option value="Python">Python</option>-->
<!--	  <option value="Ruby">Ruby</option>-->
<!--	</select>-->
    
    <input type="hidden" name="code.lang" id="code.lang" value="${code.lang}"/>
  </div>
  <div class="field">
<!--    Trecho:<br />-->
    <textarea name="code.snippet" style="width: 95%;max-width: 95%; max-height:253px; height: 253px" >${code.snippet }</textarea>
<!--    <input type="text" name="code.snippet" value="${code.snippet}" />-->
  </div>
  <div class="actions">
    <button type="submit">Salvar</button>
  </div>
</form>

<script type="text/javascript" >
	function changeLang(lang)
	{
		$('[name=code.lang]').val(lang);
	}

</script>

<div class="actions2">
 <form action="<c:url value="/codes/${code.id}"/>" method="post">
	    	  <input type="hidden" name="_method" value="delete"/>
	    	  <button type="submit" onclick="return confirm('Are you sure?')">Apagar</button>
		    </form>
		    </div>
<!--<a href="<c:url value="/codes"/>">Back</a>-->

