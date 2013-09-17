package gerenciamentodefrota.test.dao;

import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.model.Combustivel;
import gerenciamentodefrota.test.utils.DAOTest;

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
		commitOrRallBack();
		
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
		Combustivel combustivel = combustivelDAO.busca((long)1);
		Assert.assertEquals(new BigDecimal("3.07"), combustivel.getPreco());
	}
	
	@Test
	public void cadastraCombustivel() {
		Combustivel combustivel = new Combustivel();
		combustivel.setDescricao("ALCOOL");
		combustivel.setPreco(new BigDecimal("2.55"));
		combustivelDAO.adiciona(combustivel);
		Assert.assertEquals(3, combustivelDAO.lista().size());
	}
	
	@Test
	public void alteraCombustivel() {
		Combustivel combustivel1 = combustivelDAO.busca((long)1);
		combustivel1.setPreco(new BigDecimal("4.00"));
		combustivelDAO.atualiza(combustivel1);
		
		Combustivel combustivel2 = combustivelDAO.busca((long)1);
		Assert.assertEquals(new BigDecimal("4.00"), combustivel2.getPreco());
	}
}
