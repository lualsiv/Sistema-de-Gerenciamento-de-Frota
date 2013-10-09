package gerenciamentodefrota.infra.vraptor;

import gerenciamentodefrota.controller.IndexController;
import gerenciamentodefrota.infra.view.Notice;

import java.util.Set;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.RequestInfo;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.HttpMethod;
import br.com.caelum.vraptor.resource.MethodNotAllowedHandler;

@Component
@RequestScoped
public class Error405 implements MethodNotAllowedHandler {
	private Result result;
	private Notice notice;

	public Error405(Result result, Notice notice) {
		this.result = result;
		this.notice = notice;
	}

	@Override
	public void deny(RequestInfo requestInfo, Set<HttpMethod> httpMethods) {
		notice.warning("Método não permitido para este recurso.");
		result.redirectTo(IndexController.class).index();
	}
}