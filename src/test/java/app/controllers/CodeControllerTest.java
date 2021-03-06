package app.controllers;

import javax.persistence.EntityManager;


import org.jmock.Mockery;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

import antlr.collections.List;
import app.models.Code;
import app.models.SearchParameter;
import app.repositories.CodeRepository;
import app.repositories.CodeRepositoryImpl;
import app.repositories.Repository;


public class CodeControllerTest {
	
	Mockery context = new Mockery();
	
	@Test 
	public void fakeTest() {
		assertNotNull("put something real.", new CodeController(null, null, null,null));
 	}


	@Test 
	public void searchParameterTest(){
		MockResult result = new MockResult();
		/* Problema do Repositorio */
		CodeRepository repository = context.mock(CodeRepositoryImpl.class);
		CodeController controller = new CodeController(result, repository, new MockValidator(), new SearchParameter());
		Code code = createCode("Code","code","C","<code>");
		controller.create(code);
		controller.searchParameter("Code");
		List list = result.included("Code");
		Assert.assertNotNull(list);
	}
	
	@Test 
	public void createTest(){
		MockResult result = new MockResult();
		/* Problema do Repositorio */
		CodeRepository repository = context.mock(CodeRepositoryImpl.class);
		CodeController controller = new CodeController(result, repository, new MockValidator(), new SearchParameter());
		Code code = createCode("Code","code","C","<code>");
		controller.create(code);
		controller.index(null,false);
		List list = result.included("Code");
		Assert.assertNotNull(list);
	}
	
	@Test
	public void destroyCodeTest(){
		MockResult result = new MockResult();
		/* Problema do Repositorio */
		CodeRepository repository = context.mock(CodeRepositoryImpl.class);
		CodeController controller = new CodeController(result, repository, new MockValidator(), new SearchParameter());
		Code code = createCode("Code","code","C","<code>");
		controller.create(code);
		controller.destroy(code);
		List list = result.included("Code");
		Assert.assertNull(list); 
	}
		
	private Code createCode(String name, String tags, String lang, String snippet){
		Code code = new Code();
		code.setName(name);
		code.setTags(tags);
		code.setLang(lang);
		code.setSnippet(snippet);
		return code;
	}
	
}
