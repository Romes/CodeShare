<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link type="text/css" href="<c:url value='/javascripts/codepress/languages/java.css'/>" rel="stylesheet" id="cp-lang-style" />
   
<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		 ${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="<c:url value="/codes"/>" method="post" >
  
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
    
    <input type="radio" name="langs" value="java" <c:if test="${code.lang eq \"java\" }"> checked </c:if> onclick="javascript:changeLang('java');"> Java 
    <input type="radio" name="langs" value="csharp" <c:if test="${code.lang eq \"csharp\" }"> checked </c:if> onclick="javascript:changeLang('csharp');"> C# 
    <input type="radio" name="langs" value="ruby" <c:if test="${code.lang eq \"ruby\" }"> checked </c:if> onclick="javascript:changeLang('ruby');"> Ruby
	<input type="radio" name="langs" value="php" <c:if test="${code.lang eq \"php\" }"> checked </c:if> onclick="javascript:changeLang('php');"> PHP
<br />
    
    <input type="hidden" name="code.lang" id="code.lang" value="${code.lang}"/>
  </div>
  <div class="field">
<!--    Trecho:<br />-->
    <textarea id="codeArea"  style="width: 95%;max-width: 95%; max-height:253px; height: 253px" class="codepress java" >${code.snippet }</textarea>
    <input type="hidden" id="snippet" name="code.snippet" value="${code.snippet}" />
  </div>
  <div class="actions">
    <button type="submit" onclick="javascript:enviar(codeArea.getCode())">Salvar</button>
  </div>
</form>

<script type="text/javascript" >
	function enviar(code)
	{
		$('#snippet').val(code);
	}
	function changeLang(lang)
	{
		$('[name=code.lang]').val(lang);
		var text = codeArea.getCode();
		codeArea.edit('codeArea',lang);
		codeArea.toggleEditor();
		codeArea.setCode(text);
		codeArea.toggleEditor();
	}

</script>

<div class="actions2">
 <form action="<c:url value="/codes/${code.id}"/>" method="post">
	    	  <input type="hidden" name="_method" value="delete"/>
	    	  <button type="submit" onclick="return confirm('Are you sure?')">Apagar</button>
		    </form>
		    </div>
<!--<a href="<c:url value="/codes"/>">Back</a>-->
