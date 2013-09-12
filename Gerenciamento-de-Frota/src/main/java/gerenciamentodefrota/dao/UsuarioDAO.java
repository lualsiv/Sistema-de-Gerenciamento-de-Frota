package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.infra.MD5;
import gerenciamentodefrota.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioDAO {

	private DAO<Usuario, Long> dao;

	public UsuarioDAO(EntityManager em) {
		this.dao = new DAO<Usuario, Long>(em, Usuario.class);
	}

	public void adiciona(Usuario usuario) {
		MD5 md5 = new MD5();
		usuario.setSenha(md5.hash(usuario.getSenha()));

		dao.create(usuario);
	}

	public Usuario busca(Long id) {
		return dao.find(id);
	}

	public void bloquear(Long id) {
		Usuario u = dao.find(id);
		u.setSituacao(false);
		dao.update(u);
	}

	public void desbloquear(Long id) {
		Usuario u = dao.find(id);
		u.setSituacao(true);
		dao.update(u);
	}

	public List<Usuario> lista() {
		return dao.list();
	}

	public Usuario autentica(String login, String senha) {
		senha = new MD5().hash(senha);

		String builder = "select o from Usuario o where login = :login and senha = :senha";

		Query query = dao.getEntityManager().createQuery(builder)
								 .setParameter("login", login)
								 .setParameter("senha", senha);

		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Usuario buscaPorCadastro(String cadastro) {
		try {
			return dao.findByField("funcionario.cadastro", cadastro);
		}
		catch (Exception e) {
			return null;
		}
	}

	public Usuario buscaPorLogin(String login) {
		try {
			return dao.findByField("login", login);
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
