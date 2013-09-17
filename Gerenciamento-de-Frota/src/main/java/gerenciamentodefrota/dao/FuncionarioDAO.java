package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.model.Funcionario;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class FuncionarioDAO {

	private DAO<Funcionario, Long> dao;
	private HQLBuilder<Funcionario> hql;
	
	public FuncionarioDAO(EntityManager entityManager){
		this.dao = new DAO<Funcionario, Long>(entityManager, Funcionario.class);
		this.hql = new HQLBuilder<Funcionario>(entityManager, Funcionario.class);
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
		hql.from()
			.andStringLikeContainsIf("nome", nome)
			.orderBy(ordem);
		
		return hql.listPagination(paginaAtual, registrosPorPagina);
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
