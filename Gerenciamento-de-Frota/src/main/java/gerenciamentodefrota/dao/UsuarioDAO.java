package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	public Usuario autentica(String login, String senha) {
		StringBuilder builder = new StringBuilder();
		builder.append("select o from Usuario o where login = :login and senha = :senha");
		
		Query query = dao.getEm().createQuery(builder.toString());
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		List<Usuario> listReturn = query.getResultList();

		return ( listReturn.size() > 0 ) ? listReturn.get(0) : null;
	}
	
}
