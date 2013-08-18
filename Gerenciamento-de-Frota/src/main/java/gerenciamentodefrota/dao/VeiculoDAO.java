package gerenciamentodefrota.dao;

import java.util.List;

import javax.persistence.EntityManager;

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
		if (placa == null)
			return null;

		List<Veiculo> veiculos;
		veiculos = dao.listAllByField("placa", placa.toUpperCase());

		if (veiculos.size() > 0)
			return veiculos.get(0);
		else
			return null;
	}

	public List<Veiculo> lista() {
		return dao.lista();
	}

}
