package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.model.Funcionario;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class FuncionarioDAO {

	private DAO<Funcionario, Long> dao;

	public FuncionarioDAO(EntityManager em){
		this.dao = new DAO<Funcionario, Long>(em, Funcionario.class);
	}
	
	public void adiciona(Funcionario funcionario) {
		dao.adiciona(funcionario);
	}

	public Funcionario busca(Long id) {
		return dao.busca(id);
	}
	
	public List<Funcionario> lista() {
		return dao.lista();
	}
	
	public Funcionario buscaPorCadastro(String cadastro) {
		if (cadastro == null)
			return null;

		List<Funcionario> funcionarios;
		funcionarios = dao.listAllByField("cadastro", cadastro.toUpperCase());

		if (funcionarios.size() > 0)
			return funcionarios.get(0);
		else
			return null;
	}
	
}
