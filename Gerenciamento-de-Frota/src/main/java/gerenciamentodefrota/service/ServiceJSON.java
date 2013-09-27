package gerenciamentodefrota.service;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.RestClient;
import br.com.caelum.restfulie.Restfulie;
import br.com.caelum.restfulie.mediatype.JsonMediaType;

public class ServiceJSON<T> {
	
	private RestClient restClient;
	private String type ="application/json";
	
	public ServiceJSON() {
		this.restClient = Restfulie.custom();
		this.restClient.getMediaTypes().register(new JsonMediaType());
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
