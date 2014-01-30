package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.dao.FuncionarioDAO;
import gerenciamentodefrota.dao.MotoristaDAO;
import gerenciamentodefrota.infra.persistence.Transacional;
import gerenciamentodefrota.infra.view.Notice;
import gerenciamentodefrota.model.Motorista;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class MotoristaController {

	private MotoristaDAO motoristaDAO;
	private FuncionarioDAO funcionarioDAO;
	private Result result;
	private Validator validator;
	private Notice notice;

	public MotoristaController(MotoristaDAO motoristaDAO, FuncionarioDAO funcionarioDAO, Validator validator, Result result, Notice notice) {
		this.motoristaDAO = motoristaDAO;
		this.funcionarioDAO = funcionarioDAO;
		this.validator = validator;
		this.result = result;
		this.notice = notice;
	}

	@Get("/motorista/novo")
	public void novo() {
	}
	
	@Transacional
	@Post("/motorista/novo")
	public void salva(Motorista motorista) {
		validaNovoMotorista(motorista);
		
		motoristaDAO.adiciona(motorista);
		notice.success("Motorista cadastrado com sucesso.");
		result.redirectTo(this).lista();
	}
	
	private void validaNovoMotorista(Motorista motorista) {
		Motorista motoristaJaCadastrado = motoristaDAO.buscaPorCadastro(motorista.getFuncionario().getCadastro());
		
		if (motoristaJaCadastrado != null)
			validator.add(new ValidationMessage("Já existe um cdastro de motorista para este funcionario.", "funcionario"));
		
		motorista.setFuncionario(funcionarioDAO.buscaPorCadastro(motorista.getFuncionario().getCadastro()));
		
		if(motorista.getFuncionario() == null)
			validator.add(new ValidationMessage("Funcionario não encontrado.", "funcionario"));
		
		validator.validate(motorista);
		
		result.include("funcionario", motorista.getFuncionario());
		validator.onErrorUsePageOf(this).novo();
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
