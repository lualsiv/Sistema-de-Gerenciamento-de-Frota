package gerenciamentodefrota.infra;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {
	
	@Override
	public long getSizeLimit() {
		// 10 MB
		return 10 * 1024 * 1024;
	}

}