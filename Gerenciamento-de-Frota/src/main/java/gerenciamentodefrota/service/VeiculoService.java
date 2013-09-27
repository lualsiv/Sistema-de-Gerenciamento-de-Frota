package gerenciamentodefrota.service;

import br.com.caelum.restfulie.Response;
import br.com.caelum.vraptor.ioc.Component;
import gerenciamentodefrota.infra.WebConfig;
import gerenciamentodefrota.model.Veiculo;

@Component
public class VeiculoService {
	
	private ServiceXML<Veiculo> service;
	private WebConfig web;
	
	public VeiculoService(WebConfig web) {
		this.web = web;
		this.service = new ServiceXML<Veiculo>(Veiculo.class);
	}
	
	public Response salva(Veiculo veiculo) {
		return service.post(web.get("url_salva_veiculo"), veiculo);
	}
	
}
