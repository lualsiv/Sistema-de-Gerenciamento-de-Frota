package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.infra.Pagination;
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
		dao.create(funcionario);
	}
	
	public Funcionario busca(Long id) {
		return dao.find(id);
	}
	
	public List<Funcionario> lista() {
		return dao.list();
	}
	
	public Pagination<Funcionario> lista(String nome, String ordem, Integer paginaAtual, Integer registrosPorPagina) {
		HQLBuilder<Funcionario, Long> hqlB = new HQLBuilder<Funcionario, Long>(dao, Funcionario.class);
		hqlB.from()
			.andStringLikeContainsIf("nome", nome)
			.orderBy(ordem);
		
		return hqlB.listPagination(paginaAtual, registrosPorPagina);
	}
	
	public Funcionario buscaPorCadastro(String cadastro) {
		try {
			return dao.findByField("cadastro", cadastro);
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
