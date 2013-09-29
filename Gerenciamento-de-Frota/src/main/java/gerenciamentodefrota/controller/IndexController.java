package gerenciamentodefrota.controller;

import gerenciamentodefrota.infra.Cookies;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {
	
	private Cookies cookie;

	public IndexController(Cookies cookie) {
		this.cookie = cookie;
	}
	
	@Get("/")
	public void index() {
		
		cookie.set("name", "cotrim");
		System.out.println(cookie.get("name"));
		
		cookie.delete("name");
		System.out.println(cookie.get("name"));
		
		cookie.set("name", "vagner");
		System.out.println(cookie.get("name"));
		
		cookie.set("name", "oliveira");
		System.out.println(cookie.get("name"));
		
	}

	@Get("/erro")
	public void erro() {
		
	}
	
}
