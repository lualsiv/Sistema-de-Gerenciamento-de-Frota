package gerenciamentodefrota.test.model;

import gerenciamentodefrota.model.Veiculo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VeiculoTest {

	private Veiculo veiculo;

	@Before
	public void setup() {
		veiculo = new Veiculo();
	}

	@After
	public void finalize() {
		veiculo = null;
	}
	
	@Test
	public void tentaSetarPlacaInvalidaNoVeiculo() {
		veiculo.setPlaca("s2w-2322");
		Assert.assertNull(veiculo.getPlaca());
	}

	@Test
	public void tentaSetarPlacaComValorNulo() {
		veiculo.setPlaca(null);
		Assert.assertNull(veiculo.getPlaca());
	}

	@Test
	public void tentaSetarPlacaComValorEmBranco() {
		veiculo.setPlaca("");
		Assert.assertNull(veiculo.getPlaca());
	}

	@Test
	public void tentaSetarPlacaValidaNoVeiculo() {
		veiculo.setPlaca("SEW-2322");
		Assert.assertEquals(veiculo.getPlaca(), "SEW-2322");
	}

}
