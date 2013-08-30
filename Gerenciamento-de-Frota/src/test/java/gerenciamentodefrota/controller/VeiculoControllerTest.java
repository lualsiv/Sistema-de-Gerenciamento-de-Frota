package gerenciamentodefrota.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Test;

import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.dao.EntityManagerCreator;
import gerenciamentodefrota.dao.EntityManagerFactoryCreator;
import gerenciamentodefrota.dao.VeiculoDAO;
import gerenciamentodefrota.model.Combustivel;
import gerenciamentodefrota.model.Veiculo;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class VeiculoControllerTest {

	@Test
	public void listaDeCombustiveisNaoDeveSerNula() {
		MockResult result = new MockResult();
		MockValidator validator = new MockValidator();
		
		EntityManagerFactory factory = new EntityManagerFactoryCreator().getInstance();
		EntityManager em = new EntityManagerCreator(factory).getInstance();
		VeiculoDAO veiculoDAO = new VeiculoDAO(em);
		CombustivelDAO combustivelDAO = new  CombustivelDAO(em);
		
		VeiculoController controller = new VeiculoController(result, veiculoDAO, validator, combustivelDAO);
		controller.novo();
		List<Combustivel> lista = result.included("combustiveis");
		
		Assert.assertNotNull(lista);
	}
	
	@Test
	public void listaVeiculosNaoPodeSerVazia() {
		MockResult result = new MockResult();
		MockValidator validator = new MockValidator();
		
		EntityManagerFactory factory = new EntityManagerFactoryCreator().getInstance();
		EntityManager em = new EntityManagerCreator(factory).getInstance();
		VeiculoDAO veiculoDAO = new VeiculoDAO(em);
		CombustivelDAO combustivelDAO = new  CombustivelDAO(em);
		
		VeiculoController controller = new VeiculoController(result, veiculoDAO, validator, combustivelDAO);
		
		List<Veiculo> lista = controller.lista();
		
		Assert.assertTrue(lista.size() > 0);
	}
	
}
