package gerenciamentodefrota.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import gerenciamentodefrota.model.Veiculo;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class VeiculoDAO {

	private DAO<Veiculo, Long> dao;

	public VeiculoDAO(EntityManager em) {
		this.dao = new DAO<Veiculo, Long>(em, Veiculo.class);
	}

	public void adiciona(Veiculo veiculo) {
		dao.adiciona(veiculo);
	}

	public void alterar(Veiculo veiculo) {
		dao.alterar(veiculo);
	}

	public void atualiza(Veiculo veiculo) {
		if (veiculo.getId() != null) {
			dao.alterar(veiculo);
		} else {
			dao.adiciona(veiculo);
		}
	}

	public Veiculo busca(Long id) {
		return dao.busca(id);
	}

	public Veiculo buscaPorPlaca(String placa) {
		try {
			return dao.findByField("placa", placa.toUpperCase()).get(0);
		} catch (NoResultException e) {
			return null;
		}
		catch (Exception e) {
			return null;
		}
	}

	public List<Veiculo> lista() {
		return dao.lista();
	}

}
