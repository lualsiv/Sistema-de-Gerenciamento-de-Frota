package gerenciamentodefrota.dao;

import java.util.List;

import javax.persistence.EntityManager;

import gerenciamentodefrota.model.Motorista;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class MotoristaDAO {

	private DAO<Motorista, Long> dao;
	
	public MotoristaDAO(EntityManager em){
		this.dao = new DAO<Motorista, Long>(em, Motorista.class);
	}
	
	public void adiciona(Motorista motorista) {
		dao.adiciona(motorista);
	}

	public Motorista busca(Long id) {
		return dao.busca(id);
	}
	
	public List<Motorista> lista() {
		return dao.lista();
	}
	
}
