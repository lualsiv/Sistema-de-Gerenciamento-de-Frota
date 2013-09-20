package gerenciamentodefrota.test.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.triadworks.dbunit.dataset.FileSystemDataSetSource;
import gerenciamentodefrota.controller.HodometroController;
import gerenciamentodefrota.dao.HodometroDAO;
import gerenciamentodefrota.dao.UsuarioDAO;
import gerenciamentodefrota.dao.VeiculoDAO;
import gerenciamentodefrota.infra.UsuarioSession;
import gerenciamentodefrota.model.Hodometro;
import gerenciamentodefrota.model.Veiculo;
import gerenciamentodefrota.test.utils.DAOTest;

public class HodometroControllerTest extends DAOTest {

	private File DATASET = new File("src/test/resources/hodometro.xml");
	private FileSystemDataSetSource arquivo = new FileSystemDataSetSource(DATASET);
	private HodometroController controller;
	private Validator validator;
	private Result result;
	private UsuarioSession usuarioSession;
	
	private VeiculoDAO veiculoDAO;
	private HodometroDAO hodometroDAO;
	private UsuarioDAO usuarioDAO;
	
	@Override
	@Before
	public void setup() {
		super.setup();
		dbunitmanager.cleanAndInsert(arquivo);
		
		result = new MockResult();
		validator = new JSR303MockValidator();
		
		veiculoDAO = new VeiculoDAO(entitymanager);
		hodometroDAO = new HodometroDAO(entitymanager);
		usuarioDAO = new UsuarioDAO(entitymanager);
		
		usuarioSession = new UsuarioSession();
		
		controller = new HodometroController(result, validator, veiculoDAO, hodometroDAO, usuarioSession);
		
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
	public void deveListarOsRegistrosDeHodometroERetornar1Item() {
		List<Hodometro> lista = controller.lista();
		
		assertEquals(1, lista.size());
	}
	
	@Test
	public void deveGravarUmNovoRegistroDeHodometro() {
		Hodometro hodometro = new Hodometro();
		hodometro.setVeiculo(veiculoDAO.busca((long)4));
		hodometro.setUsuario(usuarioDAO.busca((long)2));
		hodometro.setQuilometragem(new BigDecimal("27.34"));
		hodometro.setDataLeitura(new LocalDateTime(2013,9,19,17,23,34));
		
		controller.novoRegistro(hodometro);
		
		System.out.println(validator.getErrors());
		
		List<Hodometro> lista = controller.lista();
		
		assertEquals(2, lista.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void naoDeveGravarSeQuilometragemReceberNull() {
		Hodometro hodometro = new Hodometro();
		hodometro.setVeiculo(veiculoDAO.busca((long)4));
		hodometro.setUsuario(usuarioDAO.busca((long)2));
		hodometro.setQuilometragem(null);
		hodometro.setDataLeitura(new LocalDateTime(2013,9,19,17,23,34));
		
		controller.novoRegistro(hodometro);
	}

	@Test(expected=ValidationException.class)
	public void naoDeveGravarSePlacaDeUmVeiculoNaoCadastrado() {
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("asd-2322");
		
		Hodometro hodometro = new Hodometro();
		hodometro.setVeiculo(veiculo);
		hodometro.setUsuario(usuarioDAO.busca((long)2));
		hodometro.setQuilometragem(new BigDecimal("27.34"));
		hodometro.setDataLeitura(new LocalDateTime(2013,9,19,17,23,34));
		
		controller.novoRegistro(hodometro);
	}
	
	@Test
	public void naoDeveGravarRegistroComUsuarioNull() {
		Hodometro hodometro = new Hodometro();
		hodometro.setVeiculo(veiculoDAO.busca((long)4));
		hodometro.setUsuario(null);
		hodometro.setQuilometragem(new BigDecimal("27.34"));
		hodometro.setDataLeitura(new LocalDateTime(2013,9,19,17,23,34));
		
		controller.novoRegistro(hodometro);
	}
	
	@Test(expected=NullPointerException.class)
	public void naoDeveGravarRegistroComVeiculoNull() {
		Hodometro hodometro = new Hodometro();
		hodometro.setVeiculo(null);
		hodometro.setUsuario(usuarioDAO.busca((long)2));
		hodometro.setQuilometragem(new BigDecimal("27.34"));
		hodometro.setDataLeitura(new LocalDateTime(2013,9,19,17,23,34));
		
		controller.novoRegistro(hodometro);
	}
	
	@Test
	public void asd() {
		
	}
}
