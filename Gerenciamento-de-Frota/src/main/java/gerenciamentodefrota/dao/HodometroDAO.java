package gerenciamentodefrota.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.model.Hodometro;
import gerenciamentodefrota.model.Veiculo;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class HodometroDAO {

	private DAO<Hodometro, Long> dao;
	
	public HodometroDAO(EntityManager em) {
		this.dao = new DAO<Hodometro, Long>(em, Hodometro.class);
	}
	
	public void adiciona(Hodometro hodometro) {
		dao.adiciona(hodometro);
	}
	
	public Hodometro busca(Long id) {
		return dao.busca(id);
	}
	
	public List<Hodometro> lista() {
		return dao.lista();
	}
	
	public BigDecimal ultimaQuilometragem(Veiculo veiculo) {
		if(veiculo == null)
			return null;
		
		StringBuilder builder = new StringBuilder("select o from " + Hodometro.class.getName() + " o ");
		builder.append("where o.veiculo.id = :id order by o.quilometragem desc");
		
		Query query = dao.getEm().createQuery(builder.toString())
								 .setParameter("id", veiculo.getId())
								 .setMaxResults(1);
		
		try {
			return ((Hodometro)query.getSingleResult()).getQuilometragem();
		} catch (NoResultException e) {
			return BigDecimal.ZERO;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Hodometro ultimaRegistroDoVeiculo(Veiculo veiculo) {
		if(veiculo == null)
			return null;
		
		StringBuilder builder = new StringBuilder("select o from " + Hodometro.class.getName() + " o ");
		builder.append("where o.veiculo.id = :id order by o.quilometragem desc");
		
		Query query = dao.getEm().createQuery(builder.toString());
		query.setParameter("id", veiculo.getId());
		query.setMaxResults(1);

		List<Hodometro> hodometros = query.getResultList();
		
		if (hodometros.size() > 0)
			return hodometros.get(0);
		else
			return null;
	}

	public List<Hodometro> ultimoRegistroCadaVeiculo() {
		// TODO Implementar
		return new ArrayList<Hodometro>();
	}
	
}
