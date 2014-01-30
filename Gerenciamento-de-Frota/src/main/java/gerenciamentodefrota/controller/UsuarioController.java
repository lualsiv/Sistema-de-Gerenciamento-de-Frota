package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.dao.FuncionarioDAO;
import gerenciamentodefrota.dao.UsuarioDAO;
import gerenciamentodefrota.infra.persistence.Transacional;
import gerenciamentodefrota.infra.security.Logged;
import gerenciamentodefrota.infra.security.Permission;
import gerenciamentodefrota.infra.view.Notice;
import gerenciamentodefrota.model.Usuario;
import gerenciamentodefrota.model.enums.Perfil;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	private Validator validator;
	private FuncionarioDAO funcionarioDAO;
	private Result result;
	private Notice notice;

	public UsuarioController(UsuarioDAO usuarioDAO, FuncionarioDAO funcionarioDAO, Validator validator, Result result, Notice notice) {
		this.usuarioDAO = usuarioDAO;
		this.funcionarioDAO = funcionarioDAO;
		this.validator = validator;
		this.result = result;
		this.notice = notice;
	}

	@Permission(Perfil.ADMINISTRADOR)
	@Get("/usuario/novo")
	public void novo() {
	}
	
	@Permission(Perfil.ADMINISTRADOR)
	@Transacional
	@Post("/usuario/novo")
	public void salva(Usuario usuario) {
		validaNovoUsuario(usuario);
		usuarioDAO.adiciona(usuario);
		result.redirectTo(this).lista();
	}
	
	private void validaNovoUsuario(Usuario usuario) {
		Usuario usuarioJaCadastrado = usuarioDAO.buscaPorCadastro(usuario.getFuncionario().getCadastro());
		if (usuarioJaCadastrado != null)
			validator.add(new ValidationMessage("Já existe um usuário do sistema cadastrado para este funcionario.", "funcionario"));
		
		usuarioJaCadastrado = usuarioDAO.buscaPorLogin(usuario.getLogin());
		if (usuarioJaCadastrado != null)
			validator.add(new ValidationMessage("Já existe um usuário com este login.", "login"));
		
		usuario.setFuncionario(funcionarioDAO.buscaPorCadastro(usuario.getFuncionario().getCadastro()));
		if(usuario.getFuncionario() == null)
			validator.add(new ValidationMessage("Funcionario não encontrado.", "funcionario"));
		
		validator.validate(usuario);
		validator.onErrorUsePageOf(this).novo();
	}

	@Logged
	@Get("/usuario")
	public List<Usuario> lista() {
		try {
			return usuarioDAO.lista();
		} catch (Exception e) {
			return new ArrayList<Usuario>();
		}
	}
	
	@Permission(Perfil.ADMINISTRADOR)
	@Transacional
	@Get("/usuario/{id}/bloquear")
	public void bloquear(Long id) {
		try {
			usuarioDAO.bloquear(id);
			notice.success("Usuário bloqueado com sucesso.");
		} catch (Exception e) {
			notice.warning("Usuário não encontrado");
		}
		result.redirectTo(this).lista();
	}
	
	@Permission(Perfil.ADMINISTRADOR)
	@Transacional
	@Get("/usuario/{id}/desbloquear")
	public void desbloquear(Long id) {
		try {
			usuarioDAO.desbloquear(id);
			notice.success("Usuário desbloqueado com sucesso.");
		} catch (Exception e) {
			notice.warning("Usuário não encontrado");
		}
		
		result.redirectTo(this).lista();
	}
	
}
