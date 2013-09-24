package gerenciamentodefrota.test.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

import br.com.triadworks.dbunit.vendors.postgresql.PostgreSqlDbUnitManagerImpl;

public abstract class DAOTestHelper {

	protected PostgreSqlDbUnitManagerImpl dbunitmanager;
	public EntityManagerFactory factory;
	public EntityManager entitymanager;
	
	protected void commitOrRallBack() {
		try {
			entitymanager.getTransaction().commit();
		} catch (Exception e) {
			if (entitymanager.getTransaction().isActive()) {
				entitymanager.getTransaction().rollback();
			}
		}
	}
	
	@Before
	public void setup() {
		factory = Persistence.createEntityManagerFactory("test");
		entitymanager = factory.createEntityManager();
		dbunitmanager = new PostgreSqlDbUnitManagerImpl(new JdbcDataSource());
	}
	
	@After
	public void finalize() {
		entitymanager.close();
		factory.close();
		
		// gc
		entitymanager = null;
		factory = null;
		dbunitmanager = null;
	}

}
