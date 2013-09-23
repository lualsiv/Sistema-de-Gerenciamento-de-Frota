package gerenciamentodefrota.controller;

import java.math.BigDecimal;

import gerenciamentodefrota.model.Combustivel;
import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.RestClient;
import br.com.caelum.restfulie.mediatype.XmlMediaType;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

@Resource
public class RestController {

	private RestClient restClient;
	private Response response;
	
	@Consumes
	@Post("/combustivel/xml")
	public void xml(Combustivel combustivel) {
		System.out.println(combustivel.getId() + ", " + combustivel.getDescricao() + ", " + combustivel.getPreco());
	}
	
	@Get("/combustivel/envia")
	public void envia() {
		Combustivel combustivel = new Combustivel();
		combustivel.setId((long) 1);
		combustivel.setDescricao("ALCOOL");
		combustivel.setPreco(new BigDecimal("3.09"));
		
		restClient = br.com.caelum.restfulie.Restfulie.custom();
		restClient.getMediaTypes().register(new XmlMediaType().withTypes(Combustivel.class));
		
		response = restClient.at("http://localhost:8080/Gerenciamento-de-Frota/combustivel/xml")
				 						.accept("application/xml")
				 						.as("application/xml")
				 						.post(combustivel);
		
		System.out.println("Code: " + response.getCode());
	}
	
}
