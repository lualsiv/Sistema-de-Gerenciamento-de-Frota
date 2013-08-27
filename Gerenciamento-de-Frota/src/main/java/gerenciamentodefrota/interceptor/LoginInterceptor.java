package gerenciamentodefrota.interceptor;

import gerenciamentodefrota.annotation.Logged;
import gerenciamentodefrota.controller.LoginController;
import gerenciamentodefrota.infra.Notice;
import gerenciamentodefrota.model.UsuarioSession;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class LoginInterceptor implements Interceptor {

	private UsuarioSession usuarioSession;
	private Result result;
	private Notice notice;

	public LoginInterceptor(UsuarioSession usuarioSession, Result result, Notice notice) {
		this.usuarioSession = usuarioSession;
		this.result = result;
		this.notice = notice;
	}
	
	@Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(Logged.class);
	}
	
	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object controller) throws InterceptionException {
		if(usuarioSession.isLogado()) {
			stack.next(method, controller);
		}
		else {
			notice.addInfo("Você deve logar no sistema para executar esta operação.");
			result.redirectTo(LoginController.class).login();
		}
	}
	
}