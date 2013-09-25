package gerenciamentodefrota.infra;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class WebConfig {
	
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("web");
	
	public WebConfig() {
	}
	
	public String get(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

}
