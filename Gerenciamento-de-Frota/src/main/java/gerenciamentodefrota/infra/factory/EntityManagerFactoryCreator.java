package gerenciamentodefrota.infra.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class EntityManagerFactoryCreator implements ComponentFactory<EntityManagerFactory> {

	private final EntityManagerFactory entityManagerFactory;

	public EntityManagerFactoryCreator() {
		entityManagerFactory = Persistence.createEntityManagerFactory("default");
	}

	public EntityManagerFactory getInstance() {
		return entityManagerFactory;
	}

}
