package gerenciamentodefrota.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class DAO<T, I extends Serializable> {

	private final EntityManager em;
	private final Class<T> classe;
	
	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}
	
	public T find(I id) {
		return em.find(classe, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list() {
		Query query = em.createQuery("select e from " + classe.getName() + " e");
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<T>();
		}

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
	
}
