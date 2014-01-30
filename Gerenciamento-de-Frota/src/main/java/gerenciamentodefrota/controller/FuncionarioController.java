package gerenciamentodefrota.controller;

import gerenciamentodefrota.dao.FuncionarioDAO;
import gerenciamentodefrota.infra.persistence.Pagination;
import gerenciamentodefrota.infra.persistence.Transacional;
import gerenciamentodefrota.infra.view.Notice;
import gerenciamentodefrota.model.Funcionario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

@Resource
public class FuncionarioController {

	private FuncionarioDAO funcionarioDAO;
	private Validator validator;
	private Result result;
	private Notice notice;

	public FuncionarioController(FuncionarioDAO funcionarioDAO, Validator validator, Result result, Notice notice) {
		this.funcionarioDAO = funcionarioDAO;
		this.validator = validator;
		this.result = result;
		this.notice = notice;
	}

	@Get("/funcionario/novo")
	public void novo() {
	}

	@Transacional
	@Post("/funcionario/novo")
	public void salva(final Funcionario funcionario) {
		validaNovoFuncionario(funcionario);

		funcionarioDAO.adiciona(funcionario);
		notice.success("Funcionário cadastrado com sucesso.");
		result.redirectTo(this).lista(null, null, 1);
	}

	private void validaNovoFuncionario(final Funcionario funcionario) {
		validator.validate(funcionario);
		
		if (funcionarioDAO.buscaPorCadastro(funcionario.getCadastro()) != null) {
			validator.add(new ValidationMessage("Já existe um funcionário com este cadastro.", "funcionario.cadastro"));
		}
		
		validator.onErrorRedirectTo(this).novo();
	}
	
	@Get("/funcionario")
	public void lista(String nome, String ordem, Integer pagina) {
		result.include("nome", nome);
		result.include("ordem", ordem);
		result.include("funcionarios", funcionarioDAO.lista(nome, ordem, pagina, Pagination.PAGESIZE));
	}
	
	@Get("/funcionario/busca.json")
	public void buscaPorCadastroJson(String cadastro) {
		try {
			Funcionario funcionario = funcionarioDAO.buscaPorCadastro(cadastro);
			result.use(Results.json()).from(funcionario).serialize();
		} catch (Exception e) {
			result.use(Results.http()).sendError(404);
		}
	}
	
}
