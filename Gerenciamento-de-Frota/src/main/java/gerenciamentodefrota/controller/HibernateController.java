package gerenciamentodefrota.controller;

import org.hibernate.stat.Statistics;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class HibernateController {
	
	private Result result;
	private Statistics statistics;
	
	public HibernateController(Result result, Statistics statistics) {
		this.result = result;
		this.statistics = statistics;
	}
	
	@Get("/hibernate/statistics")
	public void consultamaislonga() {
		result.include("consultamaislonga", statistics.getQueryExecutionMaxTimeQueryString());
		result.include("queries", statistics.getQueries());
	}
	
}
