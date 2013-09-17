package gerenciamentodefrota.test.dao;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.dbunit.dataset.FileSystemDataSetSource;
import gerenciamentodefrota.dao.HQLBuilder;
import gerenciamentodefrota.infra.Pagination;
import gerenciamentodefrota.model.Funcionario;
import gerenciamentodefrota.test.utils.DAOTest;

public class HQLBuilderTest extends DAOTest {

	private File DATASET = new File("src/test/resources/funcionario.xml");
	private FileSystemDataSetSource arquivo = new FileSystemDataSetSource(DATASET);
	private HQLBuilder<Funcionario> hql;
	
	@Override
	@Before
	public void setup() {
		super.setup();
		dbunitmanager.cleanAndInsert(arquivo);
		this.hql = new HQLBuilder<Funcionario>(entitymanager, Funcionario.class);
	}

	@Override
	@After
	public void finalize() {
		super.finalize();
	}
	
	@Test
	public void deveListarTodosOsRegisTrosERetornar23Registros() {
		List<Funcionario> funcionarios = hql.from()
											.list();
		
		Assert.assertEquals(funcionarios.size(), 23);
	}
	
	@Test
	public void devePaginarOsRegistrosEm15RegistrosPorPaginaERetornar23RegistrosNoTotal() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .listPagination(1, Pagination.PAGESIZE);
		
		Assert.assertEquals(funcionarios.getTotalCount(), 23);
	}
	
	@Test
	public void deveListasOsRegistrosPorPaginaERetornar15RegistrosPorPagina() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .listPagination(1, 15);
		
		Assert.assertEquals(funcionarios.getList().size(), 15);
	}
	
	@Test
	public void devePaginarOsRegistrosEm5RegistrosPorPaginaERetornar3paginas() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .listPagination(1, 5);
		
		Assert.assertEquals(funcionarios.getTotalPage(), 5);
	}
	
	@Test
	public void deveRetornar3RegistrosNaPagina5() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .listPagination(5, 5);
		
		Assert.assertEquals(funcionarios.getList().size(), 3);
	}
	
	@Test
	public void deveFiltrarOsRegistrosUsandoAndEqualsERetornar1Registro() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .andEquals("cadastro", "101")
												  .listPagination(1, Pagination.PAGESIZE);

		Assert.assertEquals(funcionarios.getTotalCount(), 1);
	}
	
	@Test
	public void deveFiltrarRegistrosUsandoAndBetweenERetornar4Registros() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .andBetween("id", (long)1, (long)4)
												  .listPagination(1, Pagination.PAGESIZE);

		Assert.assertEquals(funcionarios.getTotalCount(), 4);
	}
	
	@Test
	public void deveFiltrarOsRegistrosUsandoAndEqualsEOrEqualsERetornar2Registros() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .andEquals("cadastro", "101")
												  .orEquals("id", (long)2)
												  .listPagination(1, Pagination.PAGESIZE);

		Assert.assertEquals(funcionarios.getTotalCount(), 2);
	}
	
	@Test
	public void deveFiltrarOsRegistrosUsandoAndGreaterThanERetornar3Registros() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .andGreaterThan("id", (long)20)
												  .listPagination(1, Pagination.PAGESIZE);
		
		Assert.assertEquals(funcionarios.getTotalCount(), 3);
	}
	
	@Test
	public void deveFiltrarOsRegistrosUsandoAndLongInERetornar5Registros() {
		List<Long> valores = Arrays.asList((long)1,(long)3,(long)5,(long)7,(long)9);
		
		Pagination<Funcionario> funcionarios = hql.from()
												  .andLongIn("id", valores)
												  .listPagination(1, Pagination.PAGESIZE);
		
		Assert.assertEquals(funcionarios.getTotalCount(), 5);
	}
	
	@Test
	public void deveFiltrarOsRegistrosUsandoAndStringInERetornar5Registros() {
		List<String> valores = Arrays.asList("101","103","105","107","109");
		
		Pagination<Funcionario> funcionarios = hql.from()
												  .andStringIn("cadastro", valores)
												  .listPagination(1, Pagination.PAGESIZE);
		
		Assert.assertEquals(funcionarios.getTotalCount(), 5);
	}
	
	@Test
	public void deveFiltrarOsRegistrosUsandoAndStringNotInERetornar18Registros() {
		List<String> valores = Arrays.asList("101","103","105","107","109");
		
		Pagination<Funcionario> funcionarios = hql.from()
												  .andStringNotIn("cadastro", valores)
												  .listPagination(1, Pagination.PAGESIZE);
		
		Assert.assertEquals(funcionarios.getTotalCount(), 18);
	}
	
	@Test
	public void deveFiltrarOsRegistrosUsandoAndIsFalseERetornar3Registros() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .andIsFalse("situacao")
												  .listPagination(1, Pagination.PAGESIZE);
		
		Assert.assertEquals(funcionarios.getTotalCount(), 3);
	}
	
	@Test
	public void deveFiltrarOsRegistrosUsandoAndIsTrueERetornar3Registros() {
		Pagination<Funcionario> funcionarios = hql.from()
												  .andIsTrue("situacao")
												  .listPagination(1, Pagination.PAGESIZE);
		Assert.assertEquals(funcionarios.getTotalCount(), 20);
	}
	
}
