package app.controllers;

import java.util.List;

import app.models.Code;
import app.repositories.CodeRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class CodeController {

	private final Result result;
	private final CodeRepository repository;
	private final Validator validator;
	
	public CodeController(Result result, CodeRepository repository, Validator validator) {
		this.result = result;
		this.repository = repository;
		this.validator = validator;
	}
	
	@Get
	@Path("/codes")
	public List<Code> index() {
		return repository.findAll();
	}
	
	@Post
	@Path("/codes")
	public void create(Code code) {
		validator.validate(code);
		validator.onErrorUsePageOf(this).newCode();
		repository.create(code);
		result.redirectTo(this).index();
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
		result.redirectTo(this).index();
	}
	
	@Get
	@Path("/codes/{code.id}/edit")
	public Code edit(Code code) {
		return repository.find(code.getId());
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
		result.redirectTo(this).index();  
	}
	
}