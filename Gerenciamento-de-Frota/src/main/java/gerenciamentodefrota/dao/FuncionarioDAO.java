package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.model.Funcionario;

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
	
	public Pagination<Funcionario> lista(String nome, Integer paginaAtual, Integer registrosPorPagina) {
		paginaAtual = (paginaAtual == null) ? 1 : paginaAtual;
		
		String hql = "select e from " + Funcionario.class.getName() + " e where 1=1 ";
		
		if(nome != null) {
			if (!nome.isEmpty())
				hql += " and e.nome like :nome ";
		}
		
		Query query = dao.getEm().createQuery(hql);
		query.setFirstResult(((paginaAtual - 1) * registrosPorPagina));
		query.setMaxResults(registrosPorPagina);

		Query queryCount = dao.getEm().createQuery(hql.replaceAll("select e ", "select count(*) "));
		
		if(nome != null) {
			if (!nome.isEmpty())
			{
				query.setParameter("nome", "%" + nome + "%");
				queryCount.setParameter("nome", "%" + nome + "%");
			}
		}
		
		return dao.listaComPaginacao(query, queryCount, paginaAtual, registrosPorPagina);
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
