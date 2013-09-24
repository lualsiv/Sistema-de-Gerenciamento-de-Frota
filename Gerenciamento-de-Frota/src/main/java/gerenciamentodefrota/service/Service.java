package gerenciamentodefrota.service;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.RestClient;
import br.com.caelum.restfulie.Restfulie;
import br.com.caelum.restfulie.mediatype.XmlMediaType;

public class Service<T> {
	
	private RestClient restClient;
	private String type;
	
	public Service(Class<T> classe, String type) {
		this.type = type;
		this.restClient = Restfulie.custom();
		registerMediaType(classe);
	}
	
	public void registerMediaType(Class<T> classe) {
		this.restClient.getMediaTypes().register(new XmlMediaType().withTypes(classe));
	}
	
	public Response delete(String action) {
		return restClient.at(action)
				 		 .accept(type)
				 		 .delete();
	}
	
	public Response get(String action) {
		return restClient.at(action)
						 .accept(type)
						 .get();
	}
	 
	public Response post(String action, T t) {
		return restClient.at(action)
						 .accept(type)
						 .as(type)
						 .post(t);
	}
	
	public Response put(String action, T t) {
		return restClient.at(action)
						 .accept(type)
						 .as(type)
						 .put(t);
	}
	
}
