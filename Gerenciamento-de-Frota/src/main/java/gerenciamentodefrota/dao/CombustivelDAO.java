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
		dao.create(combustivel);
	}

	public void atualiza(Combustivel combustivel) {
		dao.update(combustivel);
	}
	
	public Combustivel busca(Long id) {
		return dao.find(id);
	}
	
	public List<Combustivel> lista() {
		return dao.list();
	}

}
