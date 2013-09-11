package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.model.Funcionario;
import gerenciamentodefrota.util.StringUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public Pagination<Funcionario> lista(String nome, String ordem, Integer paginaAtual) {
		return lista(nome, ordem, paginaAtual, Pagination.PAGESIZE);
	}
	
	public Pagination<Funcionario> lista(String nome, String ordem, Integer paginaAtual, Integer registrosPorPagina) {
		paginaAtual = (paginaAtual == null) ? 1 : paginaAtual;
		
		String hql = "select e from " + Funcionario.class.getName() + " e where 1=1 ";
		
		if (StringUtil.notNullOrEmpty(nome)) {
			hql += " and e.nome like :nome ";
		}
		
		Query queryCount = dao.getEntityManager().createQuery(hql.replaceAll("select e ", "select count(*) "));
		
		if (StringUtil.notNullOrEmpty(ordem)) {
			hql += " order by e." + ordem;
		}
		
		Query query = dao.getEntityManager().createQuery(hql)
											.setFirstResult(((paginaAtual - 1) * registrosPorPagina))
											.setMaxResults(registrosPorPagina);
		
		if (StringUtil.notNullOrEmpty(nome)) {
			query.setParameter("nome", "%" + nome + "%");
			queryCount.setParameter("nome", "%" + nome + "%");
		}
		
		return dao.listaComPaginacao(query, queryCount, paginaAtual, registrosPorPagina);
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
