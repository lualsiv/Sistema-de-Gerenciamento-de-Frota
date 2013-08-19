package gerenciamentodefrota.dao;

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

	public T busca(I id) {
		return em.getReference(classe, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> lista() {
		Query query = em.createQuery("select e from " + classe.getName() + " e");
		List<T> lista = query.getResultList();
		return lista;
	}

	public EntityManager getEm(){
		return em; 
	}
	
	public void remove(T t) {
		this.em.remove(t);
	}

	public void adiciona(T t) {
		this.em.persist(t);
	}

	public void alterar(T t) {
		this.em.merge(t);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAllByField(String campo, Object valor) {
		StringBuilder builder = new StringBuilder("select o from " + classe.getName() + " o ");
		builder.append("where ").append(campo).append(" = :valor");
		
		Query query = em.createQuery(builder.toString());
		query.setParameter("valor", valor);
		
		List<T> listReturn = query.getResultList();
		return listReturn;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAllByFieldUsingLike(String campo, Object valor) {
		StringBuilder builder = new StringBuilder("select o from " + classe.getName() + " o ");
		builder.append("where ").append(campo).append(" like :valor");
		
		Query query = em.createQuery(builder.toString());
		query.setParameter("valor", "%" + valor + "%");
		
		List<T> listReturn = query.getResultList();
		return listReturn;
	}

}
