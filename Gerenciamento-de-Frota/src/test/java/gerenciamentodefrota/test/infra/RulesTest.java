package gerenciamentodefrota.test.infra;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import gerenciamentodefrota.infra.Rules;

public class RulesTest {

	Rules rules;

	@Before
	public void setup() {
		rules = new Rules();
		List<Object> list = new ArrayList<Object>();
		list.add("A");
		list.add("B");
		list.add("D");
		list.add("E");
		
		rules.addRules("nomes", list);
	}
	
	@Test
	public void deveVerificarPermissaoERetornaTrue() {
		Assert.assertTrue(rules.hasPermission("nomes", "A"));
	}
	
	@Test
	public void deveVerificarPermissaoERetornaFalse() {
		Assert.assertFalse(rules.hasPermission("nomes", "C"));
	}
	
	@Test(expected=NullPointerException.class)
	public void deveVerificarPermissaoQueNaoExisteERetornaNullPointerException() {
		Assert.assertFalse(rules.hasPermission("ler", "C"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tentaSetarNuloNaLista() {
		Rules rules2 = new Rules();
		rules2.addRules("ler", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void tentaSetarListaVazia() {
		Rules rules2 = new Rules();
		rules2.addRules("ler", new ArrayList<Object>());
	}
	
}
