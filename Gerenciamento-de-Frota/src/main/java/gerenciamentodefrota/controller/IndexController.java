package gerenciamentodefrota.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private Result result;

	public IndexController(Result result) {
		this.result = result;
	}
	
	@Get("/")
	public void index() {
		result.include("teste", " OK ");
	}
	
	@Get("/erro")
	public void erro() {
		
	}
	
}
