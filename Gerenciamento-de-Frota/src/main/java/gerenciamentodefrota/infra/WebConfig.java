package gerenciamentodefrota.infra;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class WebConfig {

	private ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("web");

	public WebConfig() {

	}

	public String get(String key) {
		return RESOURCE_BUNDLE.getString(key).trim();
	}

	public int getAsInteger(String key) {
		return Integer.parseInt(get(key));
	}

	public long getAsLong(String key) {
		return Long.parseLong(get(key));
	}
	
	public boolean getAsBoolean(String key) {
		if (get(key).toLowerCase().equals("false"))
			return false;
		else if (get(key).toLowerCase().equals("true"))
			return true;
		else {
			throw new IllegalArgumentException("parametro inválido, deve ser 'true' ou 'false'.");
		}
	}
	
	public double getAsDouble(String key) {
		return Double.parseDouble(get(key).replaceAll(",", "."));
	}

	public List<String> getAsStringList(String key) {
		String[] keys = get(key).split(",");
		for (int i = 0; i < keys.length; i++)
			keys[i] = keys[i].trim();

		return Arrays.asList(keys);
	}

}
