package gerenciamentodefrota.interceptor;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import gerenciamentodefrota.annotation.Permission;
import gerenciamentodefrota.controller.LoginController;
import gerenciamentodefrota.infra.UsuarioSession;
import gerenciamentodefrota.infra.view.Notice;
import gerenciamentodefrota.model.enums.Perfil;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class PermissionInterceptor implements Interceptor {

	private UsuarioSession usuarioSession;
	private Result result;
	private Notice notice;
	private HttpServletRequest request;

	public PermissionInterceptor(UsuarioSession usuarioSession, Result result, Notice notice, HttpServletRequest request) {
		this.usuarioSession = usuarioSession;
		this.result = result;
		this.notice = notice;
		this.request = request;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(Permission.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object controller) throws InterceptionException {
		if (usuarioSession.isLogado()) {
			isLogged(stack, method, controller);
		}
		else {
			notLogger();
		}
	}

	private void isLogged(InterceptorStack stack, ResourceMethod method, Object controller) {
		Permission methodPermission = method.getMethod().getAnnotation(Permission.class);
		
		if (this.hasAccess(methodPermission)) {
			stack.next(method, controller);
		} else {
			notice.warning("Seu usuário não tem permissão para usar este recurso do sistema.");
			result.redirectTo("/");
		}
	}

	private void notLogger() {
		String uri = request.getRequestURL().toString();
		usuarioSession.setUrl(uri);
		notice.info("Você deve logar no sistema para executar esta operação.");
		result.redirectTo(LoginController.class).login();
	}

	private Boolean hasAccess(Permission permission) {
		if (permission == null)
			return false;
		
		Collection<Perfil> perfilList = Arrays.asList(permission.value());
		return perfilList.contains(usuarioSession.getUsuario().getPerfil());
	}

}
