package gerenciamentodefrota.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.annotation.Permission;
import gerenciamentodefrota.annotation.Transacional;
import gerenciamentodefrota.dao.HodometroDAO;
import gerenciamentodefrota.dao.VeiculoDAO;
import gerenciamentodefrota.model.Hodometro;
import gerenciamentodefrota.model.Perfil;
import gerenciamentodefrota.model.UsuarioSession;
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
	private UsuarioSession usuarioSession;

	public HodometroController(Result result, Validator validator, VeiculoDAO veiculoDAO, HodometroDAO hodometroDAO, UsuarioSession usuarioSession) {
		this.result = result;
		this.validator = validator;
		this.veiculoDAO = veiculoDAO;
		this.hodometroDAO = hodometroDAO;
		this.usuarioSession = usuarioSession;
	}
	
	@Permission({Perfil.ADMINISTRADOR, Perfil.USUARIO})
	@Get("/veiculo/registrarquilometragem")
	public void novoRegistro() {

	}

	@Transacional
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
		
		hodometro.setFuncionario(usuarioSession.getUsuario().getFuncionario());
		hodometroDAO.adiciona(hodometro);
		result.redirectTo(this).lista();		
	}
	
	@Get("/hodometro")
	public List<Hodometro> lista() {
		try {
			return hodometroDAO.lista();
		} catch (Exception e) {
			return new ArrayList<Hodometro>();
		}
	}
	
}
