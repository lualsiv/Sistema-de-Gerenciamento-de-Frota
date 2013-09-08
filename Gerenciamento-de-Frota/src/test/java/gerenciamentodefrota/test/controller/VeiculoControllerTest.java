package gerenciamentodefrota.test.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import gerenciamentodefrota.controller.VeiculoController;
import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.dao.VeiculoDAO;
import gerenciamentodefrota.model.Combustivel;
import gerenciamentodefrota.model.Veiculo;
import gerenciamentodefrota.test.dao.DAOTest;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class VeiculoControllerTest extends DAOTest {

	@Test
	public void listaDeCombustiveisNaoDeveSerNula() {
		MockResult result = new MockResult();
		MockValidator validator = new MockValidator();
		
		VeiculoDAO veiculoDAO = new VeiculoDAO(manager);
		CombustivelDAO combustivelDAO = new  CombustivelDAO(manager);
		
		VeiculoController controller = new VeiculoController(result, veiculoDAO, validator, combustivelDAO);
		controller.novo();
		List<Combustivel> lista = result.included("combustiveis");
		
		Assert.assertNotNull(lista);
	}
	
	@Test
	public void listaVeiculosNaoPodeSerVazia() {
		MockResult result = new MockResult();
		MockValidator validator = new MockValidator();
		
		VeiculoDAO veiculoDAO = new VeiculoDAO(manager);
		CombustivelDAO combustivelDAO = new  CombustivelDAO(manager);
		
		VeiculoController controller = new VeiculoController(result, veiculoDAO, validator, combustivelDAO);
		
		List<Veiculo> lista = controller.lista();
		
		Assert.assertTrue(lista.size() > 0);
	}
	
}
