package gerenciamentodefrota.controller;

import gerenciamentodefrota.model.Veiculo;
import gerenciamentodefrota.service.VeiculoService;
import br.com.caelum.restfulie.Response;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class RestController {
	
	private Result result;
	private VeiculoService veiculoService;
	
	public RestController(Result result, VeiculoService veiculoService) {
		this.result = result;
		this.veiculoService = veiculoService;
	}
	
	@Consumes
	@Post("/veiculo/post")
	public void xml(Veiculo veiculo) {
		System.out.println(veiculo.getPlaca());
		result.nothing();
	}
	
	@Get("/combustivel/post")
	public void envia() {
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("asd-3243");
		
		Response salva = veiculoService.salva(veiculo);
		System.out.println(salva.getCode());
		result.nothing();
	}
	
}
