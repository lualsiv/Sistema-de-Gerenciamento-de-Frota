package gerenciamentodefrota.test.dao;

import gerenciamentodefrota.test.dao.JdbcDataSource;

import java.io.File;

import javax.sql.DataSource;

import org.junit.Test;

import br.com.triadworks.dbunit.dataset.FileSystemDataSetSource;
import br.com.triadworks.dbunit.vendors.postgresql.PostgreSqlDbUnitManagerImpl;

public class TesteDBUnit {
	
	private File DATASET = new File("src/test/resources/combustivel.xml");
	private PostgreSqlDbUnitManagerImpl manager;
	private DataSource dataSource;
	
	@Test
	public void teste01() throws ClassNotFoundException {
		dataSource = new JdbcDataSource();
		
		FileSystemDataSetSource arquivo = new FileSystemDataSetSource(DATASET);
		
		manager = new PostgreSqlDbUnitManagerImpl(dataSource);
		manager.cleanAndInsert(arquivo);
		
	}

}
