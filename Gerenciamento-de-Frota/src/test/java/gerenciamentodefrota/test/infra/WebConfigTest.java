package gerenciamentodefrota.test.infra;

import java.util.MissingResourceException;

import junit.framework.Assert;

import gerenciamentodefrota.infra.WebConfig;

import org.junit.Test;

public class WebConfigTest {

	WebConfig web = new WebConfig();
	
	@Test(expected=MissingResourceException.class)
	public void deveGerarExceptionComPropriedadeQueNaoExiste() {
		web.get("asdasd");
	}
	
	@Test(expected=NumberFormatException.class)
	public void deveGerarExceptionQuandoAPropriedadeNaoForDoTipoInteger() {
		web.getAsInteger("prop_string");
	}
	
	@Test(expected=NumberFormatException.class)
	public void deveGerarExceptionQuandoAPropriedadeNaoForDoTipoLong() {
		web.getAsLong("prop_string");
	}
	
	@Test(expected=NumberFormatException.class)
	public void deveGerarExceptionQuandoAPropriedadeNaoForDoTipoDouble() {
		web.getAsDouble("prop_string");
	}

	@Test(expected=NumberFormatException.class)
	public void tentaRecuperarUmValorLongParaInteger() {
		Assert.assertEquals(20534322349994l, web.getAsInteger("prop_long"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveGerarExceptionQuandoAPropriedadeNaoForDoTipoBoolean() {
		web.getAsBoolean("prop_string");
	}
	
	@Test
	public void deveRetornarUmaPropriedadeTipoString() {
		Assert.assertEquals("localhost", web.get("prop_string"));
	}
	
	@Test
	public void deveRetornarUmaPropriedadeTipoStringList() {
		Assert.assertEquals(3, web.getAsStringList("prop_list").size());
		Assert.assertEquals("spring", web.getAsStringList("prop_list").get(1));
	}
	
	@Test
	public void deveRetornarUmaPropriedadeTipoBoolean() {
		Assert.assertTrue(web.getAsBoolean("prop_boolean"));
	}
	
	@Test
	public void deveRetornarUmaPropriedadeTipoBooleanFalse() {
		Assert.assertFalse(web.getAsBoolean("prop_false"));
	}
	
	@Test
	public void deveRetornarUmaPropriedadeTipoInteger() {
		Assert.assertEquals(1234, web.getAsInteger("prop_integer"));
	}
	
	@Test
	public void deveRetornarUmaPropriedadeTipoLong() {
		Assert.assertEquals(20534322349994l, web.getAsLong("prop_long"));
	}
	
}
