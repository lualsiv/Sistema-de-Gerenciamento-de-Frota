package gerenciamentodefrota.infra;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class WebConfig {
	
	private Properties properties;
	
	public WebConfig() {
		getWebProperties();
	}
	
	public String get(String key) {
		return properties.getProperty(key);
	}

	private void getWebProperties() {
		try {
			properties = new Properties();
			properties.load(new FileReader(new File("src/main/resources/web.properties")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não achou o arquivo web.properties");
		}
	}
	
}
