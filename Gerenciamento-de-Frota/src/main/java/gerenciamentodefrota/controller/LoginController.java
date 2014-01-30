package gerenciamentodefrota.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import gerenciamentodefrota.dao.UsuarioDAO;
import gerenciamentodefrota.infra.UsuarioSession;
import gerenciamentodefrota.infra.security.Logged;
import gerenciamentodefrota.infra.view.Notice;
import gerenciamentodefrota.model.Usuario;
import gerenciamentodefrota.util.StringUtil;

@Resource
public class LoginController {
	
	private UsuarioSession usuarioSession;
	private Result result;
	private UsuarioDAO usuarioDAO;
	private Notice notice;

	public LoginController(UsuarioDAO usuarioDAO, UsuarioSession usuarioSession, Result result, Notice notice) {
		this.usuarioDAO = usuarioDAO;
		this.usuarioSession = usuarioSession;
		this.result = result;
		this.notice = notice;
	}

	@Get("/login")
	public void login() {
		if (usuarioSession.isLogado()) {
			result.redirectTo("/");
		}
	}

	@Post("/login")
	public void login(Usuario usuario) {
		usuario = usuarioDAO.autentica(usuario.getLogin(), usuario.getSenha());
		
		String uri = StringUtil.isNullOrEmpty(usuarioSession.getUrl()) ? "/" : usuarioSession.getUrl();
		
		if (usuario != null) {
			usuarioExiste(usuario, uri);
		} else {
			notice.warning("Login ou senha inválidos.");
			result.redirectTo(this).login();
		}
	}

	private void usuarioExiste(Usuario usuario, String uri) {
		if (usuario.getSituacao() == true) {
			usuarioSession.login(usuario);
			result.redirectTo(uri);
		} else {
			notice.warning("O usuário está desativado.");
			result.redirectTo("/");
		}
	}
	
	@Logged
	@Get("/logout")
	public void logout() {
		usuarioSession.setUrl(null);
		usuarioSession.logoff();
		result.redirectTo(LoginController.class).login();
	}

}
