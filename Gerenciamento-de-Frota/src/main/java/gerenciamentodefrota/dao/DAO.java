package gerenciamentodefrota.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAO<T> {

	private final EntityManager em;
	private final Class<T> classe;

	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}

	public T busca(Long id) {
		return em.getReference(classe, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> lista() {
		return em.createQuery("select e from " + classe.getName() + " e")
				.getResultList();
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
	public List<T> listAllByFieldUsingLike(String campo, Object valor) {
		StringBuilder builder = new StringBuilder("select o from " + classe.getName() + " o ");
		builder.append("where ").append(campo).append(" like :valor");
		Query query = em.createQuery(builder.toString());
		query.setParameter("valor", "%" + valor + "%");
		List<T> listReturn = query.getResultList();
		return listReturn;
	}
	
}
