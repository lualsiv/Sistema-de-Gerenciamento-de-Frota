package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.annotation.Transacional;
import gerenciamentodefrota.dao.FuncionarioDAO;
import gerenciamentodefrota.dao.MotoristaDAO;
import gerenciamentodefrota.model.Motorista;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class MotoristaController {

	private MotoristaDAO motoristaDAO;
	private FuncionarioDAO funcionarioDAO;
	private Result result;
	private Validator validator;

	public MotoristaController(MotoristaDAO motoristaDAO, FuncionarioDAO funcionarioDAO, Validator validator, Result result) {
		this.motoristaDAO = motoristaDAO;
		this.funcionarioDAO = funcionarioDAO;
		this.validator = validator;
		this.result = result;
	}

	@Get
	@Path(value = "/funcionario/{id}/motorista/novo", priority = Path.HIGHEST)
	public void novo(Long id) {
		result.include("funcionario", funcionarioDAO.busca(id));
	}
	
	@Transacional
	@Post("/motorista/salvar")
	public void salva(final Motorista motorista) {
		motorista.setFuncionario(funcionarioDAO.busca(motorista.getFuncionario().getId()));
		
		validator.validate(motorista);
		
		result.include("funcionario", motorista.getFuncionario());
		validator.onErrorUsePageOf(this).novo(motorista.getFuncionario().getId());

		motoristaDAO.adiciona(motorista);
		result.redirectTo(this).lista();
	}
	
	@Get("/motorista")
	public List<Motorista> lista() {
		try {
			return motoristaDAO.lista();
		} catch (Exception e) {
			return new ArrayList<Motorista>();
		}
	}
}
