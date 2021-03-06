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
import br.com.caelum.vraptor.validator.ValidationMessage;
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
		CodeValidation(code, true);
		validator.onErrorForwardTo(CodeController.class).index(code,false);
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
		CodeValidation(code,false);
		validator.onErrorForwardTo(CodeController.class).index(code,false);
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
	
private void CodeValidation(Code code, boolean create){
		
		List<Code> codeList;
		
		validator.validate(code);
		code = WithoutBlankChar(code);
		
		if(code.getName().isEmpty()) 
	        validator.add(new ValidationMessage("Erro","O campo Nome nao pode ser deixado em branco"));
	    
		if(code.getTags().isEmpty()) 
	        validator.add(new ValidationMessage("Erro","O campo Tags não pode ser deixado em branco"));
	    
		if(code.getLang().isEmpty()) 
	        validator.add(new ValidationMessage("Erro","Escolha uma linguagem para o codigo a ser cadastrado"));
	    
		if(code.getSnippet().isEmpty()) 
	        validator.add(new ValidationMessage("Erro","O campo de texto do codigo não pode ser deixado em branco"));
	    if(create){
		codeList = repository.findByName(code.getName());
		if(!codeList.isEmpty())
			validator.add(new ValidationMessage("Erro","Nao pode haver dois codigos com o mesmo nome, insira um nome diferente"));
	    }
	    }
	
	private Code WithoutBlankChar(Code code){
		code.setName(code.getName().trim());
		code.setTags(code.getTags().trim());
		code.setLang(code.getLang().trim());
		code.setSnippet(code.getSnippet().trim());
		return code;
	}
	
}
