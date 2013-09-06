package gerenciamentodefrota.test.controller;

import org.junit.Test;

import gerenciamentodefrota.controller.CombustivelController;
import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.test.dao.DAOTest;

public class CombustivelControllerTest extends DAOTest {

	@Test
	public void cadastrarCombustivel() {
		CombustivelDAO combustivelDAO = new  CombustivelDAO(manager);
		
		CombustivelController controller = new CombustivelController(combustivelDAO, validator, result, notice);
		
	}
	
}
