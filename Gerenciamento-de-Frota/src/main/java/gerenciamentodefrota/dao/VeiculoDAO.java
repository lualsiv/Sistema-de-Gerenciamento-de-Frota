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
	
	public void atualiza(Veiculo veiculo) {
		if (veiculo.getId() > 0) {
			dao.alterar(veiculo);
		} else {
			dao.adiciona(veiculo);
		}
	}
	
	public Veiculo busca(Long id) {
		return dao.busca(id);
	}
	
	public Veiculo buscaPorPlaca(String placa) {
		List<Veiculo> veiculos = dao.listAllByFieldUsingLike("placa", placa.toUpperCase());
		
		if(veiculos != null)
			if(veiculos.size() > 0)
				return veiculos.get(0);
			else
				return null;
		else
			return null;
	}

	public List<Veiculo> lista() {
		return dao.lista();
	}

}
