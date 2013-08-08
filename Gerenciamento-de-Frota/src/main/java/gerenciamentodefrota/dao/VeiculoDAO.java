package gerenciamentodefrota.dao;

import java.util.List;

import javax.persistence.EntityManager;

import gerenciamentodefrota.model.Veiculo;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class VeiculoDAO {

	private DAO<Veiculo> dao;

	public VeiculoDAO(EntityManager em) {
		this.dao = new DAO<Veiculo>(em, Veiculo.class);
	}

	public void adiciona(Veiculo veiculo) {
		dao.adiciona(veiculo);
	}

	public void alterar(Veiculo veiculo) {
		dao.alterar(veiculo);
	}

	public Veiculo busca(Long id) {
		return dao.busca(id);
	}

	public Veiculo buscaPorPlaca(String placa) {
		List<Veiculo> veiculos = dao.listAllByFieldUsingLike("placa", placa);

		if(veiculos != null)
			return veiculos.get(0);
		else
			return null;
	}

	public List<Veiculo> lista() {
		return dao.lista();
	}

}
