package gerenciamentodefrota.infra.factory;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class StatisticsCreator implements ComponentFactory<Statistics> {
	
	private EntityManager manager;
	
	public StatisticsCreator(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public Statistics getInstance() {
		return ((Session)manager.getDelegate()).getSessionFactory().getStatistics();
	}
	
}
