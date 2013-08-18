package gerenciamentodefrota.controller;

import gerenciamentodefrota.infra.Notice;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private Result result;
	private Notice notice;

	public IndexController(Result result, Notice notice) {
		this.result = result;
		this.notice = notice;
	}
	
	@Get("/")
	public void index() {
		notice.addInfo("Página princiapl");
		
		result.include("texto", "VRaptor");
	}
	
}
