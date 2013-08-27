package gerenciamentodefrota.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import gerenciamentodefrota.annotation.Logged;
import gerenciamentodefrota.dao.UsuarioDAO;
import gerenciamentodefrota.infra.Notice;
import gerenciamentodefrota.model.Usuario;
import gerenciamentodefrota.model.UsuarioSession;

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

		if (usuario != null) {
			usuarioSession.login(usuario);
			result.redirectTo(IndexController.class).index();
		} else {
			notice.addWarning("Login ou senha inválidos.");
			result.redirectTo(this).login();
		}
	}

	@Logged
	@Get("/logout")
	public void logout() {
		usuarioSession.logoff();
		result.redirectTo(LoginController.class).login();
	}

}