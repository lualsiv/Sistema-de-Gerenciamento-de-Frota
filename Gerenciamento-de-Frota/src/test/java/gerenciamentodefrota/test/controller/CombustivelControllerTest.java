package gerenciamentodefrota.test.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
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
		super.finalize();
	}

	@Test
	public void deveRetornarListaCom2Itens() {
		List<Combustivel> lista = controller.lista();
		Assert.assertEquals(lista.size(), 2);
	}

	@Test
	public void cadastrarNovoCombustivel() {
		Combustivel combustivel = new Combustivel();
		combustivel.setDescricao("ALCOOL");
		combustivel.setPreco(new BigDecimal("2.55"));
		
		controller.salva(combustivel);
		
		List<Combustivel> combustiveis = combustivelDAO.lista();
		
		Assert.assertEquals(3, combustiveis.size());
	}
	
	@Test
	public void salvaAlteracaoDeUmCombustivel() {
		Combustivel combustivel = combustivelDAO.busca((long) 1);
		
//		combustivel.setPreco(new BigDecimal("4.44"));
		combustivel.setDescricao("ALCOOL");
		
		// JSR303MockValidator deve estar bugado
		
		validator.validate(combustivel);
		
		System.out.println("Tem erro? " + validator.hasErrors());
		System.out.println("N. de erros: " + validator.getErrors().size());
		
		System.out.println("Messafes: " + validator.getErrors());
		for (Message m : validator.getErrors()) {
			System.out.println(m.getCategory() + " : " + m.getMessage());
		}
		
		Assert.assertFalse(validator.hasErrors());
		
	}
	
	@Test
	public void editarDadosDeUmCombustivel() {
		controller.editar((long) 1);
		
		Combustivel combustivel = (Combustivel) result.included().get("combustivel");
		Assert.assertEquals("GASOLINA", combustivel.getDescricao());
	}
	
	@Test
	public void tentaValidarUmModelSemErros() {
		Combustivel combustivel = new Combustivel();
		
		combustivel.setId((long) 3);
		combustivel.setDescricao("ALCOOL");
		combustivel.setPreco(new BigDecimal("2.56"));
		
		validator.validate(combustivel);
		
		Assert.assertEquals(validator.getErrors().size(), 0);
	}
	
	@Test
	public void tentaValidarComPrecoNulo() {
		Combustivel combustivel = new Combustivel();
		combustivel.setId((long) 3);
		combustivel.setDescricao("ALCOOL");
		combustivel.setPreco(null);
		
		validator.validate(combustivel);
		
		for (Message m : validator.getErrors()) {
			Assert.assertEquals("preco", m.getCategory());
		}
	}
	
}
