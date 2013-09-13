package gerenciamentodefrota.dao;

import gerenciamentodefrota.dao.DAO;
import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.util.StringUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

public class HQLBuilder<T,I extends Serializable> {
	
	private DAO<T, I> dao;
	
	private String hql = "";
	private Map<String, Object> campos = new HashMap<String, Object>();
	private String ordem = "";
	private Class<T> classe;
	private String alias = "a";
	
	public HQLBuilder(DAO<T, I> dao, Class<T> classe) {
		this.dao = dao;
		this.classe = classe;
	}
	
	public HQLBuilder(DAO<T, I> dao, Class<T> classe, String alias) {
		this.dao = dao;
		this.classe = classe;
		this.alias = alias;
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
	
	public HQLBuilder<T,I> from() {
		this.hql = "from " + classe.getName() + " " + alias + " where 1=1";
		return this;
	}

	public HQLBuilder<T,I> andBetween(String campo, Object valor1, Object valor2) {
		this.hql += " and " + alias + "." + campo + " between :" + campo + "1" + " and :" + campo + "2 ";
		campos.put(campo + "1", valor1);
		campos.put(campo + "2", valor2);
		return this;
	}

	public HQLBuilder<T,I> andBetweenIf(String campo, Object valor1, Object valor2) {
		if (valor1 != null && valor2 != null)
			return andBetween(campo, valor1, valor2);
		return this;
	}

	public HQLBuilder<T,I> andNotBetween(String campo, Object valor1, Object valor2) {
		this.hql += " and " + alias + "." + campo + " not between :" + campo + "1" + " and :" + campo + "2 ";
		campos.put(campo + "1", valor1);
		campos.put(campo + "2", valor2);
		return this;
	}

	public HQLBuilder<T,I> andNotBetweenIf(String campo, Object valor1, Object valor2) {
		if (valor1 != null && valor2 != null)
			return andNotBetween(campo, valor1, valor2);
		return this;
	}
	
	public HQLBuilder<T,I> andIsTrue(String campo) {
		this.hql += " and " + alias + "." + campo + " = true";
		return this;
	}
	
	public HQLBuilder<T,I> andIsFalse(String campo) {
		this.hql += " and " + alias + "." + campo + " = false";
		return this;
	}
	
	public HQLBuilder<T,I> andIsNull(String campo) {
		this.hql += " and " + alias + "." + campo + " is null";
		return this;
	}
	
	public HQLBuilder<T,I> andNotIsNull(String campo) {
		this.hql += " and " + alias + "." + campo + " not is null";
		return this;
	}

	public HQLBuilder<T,I> andEquals(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " = :" + campo;
		campos.put(campo, valor);
		return this;
	}

	public HQLBuilder<T,I> andEqualsIf(String campo, Object valor) {
		if (valor != null)
			return andEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andNotEquals(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " <> :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andNotEqualsIf(String campo, Object valor) {
		if (valor != null)
			return andNotEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andLessOrEquals(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " <= :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andLessOrEqualsIf(String campo, Object valor) {
		if (valor != null)
			return andLessOrEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andGreaterThan(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " > :" + campo;
		campos.put(campo, valor);
		return this;
	}	
	
	public HQLBuilder<T,I> andGreaterThanIf(String campo, Object valor) {
		if (valor != null)
			andGreaterThan(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andLessThan(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " < :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andLessThanIf(String campo, Object valor) {
		if (valor != null)
			return andLessThan(campo, valor);
		return this;
	}

	public HQLBuilder<T,I> andGreaterOrEquals(String campo, Object valor) {
		this.hql += " and " + alias + "." + campo + " >= :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andGreaterOrEqualsIf(String campo, Object valor) {
		if (valor != null)
			return andGreaterOrEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> andStringLikeStartWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringLike(campo, valor + "%");
		return this;
	}
	
	public HQLBuilder<T,I> andStringLikeEndsWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringLike(campo, "%" + valor);
		return this;
	}
	
	public HQLBuilder<T,I> andStringLikeContainsIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringLike(campo, "%" + valor + "%");
		return this;
	}
	
	public HQLBuilder<T,I> andStringNotLikeStartWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringNotLike(campo, valor + "%");
		return this;
	}
	
	public HQLBuilder<T,I> andStringNotLikeEndsWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringNotLike(campo, "%" + valor);
		return this;
	}
	
	public HQLBuilder<T,I> andStringNotLikeContainsIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return andStringNotLike(campo, "%" + valor + "%");
		return this;
	}
	
	public HQLBuilder<T,I> andStringLike(String campo, String valor) {
		this.hql += " and " + alias + "." + campo + " like :" + campo;
		campos.put(campo, valor);
		return this;
	}

	public HQLBuilder<T,I> andStringNotLike(String campo, String valor) {
		this.hql += " and " + alias + "." + campo + " not like :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> orEquals(String campo, Object valor) {
		this.hql += " or " + alias + "." + campo + " = :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> orEqualsIf(String campo, Object valor) {
		if (valor != null)
			return orEquals(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> orNotEquals(String campo, Object valor) {
		this.hql += " or " + alias + "." + campo + " <> :" + campo;
		campos.put(campo, valor);
		return this;
	}
	
	public HQLBuilder<T,I> orNotEqualsIf(String campo, Object valor) {
		if (valor != null)
			return orNotEquals(campo, valor);
		return this;
	}

	public HQLBuilder<T, I> andStringIn(String campo, List<String> valores) {
		if (valores != null) {
			if (valores.size() > 0) {
				this.hql += " and " + alias + "." + campo + " in " + this.listStringToString(valores);
			}
		}
		return this;
	}
	
	public HQLBuilder<T, I> andStringNotIn(String campo, List<String> valores) {
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

	public HQLBuilder<T,I> orderBy(String ordem) {
		if (StringUtil.notNullOrEmpty(ordem))
			this.ordem = " order by " + alias + "." + ordem;
		return this;
	}
	
	private Query getQuery() {
		return dao.getEntityManager()
				  .createQuery(getHQL());
	}
	
	private Query getQuery(Integer paginaAtual, Integer registrosPorPagina) {
		return dao.getEntityManager()
				 .createQuery(getHQL())
				 .setFirstResult(((paginaAtual - 1) * registrosPorPagina))
				 .setMaxResults(registrosPorPagina);
	}
	
	private Query getQueryCount() {
		return dao.getEntityManager().createQuery(getHQLCount());
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
		
		return dao.listPagination(query, queryCount, paginaAtual, registrosPorPagina);
	}
	
}
