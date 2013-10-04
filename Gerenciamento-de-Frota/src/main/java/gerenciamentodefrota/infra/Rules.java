package gerenciamentodefrota.infra;

import java.util.HashMap;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class Rules {

	private HashMap<String, List<Object>> rules = new HashMap<String, List<Object>>();
	
	public void addRules(String name, List<Object> rules) {
		if (rules == null)
			throw new IllegalArgumentException();

		if (rules.size() == 0)
			throw new  IllegalArgumentException();
			
		this.rules.put(name, rules);
	}
	
	public boolean hasPermission(String name, Object value) {
		List<Object> values = rules.get(name);
		
		for (Object object : values) {
			if (value.equals(object))
				return true;
		}
		
		return false;
	}
	
}
