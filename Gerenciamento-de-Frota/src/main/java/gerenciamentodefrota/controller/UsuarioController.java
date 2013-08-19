package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.dao.UsuarioDAO;
import gerenciamentodefrota.model.Usuario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class UsuarioController {

	private UsuarioDAO usuarioDAO;

	public UsuarioController(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	@Get("/usuario")
	public List<Usuario> lista() {
		try {
			return usuarioDAO.lista();
		} catch (Exception e) {
			return new ArrayList<Usuario>();
		}
	}
	
}
