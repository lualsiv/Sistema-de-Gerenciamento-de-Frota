package gerenciamentodefrota.test.dao;

import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.model.Combustivel;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.dbunit.dataset.FileSystemDataSetSource;

public class CombustivelDAOTest extends DAOTest {
	
	private File DATASET = new File("src/test/resources/combustivel.xml");
	FileSystemDataSetSource arquivo = new FileSystemDataSetSource(DATASET);
	CombustivelDAO combustivelDAO;
	
	@Override
	@Before
	public void setup() {
		super.setup();
		dbunitmanager.cleanAndInsert(arquivo);
		combustivelDAO = new CombustivelDAO(entitymanager);
		
		entitymanager.getTransaction().begin();
	}
	
	@Override
	@After
	public void finalize() {
		entitymanager.getTransaction().commit();
		
		super.finalize();
		combustivelDAO = null;
	}
	
	@Test
	public void deveListar2itens() {
		List<Combustivel> combustiveis = combustivelDAO.lista();  
		Assert.assertEquals(2, combustiveis.size());
	}
	
	@Test
	public void buscaPorId() {
//		Combustivel combustivel = combustivelDAO.busca((long)2);
		
		Combustivel combustivel = entitymanager.getReference(Combustivel.class, (long)1 );
		
		Assert.assertEquals(new BigDecimal("3.07"), combustivel.getPreco());
	}
	
	@Test
	public void cadastraCombustivel() {
		Combustivel combustivel = new Combustivel("Alcool", new BigDecimal("2.55"));
		combustivelDAO.adiciona(combustivel);
		
		Assert.assertEquals(3, combustivelDAO.lista().size());
	}
	
}
