package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.model.Usuario;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioDAO {

	private DAO<Usuario, Long> dao;

	public UsuarioDAO(EntityManager em){
		this.dao = new DAO<Usuario, Long>(em, Usuario.class);
	}
	
	public void adiciona(Usuario usuario) {
		dao.adiciona(usuario);
	}

	public Usuario busca(Long id) {
		return dao.busca(id);
	}
	
	public List<Usuario> lista() {
		return dao.lista();
	}

}
