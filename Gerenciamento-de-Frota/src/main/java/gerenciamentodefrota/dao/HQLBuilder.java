package gerenciamentodefrota.dao;

import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class HQLBuilder<T> {
	
	private String hql = "";
	private Map<String, Object> campos = new HashMap<String, Object>();
	private String ordem = "";
	private Class<T> classe;
	private String alias;
	private EntityManager em;
	
	public HQLBuilder(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
		this.alias = "a";
	}
	
	private String getHQL() {
		clearHQL();
		return "select " + alias + " " + hql + ordem;
	}
	
	private String getHQLCount() {
		clearHQL();
		return "select count(*) " + hql;
	}

	private void clearHQL() {
		hql = hql.replaceAll(" 1=1 and", "");
		hql = hql.replaceAll(" 1=1 or", "");
		hql = hql.replaceAll(" where  ", "where ");
	}
	
	public HQLBuilder<T> from() {
		this.hql = "from " + classe.getName() + " " + alias + " where 1=1";
		return this;
	}

	public HQLBuilder<T> andBetween(String campo, Object valor1, Object valor2) {
		this.hql += " and " + alias + "." + campo + " between :" + campo + "1" + " and :" + campo + "2 ";
		campos.put(campo + "1", valor1);
		campos.put(campo + "2", valor2);
		return this;
	}

	public HQLBuilder<T> andBetweenIf(String campo, Object valor1, Object valor2) {
		if (valor1 != null && valor2 != null)
			return andBetween(campo, valor1, valor2);
		return this;
	}

	public HQLBuilder<T> andNotBetween(String campo, Object valor1, Object valor2) {
		this.hql += " and " + alias + "." + campo + " not between :" + campo + "1" + " and :" + campo + "2 ";
		campos.put(campo + "1", valor1);
		campos.put(campo + "2", valor2);
		return this;
	}

	public HQLBuilder<T> andNotBetweenIf(String campo, Object valor1, Object valor2) {
		if (valor1 != null && valor2 != null)
			return andNotBetween(campo, valor1, valor2);
		return this;
	}
	
	public HQLBuilder<T> andIsTrue(String campo) {
		this.hql += " and " + alias + "." + campo + " = true";
		return this;
	}
	
	public HQLBuilder<T> andIsFalse(String campo) {
		this.hql += " and " + alias + "." + campo + " = false";
		return this;
	}
	
	public HQLBuilder<T> andIsNull(String campo) {
		this.hql += " and " + alias + "." + campo + " is null";
		return this;
	}
	
	public HQLBuilder<T> andNotIsNull(String campo) {
		this.hql += " and " + alias + "." + campo + " not is null";
		return this;
	}

	public HQLBuilder<T> andEquals(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " = :" + campo;
		campos.put(campo, valor);
		return this;
	}

	public HQLBuilder<T> andEqualsIf(String campo, Object valor) {
		if (valor != null)
			return andEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andNotEquals(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " <> :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andNotEqualsIf(String campo, Object valor) {
		if (valor != null)
			return andNotEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andLessOrEquals(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " <= :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andLessOrEqualsIf(String campo, Object valor) {
		if (valor != null)
			return andLessOrEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andGreaterThan(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " > :" + campo;
		campos.put(campo, valor);
		return this;
	}	
	
	public HQLBuilder<T> andGreaterThanIf(String campo, Object valor) {
		if (valor != null)
			andGreaterThan(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andLessThan(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " < :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andLessThanIf(String campo, Object valor) {
		if (valor != null)
			return andLessThan(campo, valor);
		return this;
	}

	public HQLBuilder<T> andGreaterOrEquals(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " >= :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andGreaterOrEqualsIf(String campo, Object valor) {
		if (valor != null)
			return andGreaterOrEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> andStringLikeStartWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringLike(campo, valor + "%");
		return this;
	}
	
	public HQLBuilder<T> andStringLikeEndsWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringLike(campo, "%" + valor);
		return this;
	}
	
	public HQLBuilder<T> andStringLikeContainsIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringLike(campo, "%" + valor + "%");
		return this;
	}
	
	public HQLBuilder<T> andStringNotLikeStartWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringNotLike(campo, valor + "%");
		return this;
	}
	
	public HQLBuilder<T> andStringNotLikeEndsWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringNotLike(campo, "%" + valor);
		return this;
	}
	
	public HQLBuilder<T> andStringNotLikeContainsIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringNotLike(campo, "%" + valor + "%");
		return this;
	}
	
	public HQLBuilder<T> andStringLike(String campo, String valor) {
		this.hql += " and " + alias + "." + campo + " like :" + campo;
		campos.put(campo, valor);
		return this;
	}

	public HQLBuilder<T> andStringNotLike(String campo, String valor) {
		this.hql += " and " + alias + "." + campo + " not like :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> orEquals(String campo, Object valor) {
		this.hql += " or " + alias + "." + campo + " = :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> orEqualsIf(String campo, Object valor) {
		if (valor != null)
			return orEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> orNotEquals(String campo, Object valor) {
		this.hql += " or " + alias + "." + campo + " <> :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T> orNotEqualsIf(String campo, Object valor) {
		if (valor != null)
			return orNotEquals(campo, valor);
		return this;
	}

	public HQLBuilder<T> andStringIn(String campo, List<String> valores) {
		if (valores != null) {
			if (valores.size() > 0) {
				this.hql += " and " + alias + "." + campo + " in " + this.listStringToString(valores);
			}
		}
		return this;
	}
	
	public HQLBuilder<T> andStringNotIn(String campo, List<String> valores) {
		if (valores != null) {
			if (valores.size() > 0) {
				this.hql += " and " + alias + "." + campo + " not in " + this.listStringToString(valores);
			}
		}
		return this;
	}
	
	private String listStringToString(List<String> valores) {
		String retorno = "";
		
		retorno += "(";
		for (String string : valores) {
			retorno += "'" + string + "',"; 
		}
		retorno += ")";

		return retorno.replace(",)", ")");
	}

	public HQLBuilder<T> orderBy(String ordem) {
		if (StringUtil.notNullOrEmpty(ordem))
			this.ordem = " order by " + alias + "." + ordem;
		return this;
	}
	
	private Query getQuery() {
		return em.createQuery(getHQL());
	}

	private Query getQuery(Integer paginaAtual, Integer registrosPorPagina) {
		return em.createQuery(getHQL())
				 .setFirstResult(((paginaAtual - 1) * registrosPorPagina))
				 .setMaxResults(registrosPorPagina);
	}
	
	private Query getQueryCount() {
		return em.createQuery(getHQLCount());
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list() {
		return (List<T>) getQuery().getResultList();
	}
	
	public Pagination<T> listPagination(Integer paginaAtual) {
		return listPagination(paginaAtual, Pagination.PAGESIZE);
	}
	
	public Pagination<T> listPagination(Integer paginaAtual, Integer registrosPorPagina) {
		paginaAtual = (paginaAtual == null) ? 1 : paginaAtual;
		
		Query query = this.getQuery(paginaAtual, registrosPorPagina);
		Query queryCount = this.getQueryCount();
		
		for(Entry<String, Object> e : campos.entrySet()) {
			query.setParameter(e.getKey(), e.getValue());
			queryCount.setParameter(e.getKey(), e.getValue());
	    }
		
		return listPagination(query, queryCount, paginaAtual, registrosPorPagina);
	}
	
	@SuppressWarnings("unchecked")
	public Pagination<T> listPagination(Query query, Query queryCount, Integer paginaAtual, Integer registrosPorPagina) {
		Long totalRegistros = (Long) queryCount.getSingleResult();
		Integer totalPaginas = (int) Math.ceil(totalRegistros / (double)registrosPorPagina);
		
		return new Pagination<T>(query.getResultList(), registrosPorPagina, paginaAtual, totalPaginas, totalRegistros.intValue());
	}

}
