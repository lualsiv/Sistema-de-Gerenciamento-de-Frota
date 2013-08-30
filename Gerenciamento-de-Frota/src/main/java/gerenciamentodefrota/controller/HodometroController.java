package gerenciamentodefrota.controller;

import java.math.BigDecimal;

import gerenciamentodefrota.dao.HodometroDAO;
import gerenciamentodefrota.dao.VeiculoDAO;
import gerenciamentodefrota.model.Hodometro;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class HodometroController {

	private Result result;
	private Validator validator;
	private VeiculoDAO veiculoDAO;
	private HodometroDAO hodometroDAO;

	public HodometroController(Result result, Validator validator, VeiculoDAO veiculoDAO, HodometroDAO hodometroDAO) {
		this.result = result;
		this.validator = validator;
		this.veiculoDAO = veiculoDAO;
		this.hodometroDAO = hodometroDAO;
	}

	@Get("/veiculo/registrarquilometragem")
	public void novoRegistro() {

	}

	@Post("/veiculo/registrarquilometragem")
	public void novoRegistro(Hodometro hodometro) {
		hodometro.setVeiculo(veiculoDAO.buscaPorPlaca(hodometro.getVeiculo().getPlaca()));
		
		if (hodometro.getVeiculo() == null) {
			validator.add(new ValidationMessage("Não existe veículo com esta placa nos registro.","veiculo.placa"));
		}
		
		validator.onErrorUsePageOf(this).novoRegistro();

		BigDecimal numero = hodometroDAO.ultimaQuilometragem(hodometro.getVeiculo());
		
		if (hodometro.getQuilometragem().compareTo(numero) != 1) {
			validator.add(new ValidationMessage("A quilometragem deve ser maior que o registro anterior.","hodometro.quilometragem"));
		}
		
		validator.onErrorUsePageOf(this).novoRegistro();
		
	}

}
