package gerenciamentodefrota.dao;

import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.util.StringUtil;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAO<T, I extends Serializable> {

	private final EntityManager em;
	private final Class<T> classe;
	
	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}
	
	public T find(I id) {
		return em.getReference(classe, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list() {
		Query query = em.createQuery("select e from " + classe.getName() + " e");
		return (List<T>) query.getResultList();
	}
	
	public EntityManager getEntityManager(){
		return em; 
	}
	
	public void delete(T t) {
		this.em.remove(t);
	}
	
	public void create(T t) {
		this.em.persist(t);
	}
	
	public void update(T t) {
		this.em.merge(t);
	}
	
	@SuppressWarnings("unchecked")
	public T findByField(String campo, String valor) {
		StringBuilder builder = new StringBuilder("select o from " + classe.getName() + " o ");
		builder.append("where ").append(campo).append(" = :valor");
		
		Query query = em.createQuery(builder.toString())
						.setParameter("valor", valor);

		return (T) query.getSingleResult();			
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAllByField(String campo, Object valor) {
		StringBuilder builder = new StringBuilder("select o from " + classe.getName() + " o ");
		builder.append("where ").append(campo).append(" = :valor");
		
		Query query = em.createQuery(builder.toString())
						.setParameter("valor", valor);
		
		return (List<T>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAllByFieldUsingLike(String campo, Object valor) {
		StringBuilder builder = new StringBuilder("select o from " + classe.getName() + " o ");
		builder.append("where ").append(campo).append(" like :valor");
		
		Query query = em.createQuery(builder.toString())
						.setParameter("valor", "%" + valor + "%");
		
		List<T> listReturn = query.getResultList();
		return listReturn;
	}
	
	public Pagination<T> listPagination(Integer paginaAtual, Integer registrosPorPagina) {
		return listPagination(null, paginaAtual, registrosPorPagina);
	}
	
	public Pagination<T> listPagination(String ordem, Integer paginaAtual, Integer registrosPorPagina) {
		paginaAtual = (paginaAtual == null) ? 1 : paginaAtual;
		
		String hql = "select e from " + classe.getName() + " e ";
		
		Query queryCount = em.createQuery(hql.replaceAll("select e ", "select count(*) "));

		if (StringUtil.notNullOrEmpty(ordem)) {
			hql += " order by e." + ordem;
		}

		Query query = em.createQuery(hql)
				.setFirstResult(((paginaAtual - 1) * registrosPorPagina))
				.setMaxResults(registrosPorPagina);
		
		return listPagination(query, queryCount, paginaAtual, registrosPorPagina);
	}
	
	@SuppressWarnings("unchecked")
	public Pagination<T> listPagination(Query query, Query queryCount, Integer paginaAtual, Integer registrosPorPagina) {
		Long totalRegistros = (Long) queryCount.getSingleResult();
		Integer totalPaginas = (int) Math.ceil(totalRegistros / (double)registrosPorPagina);
		
		return new Pagination<T>(query.getResultList(), registrosPorPagina, paginaAtual, totalPaginas, totalRegistros.intValue());
	}
	
}
