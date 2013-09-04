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
import br.com.caelum.vraptor.validator.Validations;

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

	@Get
	@Path(value = "/funcionario/novo", priority = Path.HIGHEST)
	public void novo() {
	}

	@Transacional
	@Post("/funcionario/salvar")
	public void salva(final Funcionario funcionario) {
		validator.validate(funcionario);

		validator.checking(new Validations() {
			{
				that(funcionarioDAO.buscaPorCadastro(funcionario.getCadastro()) == null,
						"funcionario.cadastro",
						"Já existe um funcionário com este cadastro");
			}
		});
		
		validator.onErrorRedirectTo(this).novo();

		funcionarioDAO.adiciona(funcionario);
		
		notice.addSuccess("Funcionário cadastrado com sucesso.");
		result.redirectTo(this).lista(null,null, 1);
	}
	
	@Get("/funcionario")
	public void lista(String nome, String ordem, Integer pagina) {
		result.include("nome", nome);
		result.include("ordem", ordem);
		
		result.include("funcionarios", funcionarioDAO.lista(nome, ordem, pagina, 5));
	}
	
}
