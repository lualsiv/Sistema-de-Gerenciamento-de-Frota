package gerenciamentodefrota.test.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import gerenciamentodefrota.service.Pasta;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PastaTest {

	@Before
	public void init() throws IOException {
		File bmp = new File("D:/Projetos/Teste/arquivo.bmp");
		if (bmp.exists())
			bmp.delete();
		bmp.createNewFile();
		
		File zip = new File("D:/Projetos/Teste/arquivo.zip");
		if (zip.exists())
			zip.delete();
		zip.createNewFile();
		
		File txt = new File("D:/Projetos/Teste/arquivo.txt");
		if (txt.exists())
			txt.delete();
		txt.createNewFile();
		
	}
	
	@Test
	public void deveRetornarUmaListaCom3arquivos() {
		Pasta pasta = new Pasta("D:/Projetos/Teste/");
		Assert.assertEquals(pasta.get().size(), 3);
	}
	
	@Test
	public void deveRetornarOArquivo_ArquivoBMP() {
		Pasta pasta = new Pasta("D:/Projetos/Teste/");
		Assert.assertEquals(pasta.get("arquivo.bmp").getName(), "arquivo.bmp");
	}
	
	@Test
	public void deveRetornaArrayDeBytesVazio() throws FileNotFoundException, IOException {
		Pasta pasta = new Pasta("D:/Projetos/Teste/");
		Assert.assertEquals(pasta.toByteArray("arquivo.txt").length, 0);
	}
	
}
