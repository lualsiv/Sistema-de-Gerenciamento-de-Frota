package gerenciamentodefrota.test.controller;

import gerenciamentodefrota.controller.FuncionarioController;
import gerenciamentodefrota.dao.FuncionarioDAO;
import gerenciamentodefrota.infra.Notice;
import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.model.Funcionario;
import gerenciamentodefrota.test.utils.DAOTest;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.triadworks.dbunit.dataset.FileSystemDataSetSource;

public class FuncionarioControllerTest extends DAOTest {

	private File DATASET = new File("src/test/resources/funcionario.xml");
	private FileSystemDataSetSource arquivo = new FileSystemDataSetSource(DATASET);
	private FuncionarioDAO funcionarioDAO;
	private FuncionarioController controller;
	private Validator validator;
	private Result result;
	private Notice notice;
	
	@Override
	@Before
	public void setup() {
		super.setup();
		dbunitmanager.cleanAndInsert(arquivo);
		
		funcionarioDAO = new FuncionarioDAO(entitymanager);
		validator = new JSR303MockValidator();
		
		result = new MockResult();
		notice = new Notice();
		
		controller = new FuncionarioController(funcionarioDAO, validator, result, notice);
		
		entitymanager.getTransaction().begin();
	}
	
	@Override
	@After
	public void finalize() {
		commitOrRallBack();
		super.finalize();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deveRetornar8RegistrosNaPagina2() {
		controller.lista(null, null, 2);
		Pagination<Funcionario> lista = (Pagination<Funcionario>) result.included().get("funcionarios");
		Assert.assertEquals(8, lista.getList().size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deveRetornarListaCom2Paginas() {
		controller.lista(null, null, 1);
		Pagination<Funcionario> lista = (Pagination<Funcionario>) result.included().get("funcionarios");
		Assert.assertEquals(new Integer(2), lista.getTotalPage());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deveRetornarListaCom23Registros() {
		controller.lista(null, null, 1);
		Pagination<Funcionario> lista = (Pagination<Funcionario>) result.included().get("funcionarios");
		Assert.assertEquals(new Integer(23), lista.getTotalCount());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deveRetornarListaNaPagina2() {
		controller.lista(null, null, 2);
		Pagination<Funcionario> lista = (Pagination<Funcionario>) result.included().get("funcionarios");
		Assert.assertEquals(new Integer(2), lista.getPageNum());
	}
	
}
