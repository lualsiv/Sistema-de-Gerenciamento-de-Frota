package gerenciamentodefrota.test.dao;

import gerenciamentodefrota.infra.Notice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public abstract class DAOTest {

	public EntityManagerFactory factory;
	public EntityManager manager;
	public MockResult result;
	public MockValidator validator;
	public Notice notice;
	
	@Before
	public void setup() {
		factory = Persistence.createEntityManagerFactory("default");
		manager = factory.createEntityManager();
		result = new MockResult();
		validator = new MockValidator();
		notice = new Notice();
	}

	@After
	public void finalize() {
		manager.close();
		factory.close();

		// gc
		manager = null;
		factory = null;
	}

}
