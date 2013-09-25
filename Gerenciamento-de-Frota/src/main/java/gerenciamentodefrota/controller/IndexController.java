package gerenciamentodefrota.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {
	
	@Get("/")
	public void index() {
		
	}

	@Get("/erro")
	public void erro() {
		
	}
	
}
