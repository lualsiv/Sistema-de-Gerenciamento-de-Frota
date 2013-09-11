package gerenciamentodefrota.controller;

import gerenciamentodefrota.annotation.Transacional;
import gerenciamentodefrota.dao.FuncionarioDAO;
import gerenciamentodefrota.infra.Notice;
import gerenciamentodefrota.model.Funcionario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
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

	public FuncionarioController(FuncionarioDAO funcionarioDAO,
			Validator validator, Result result, Notice notice) {
		this.funcionarioDAO = funcionarioDAO;
		this.validator = validator;
		this.result = result;
		this.notice = notice;
	}

	@Get
	@Path(value = "/funcionario/novo", priority = Path.HIGHEST)
	public void novo() {
	}

	@Transacional
	@Post("/funcionario/salvar")
	public void salva(final Funcionario funcionario) {
		validaNovoFuncionario(funcionario);

		funcionarioDAO.adiciona(funcionario);

		notice.addSuccess("Funcionário cadastrado com sucesso.");
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
		result.include("funcionarios", funcionarioDAO.lista(nome, ordem, pagina));
	}
	
	@Get("/funcionario/busca.json")
	public void funcionarioJson(String cadastro) {
		try {
			Funcionario funcionario = funcionarioDAO.buscaPorCadastro(cadastro);
			result.use(Results.json()).from(funcionario).serialize();
		} catch (Exception e) {
			result.use(Results.http()).sendError(404);
		}

	}
	
}
