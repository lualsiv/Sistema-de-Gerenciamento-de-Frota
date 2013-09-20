package gerenciamentodefrota.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.validator.Message;
import br.com.triadworks.dbunit.dataset.FileSystemDataSetSource;
import gerenciamentodefrota.controller.CombustivelController;
import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.infra.Notice;
import gerenciamentodefrota.model.Combustivel;
import gerenciamentodefrota.test.utils.DAOTest;

public class CombustivelControllerTest extends DAOTest {

	private File DATASET = new File("src/test/resources/combustivel.xml");
	private FileSystemDataSetSource arquivo = new FileSystemDataSetSource(DATASET);
	private CombustivelDAO combustivelDAO;
	private CombustivelController controller;
	private Validator validator;
	private Result result;
	private Notice notice;
	
	@Override
	@Before
	public void setup() {
		super.setup();
		dbunitmanager.cleanAndInsert(arquivo);
		
		combustivelDAO = new CombustivelDAO(entitymanager);
		validator = new JSR303MockValidator();
		
		result = new MockResult();
		notice = new Notice();
		
		controller = new CombustivelController(combustivelDAO, validator, result, notice);
		
		entitymanager.getTransaction().begin();
	}
	
	@Override
	@After
	public void finalize() {
		commitOrRallBack();
		dbunitmanager.deleteAll(arquivo);
		super.finalize();
	}

	@Test
	public void deveRetornarListaCom2Itens() {
		List<Combustivel> lista = controller.lista();
		assertEquals(lista.size(), 2);
	}
	
	@Test
	public void cadastrarNovoCombustivel() {
		Combustivel combustivel = new Combustivel();
		combustivel.setDescricao("ALCOOL");
		combustivel.setPreco(new BigDecimal("2.55"));
		
		controller.salva(combustivel);
		
		List<Combustivel> combustiveis = combustivelDAO.lista();
		
		assertEquals(3, combustiveis.size());
	}
	
	@Test
	public void salvaAlteracaoDeUmCombustivel() {
		Combustivel combustivel = combustivelDAO.busca((long) 1);
		combustivel.setPreco(new BigDecimal("4.44"));
		
		controller.alterar(combustivel);

		Combustivel busca = combustivelDAO.busca((long) 1);
		assertEquals(new BigDecimal("4.44"), busca.getPreco());

	}
	
	@Test
	public void editarDadosDeUmCombustivel() {
		controller.editar((long) 1);
		
		Combustivel combustivel = (Combustivel) result.included().get("combustivel");
		assertEquals("GASOLINA", combustivel.getDescricao());
	}
	
	@Test
	public void tentaValidarUmModelSemErros() {
		Combustivel combustivel = new Combustivel();
		
		combustivel.setId((long) 3);
		combustivel.setDescricao("ALCOOL");
		combustivel.setPreco(new BigDecimal("2.56"));
		
		validator.validate(combustivel);
		
		assertEquals(validator.getErrors().size(), 0);
	}
	
	@Test
	public void tentaValidarComPrecoNulo() {
		Combustivel combustivel = new Combustivel();
		combustivel.setId((long) 3);
		combustivel.setDescricao("ALCOOL");
		combustivel.setPreco(null);
		
		validator.validate(combustivel);
		
		for (Message m : validator.getErrors()) {
			assertEquals("preco", m.getCategory());
		}
	}
	
	@Test
	public void teste1() {
		Combustivel combustivel = combustivelDAO.busca((long) 1);
		combustivel.setPreco(new BigDecimal("4.44"));
		combustivel.setDescricao("ALCOOL");
		assertFalse("Já tem erros antes!", validator.hasErrors());
		validator.validate(combustivel);        
		assertFalse("Tem erros depois =(", validator.hasErrors());
		
//		Combustivel combustivel = combustivelDAO.busca((long) 1);
//		combustivel.setPreco(new BigDecimal("4.44"));
		
//		validator.validate(combustivel);
//		System.out.println(validator.getErrors());
		
//		assertFalse(validator.getErrors().size() > 0);
//		assertFalse(validator.hasErrors());
	}

	@Test
	public void teste2() {
		Combustivel combustivel = combustivelDAO.busca((long) 1);

		validator.validate(combustivel);
		
		assertFalse(validator.hasErrors());
	}
	
	@Test
	public void teste3() {
		Combustivel combustivel = new Combustivel();
		combustivel.setPreco(new BigDecimal("4.44"));
		combustivel.setDescricao("ALCOOL");

		validator.validate(combustivel);
		
		assertFalse(validator.hasErrors());
	}
	
}
