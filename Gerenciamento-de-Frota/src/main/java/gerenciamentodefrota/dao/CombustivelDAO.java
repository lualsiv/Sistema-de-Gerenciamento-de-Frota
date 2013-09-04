package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.model.Combustivel;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CombustivelDAO {

	private DAO<Combustivel, Long> dao;

	public CombustivelDAO(EntityManager em){
		this.dao = new DAO<Combustivel, Long>(em, Combustivel.class);
	}
	
	public void adiciona(Combustivel combustivel) {
		dao.adiciona(combustivel);
	}

	public void atualiza(Combustivel combustivel) {
		dao.alterar(combustivel);
	}
	
	public Combustivel busca(Long id) {
		return dao.busca(id);
	}
	
	public List<Combustivel> lista() {
		return dao.lista();
	}

}
