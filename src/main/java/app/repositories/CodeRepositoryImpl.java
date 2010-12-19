package app.repositories;

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
}
