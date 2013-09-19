package gerenciamentodefrota.dao;

import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.infra.Select;
import gerenciamentodefrota.util.StringUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class HQLBuilder<T> {
	
	private String hql;
	private Map<String, Object> campos = new HashMap<String, Object>();
	private String ordem;
	private Class<T> classe;
	private String alias;
	private EntityManager em;
	
	public HQLBuilder(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
		this.alias = "a";
		this.hql = "";
		this.ordem = "";
	}
	
	private String getHQL() {
		clearHQL();
		return "select " + alias + " " + hql + ordem;
	}
	
	private String getHQLSelect(String value, String text) {
		clearHQL();
		String hqlSelect = "select new #select#(#value#,#text#) "
						   .replaceAll("#select#", Select.class.getName())
						   .replaceAll("#value#", value)
						   .replaceAll("#text#", text);
		
		return hqlSelect + hql + ordem;
	}
	
	private String getHQLCount() {
		clearHQL();
		return "select count(*) " + hql;
	}

	private void clearHQL() {
		hql = hql.replaceAll(" 1=1 and", "");
		hql = hql.replaceAll(" 1=1 or", "");
		hql = hql.replaceAll(" where  ", "where ");
		hql = hql.replaceAll(" 1=1a.", " a.");
	}
	
	public HQLBuilder<T> from() {
		this.hql = "from " + classe.getName() + " " + alias + " where 1=1";
		return this;
	}
	
	public HQLBuilder<T> andBetween(String campo, Object valor1, Object valor2) {
		return montaCondicao(campo, valor1, valor2, Condition.AND, Operator.BETWEEN);
	}
	
	public HQLBuilder<T> andBetweenIf(String campo, Object valor1, Object valor2) {
		return montaCondicaoIf(campo, valor1, valor2, Condition.AND, Operator.BETWEEN);
	}

	public HQLBuilder<T> andNotBetween(String campo, Object valor1, Object valor2) {
		return montaCondicao(campo, valor1, valor2, Condition.AND, Operator.NOTBETWEEN);
	}

	public HQLBuilder<T> andNotBetweenIf(String campo, Object valor1, Object valor2) {
		return montaCondicaoIf(campo, valor1, valor2, Condition.AND, Operator.NOTBETWEEN);
	}
	
	public HQLBuilder<T> andIsTrue(String campo) {
		return montaCondicao(campo, Condition.AND, Operator.ISTRUE);
	}
	
	public HQLBuilder<T> andIsFalse(String campo) {
		return montaCondicao(campo, Condition.AND, Operator.ISFALSE);
	}
	
	public HQLBuilder<T> andIsNull(String campo) {
		return montaCondicao(campo, Condition.AND, Operator.ISNULL);
	}
	
	public HQLBuilder<T> andNotIsNull(String campo) {
		return montaCondicao(campo, Condition.AND, Operator.ISNOTNULL);
	}

	public HQLBuilder<T> andEqualsIf(String campo, Object valor) {
		return montaCondicaoIf(campo, valor, Condition.AND, Operator.EQUALS);
	}
	
	public HQLBuilder<T> andNotEquals(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.AND, Operator.NOTEQUALS);
	}
	
	public HQLBuilder<T> andNotEqualsIf(String campo, Object valor) {
		return montaCondicaoIf(campo, valor, Condition.AND, Operator.NOTEQUALS);
	}
	
	public HQLBuilder<T> andLessOrEquals(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.AND, Operator.LESSOREQUALS);
	}
	
	public HQLBuilder<T> andLessOrEqualsIf(String campo, Object valor) {
		return montaCondicaoIf(campo, valor, Condition.AND, Operator.LESSOREQUALS);
	}
	
	public HQLBuilder<T> andGreaterThan(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.AND, Operator.GREATERTHAN);
	}	
	
	public HQLBuilder<T> andGreaterThanIf(String campo, Object valor) {
		return montaCondicaoIf(campo, valor, Condition.AND, Operator.GREATERTHAN);
	}
	
	public HQLBuilder<T> andLessThan(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.AND, Operator.LESSTHAN);
	}
	
	public HQLBuilder<T> andLessThanIf(String campo, Object valor) {
		return montaCondicaoIf(campo, valor, Condition.AND, Operator.LESSTHAN);
	}

	public HQLBuilder<T> andGreaterOrEquals(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.AND, Operator.GREATEROREQUALS);
	}
	
	public HQLBuilder<T> andGreaterOrEqualsIf(String campo, Object valor) {
		return montaCondicaoIf(campo, valor, Condition.AND, Operator.GREATEROREQUALS);
	}
	
	public HQLBuilder<T> andStringLikeStartWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return montaCondicao(campo, valor + "%", Condition.AND, Operator.LIKE);
		return this;
	}
	
	public HQLBuilder<T> andStringLikeEndsWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return montaCondicao(campo, "%" + valor, Condition.AND, Operator.LIKE);
		return this;
	}
	
	public HQLBuilder<T> andStringLikeContainsIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return montaCondicao(campo, "%" + valor + "%", Condition.AND, Operator.LIKE);
		return this;
	}
	
	public HQLBuilder<T> andStringNotLikeStartWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return montaCondicao(campo, valor + "%", Condition.AND, Operator.NOTLIKE);
		return this;
	}
	
	public HQLBuilder<T> andStringNotLikeEndsWithIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return montaCondicao(campo, "%" + valor, Condition.AND, Operator.NOTLIKE);
		return this;
	}
	
	public HQLBuilder<T> andStringNotLikeContainsIf(String campo, String valor) {
		if(StringUtil.notNullOrEmpty(valor))
			return montaCondicao(campo, "%" + valor + "%", Condition.AND, Operator.NOTLIKE);
		return this;
	}
	
	public HQLBuilder<T> andStringLike(String campo, String valor) {
		return montaCondicao(campo, valor, Condition.AND, Operator.LIKE);
	}

	public HQLBuilder<T> andStringNotLike(String campo, String valor) {
		return montaCondicao(campo, valor, Condition.AND, Operator.NOTLIKE);
	}

	public HQLBuilder<T> equals(String campo, Object valor, Condition cond) {
		return montaCondicao(campo, valor, cond, Operator.EQUALS);
	}
	
	public HQLBuilder<T> equals(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.NONE, Operator.EQUALS);
	}
	
	public HQLBuilder<T> equalsIf(String campo, Object valor, Condition cond) {
		return montaCondicaoIf(campo, valor, cond, Operator.EQUALS);
	}

	public HQLBuilder<T> andEquals(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.AND, Operator.EQUALS);
	}
	
	public HQLBuilder<T> andEqualsIf(String campo, Object valor, Condition cond) {
		return montaCondicaoIf(campo, valor, Condition.AND, Operator.EQUALS);
	}

	public HQLBuilder<T> orEquals(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.OR, Operator.EQUALS);
	}
	
	public HQLBuilder<T> orEqualsIf(String campo, Object valor) {
		return montaCondicaoIf(campo, valor, Condition.OR, Operator.EQUALS);
	}
	
	public HQLBuilder<T> orNotEquals(String campo, Object valor) {
		return montaCondicao(campo, valor, Condition.OR, Operator.NOTEQUALS);
	}
	
	public HQLBuilder<T> orNotEqualsIf(String campo, Object valor) {
		return montaCondicaoIf(campo, valor, Condition.OR, Operator.NOTEQUALS);
	}

	public HQLBuilder<T> andIntegerIn(String campo, List<Integer> valores) {
		String valoresString = listObjectToString(Arrays.asList(valores.toArray()));
		return montaCondicao(campo, valoresString, Condition.AND, Operator.IN);
	}
	
	public HQLBuilder<T> andLongIn(String campo, List<Long> valores) {
		String valoresString = listObjectToString(Arrays.asList(valores.toArray()));
		return montaCondicao(campo, valoresString, Condition.AND, Operator.IN);
	}
	
	public HQLBuilder<T> andIn(String campo, List<Object> valores) {
		if (valores != null) {
			if (valores.size() > 0) {
				String valoresString = listObjectToString(Arrays.asList(valores.toArray()));
				return montaCondicao(campo, valoresString, Condition.AND, Operator.IN);
			}
		}
		return this;
	}
	
	public HQLBuilder<T> andStringIn(String campo, List<String> valores) {
		if (valores != null) {
			if (valores.size() > 0) {
				String valoresString = listStringToString(valores);
				return montaCondicao(campo, valoresString, Condition.AND, Operator.IN);
			}
		}
		return this;
	}
	
	public HQLBuilder<T> andStringNotIn(String campo, List<String> valores) {
		if (valores != null) {
			if (valores.size() > 0) {
				String valoresString = listStringToString(valores);
				return montaCondicao(campo, valoresString, Condition.AND, Operator.NOTIN);
			}
		}
		return this;
	}
	
	private String listStringToString(List<String> valores) {
		return valores.toString()
					  .replace("[", "('")
					  .replace("]", "')")
					  .replaceAll(", ", "', '");
	}
	
	private String listObjectToString(List<Object> valores) {
		return valores.toString()
				  .replace("[", "(")
				  .replace("]", ")")
				  .replaceAll(", ", ", ");
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
		Query query = this.getQuery();

		for(Entry<String, Object> e : campos.entrySet()) {
			query.setParameter(e.getKey(), e.getValue());
	    }
		
		return (List<T>) query.getResultList();
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

	@SuppressWarnings("unchecked")
	public List<Select> listSelect(String valor, String texto) {
		Query query = em.createQuery(getHQLSelect(valor, texto));
		
		List<Select> selectList = query.getResultList();
		return selectList;
	}
	
	public HQLBuilder<T> montaCondicao(String campo, Condition cond, Operator oper) {
		String condicao = oper.toString()
							  .replaceAll("#FIELD#", campo);
		
		this.hql += cond.toString() + alias + "." + condicao;
		return this;
	}
	
	public HQLBuilder<T> montaCondicaoIf(String campo, Object valor, Condition cond, Operator oper) {
		if (valor != null)
			return montaCondicao(campo, valor, cond, oper);
		return this;
	}
	
	public String getParameterName(String parametro) {
		return parametro + "_" + ( campos.size() + 1);
	}
	
	private String setParametro(String hql, String chave, String campo, Object valor) {
		String parameter = getParameterName(campo);
		hql = hql.replaceAll(chave, ":" + parameter);

		if (hql.contains(parameter))
			campos.put(parameter, valor);
		
		return hql;
	}
	
	public HQLBuilder<T> montaCondicao(String campo, Object valor, Condition cond, Operator oper) {
		String condicao = oper.toString()
							  .replaceAll("#FIELD#", campo)
							  .replaceAll("#LIST#", valor.toString());

		condicao = setParametro(condicao, "#VALUE1#", campo, valor);
		
		this.hql += cond.toString() + alias + "." + condicao;
		
		return this;
	}
	
	public HQLBuilder<T> montaCondicaoIf(String campo, Object valor1, Object valor2, Condition cond, Operator oper) {
		if (valor1 != null && valor2 != null)
			return montaCondicao(campo, valor1, cond, oper);
		return this;
	}
	
	public HQLBuilder<T> montaCondicao(String campo, Object valor1, Object valor2, Condition cond, Operator oper) {
		String condicao = oper.toString().replaceAll("#FIELD#", campo);

		condicao = setParametro(condicao, "#VALUE1#", campo, valor1);
		condicao = setParametro(condicao, "#VALUE2#", campo, valor2);
		
		this.hql += cond.toString() + alias + "." + condicao;
		return this;
	}
	
}
