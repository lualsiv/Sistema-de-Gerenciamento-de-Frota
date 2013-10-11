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
		result.include("connectCount", statistics.getConnectCount());
		result.include("entityNames", statistics.getEntityNames());
		result.include("queryExecutionMaxTime", statistics.getQueryExecutionMaxTime());
		result.include("secondLevelCacheHitCount", statistics.getSecondLevelCacheHitCount());
		
		result.include("consultamaislonga", statistics.getQueryExecutionMaxTimeQueryString());
		result.include("queries", statistics.getQueries());
	}
	
}
