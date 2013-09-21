package gerenciamentodefrota.test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.JSR303MockValidator;
import gerenciamentodefrota.model.Veiculo;
import gerenciamentodefrota.test.utils.ValidatorUtils;

public class VeiculoTest {

	private Validator validator = new JSR303MockValidator();
	
	@Test
	public void deveEncontrarErroNoAtributoPlacaQuandoInformarNumeroInvalido() {
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("sd2-23w2");
		
		validator.validate(veiculo);
		
		assertTrue(ValidatorUtils.containsCategory(validator, "placa"));
	}
	
	@Test
	public void naoDeveDeveEncontrarErroNoAtributoPlacaQuandoInformarNumeroValido() {
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("sdw-4563");
		
		validator.validate(veiculo);
		
		assertFalse(ValidatorUtils.containsCategory(validator, "placa"));
	}
	
}
