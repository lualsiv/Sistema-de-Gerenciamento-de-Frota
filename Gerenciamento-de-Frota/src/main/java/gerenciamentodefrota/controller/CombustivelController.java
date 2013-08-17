package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.model.Combustivel;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class CombustivelController {

	private CombustivelDAO combustivelDAO;

	public CombustivelController(CombustivelDAO combustivelDAO) {
		this.combustivelDAO = combustivelDAO;
	}

	@Get("/combustivel")
	public List<Combustivel> lista() {
		try {
			return combustivelDAO.lista();
		} catch (Exception e) {
			return new ArrayList<Combustivel>();
		}
	}
	
}
