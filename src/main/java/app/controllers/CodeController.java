package app.controllers;

import java.util.List;

import app.models.Code;
import app.models.SearchParameter;
import app.repositories.CodeRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;

@Resource
public class CodeController {

	private final Result result;
	private final CodeRepository repository;
	private final Validator validator;
	private SearchParameter searchTag;
	
	public CodeController(Result result, CodeRepository repository, Validator validator, SearchParameter searchTag) {
		this.result = result;
		this.repository = repository;
		this.validator = validator;
		this.searchTag = searchTag;
	}
	
	
	@Get
	@Path("/codes")
	public List<Code> index(Code code, boolean clean) {
		if(searchTag == null || searchTag.getSearchTag()==null || searchTag.getSearchTag().equals(""))
			result.include("searchResult", "MOSTRANDO TODOS OS TECHOS CADASTRADOS");
		else
			result.include("searchResult", "MOSTRANDO OS RESULTADOS DA BUSCA POR \'"+searchTag.getSearchTag()+"\'");
		
		if(code == null)
		{
			return repository.findByTags(this.searchTag.getSearchTag());
		}
		else
		{
			result.include("code", code);
			if(clean)
				code.clean();
			return repository.findByTags(this.searchTag.getSearchTag());
		}
	}
	
	@Post
	@Path("/codes/search")
	public void searchParameter(String search) {
		this.searchTag.setSearchTag(search);
		this.result.use(Results.logic()).forwardTo(CodeController.class).index(null,false);
	}
	
	@Post
	@Path("/codes")
	public void create(Code code) {
		validator.validate(code);
		System.out.println("LANG: " + code.getLang());
		System.out.println("TEXTO: " + code.getSnippet());
		validator.onErrorUsePageOf(this).newCode();
		repository.create(code);
		
		
		result.redirectTo(this).index(code, true);
	}
	
	@Get
	@Path("/codes/new")
	public Code newCode() {
		return new Code();
	}
	
	@Put
	@Path("/codes")
	public void update(Code code) {
		validator.validate(code);
		validator.onErrorUsePageOf(this).edit(code);
		repository.update(code);
		result.forwardTo(this).index(code,true);
	}
	
	@Get
	@Path("/codes/{code.id}/edit")
	public void edit(Code code) {
		this.result.use(Results.logic()).forwardTo(CodeController.class).index(repository.find(code.getId()),false);
	}

	@Get
	@Path("/codes/{code.id}")
	public Code show(Code code) {
		return repository.find(code.getId());
	}

	@Delete
	@Path("/codes/{code.id}")
	public void destroy(Code code) {
		repository.destroy(repository.find(code.getId()));
		result.redirectTo(this).index(null,false);  
	}
	
}
