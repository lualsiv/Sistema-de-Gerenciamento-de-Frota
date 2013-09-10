package gerenciamentodefrota.interceptor;

import java.util.Arrays;
import java.util.Collection;

import gerenciamentodefrota.annotation.Permission;
import gerenciamentodefrota.controller.LoginController;
import gerenciamentodefrota.infra.Notice;
import gerenciamentodefrota.model.Perfil;
import gerenciamentodefrota.model.UsuarioSession;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.view.Results;

@Intercepts
public class PermissionInterceptor implements Interceptor {

	private UsuarioSession usuarioSession;
	private Result result;
	private Notice notice;

	public PermissionInterceptor(UsuarioSession usuarioSession, Result result, Notice notice) {
		this.usuarioSession = usuarioSession;
		this.result = result;
		this.notice = notice;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return method.containsAnnotation(Permission.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object controller) throws InterceptionException {
		Permission methodPermission = method.getMethod().getAnnotation(Permission.class);
		Permission controllerPermission = method.getResource().getType().getAnnotation(Permission.class);

		if (usuarioSession.isLogado()) {
			if (this.hasAccess(methodPermission) && this.hasAccess(controllerPermission)) {
				stack.next(method, controller);
			} else {
				result.use(Results.http()).sendError(403, "Você não tem permissão para esta ação!");
			}
		}
		else {
			notice.addInfo("Você deve logar no sistema para executar esta operação.");
			result.redirectTo(LoginController.class).login();
		}
	}

	private Boolean hasAccess(Permission permission) {
		if (permission == null)
			return false;
		
		Collection<Perfil> perfilList = Arrays.asList(permission.value());
		return perfilList.contains(usuarioSession.getUsuario().getPerfil());
	}

}
