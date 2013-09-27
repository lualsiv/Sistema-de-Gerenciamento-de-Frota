package gerenciamentodefrota.infra.service;

import br.com.caelum.restfulie.Response;
import br.com.caelum.restfulie.RestClient;
import br.com.caelum.restfulie.Restfulie;
import br.com.caelum.restfulie.mediatype.XmlMediaType;

public class ServiceXML<T> {
	
	private RestClient restClient;
	private String type ="application/xml";
	
	@SuppressWarnings("rawtypes")
	public ServiceXML(Class... classe) {
		this.restClient = Restfulie.custom();
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
