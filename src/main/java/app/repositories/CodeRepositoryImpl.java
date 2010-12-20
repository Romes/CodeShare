package app.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import app.models.Code;

@Component
public class CodeRepositoryImpl 
    extends Repository<Code, Long>
    implements CodeRepository {

	public CodeRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Code> findByTags(String tag) {
		if(tag==null)
			tag = "";
		return entityManager.createQuery("From app.models.Code WHERE tags LIKE \'%" + tag +"%\'" ).getResultList();

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Code> findByName(String name){
		return entityManager.createQuery("From app.models.Code WHERE name LIKE \'%" + name +"%\'" ).getResultList();
	}
}
