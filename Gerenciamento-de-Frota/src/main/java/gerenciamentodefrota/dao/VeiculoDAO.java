package gerenciamentodefrota.dao;

import java.util.List;

import javax.persistence.EntityManager;

import gerenciamentodefrota.model.Veiculo;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class VeiculoDAO {

	private DAO<Veiculo> dao;
	private EntityManager em;
	
	public VeiculoDAO(EntityManager em) {
		this.em = em;
		this.dao = new DAO<Veiculo>(em, Veiculo.class);
	}
	
	public void adiciona(Veiculo veiculo) {
			dao.adiciona(veiculo);
	}
	
	public void alterar(Veiculo veiculo) {
		dao.alterar(veiculo);
	}
	
	public Veiculo busca(Long id){
		return dao.busca(id);
	}
	
	public List<Veiculo> buscaPorPlaca(String placa){
		return dao.listAllByFieldUsingLike("placa", placa);
	}
	
	public List<Veiculo> lista() {
		return dao.lista();
	}
	
}
