package gerenciamentodefrota.infra.vraptor;

import gerenciamentodefrota.infra.annotation.Modulo;
import br.com.caelum.vraptor.http.FormatResolver;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.DefaultPathResolver;

@Component
public class ModuloPathResolver extends DefaultPathResolver {

	private String nomeModulo = "";
	
	public ModuloPathResolver(FormatResolver resolver) {
		super(resolver);
	}

	@Override
	public String pathFor(ResourceMethod method) {
		try {
			Modulo modulo = method.getResource().getType().getAnnotation(Modulo.class);
			this.nomeModulo = modulo.nome().toLowerCase();
		} catch (Exception e) {
			
		}
		
		return super.pathFor(method).replaceAll("jsp/", "jsp/" + nomeModulo + "/");
	}
	
}
