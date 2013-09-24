package gerenciamentodefrota.service;

import br.com.caelum.restfulie.Response;
import br.com.caelum.vraptor.ioc.Component;
import gerenciamentodefrota.infra.WebConfig;
import gerenciamentodefrota.model.Veiculo;

@Component
public class VeiculoService {
	
	private Service<Veiculo> service;
	private WebConfig webconfig;
	
	public VeiculoService(WebConfig webconfig) {
		this.webconfig = webconfig;
		this.service = new Service<Veiculo>(Veiculo.class, "application/xml");
	}
	
	public Response salva(Veiculo veiculo) {
		return service.post(webconfig.get("url_salva_veiculo"), veiculo);
	}
	
}
