package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.infra.MD5;
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
		MD5 md5 = new MD5(usuario.getSenha());
		usuario.setSenha(md5.getPassword());
		
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
		MD5 md5 = new MD5(senha);
		senha = md5.getPassword();
		
		StringBuilder builder = new StringBuilder();
		builder.append("select o from Usuario o where login = :login and senha = :senha");
		
		Query query = dao.getEm().createQuery(builder.toString());
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		List<Usuario> listReturn = query.getResultList();

		return ( listReturn.size() > 0 ) ? listReturn.get(0) : null;
	}
	
}
