package app.repositories;

import java.util.List;

import app.models.Code;

public interface CodeRepository {
	/*
	 * Delete the methods you don't want to expose
	 */
	 
	void create(Code entity);
	
	void update(Code entity);
	
	void destroy(Code entity);
	
	Code find(Long id);
	
	List<Code> findAll();
	
	List<Code> findByTags(String tag);
	
	List<Code> findByName(String name);

}
