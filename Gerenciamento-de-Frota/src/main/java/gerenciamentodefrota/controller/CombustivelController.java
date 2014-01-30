package gerenciamentodefrota.controller;

import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.infra.persistence.Transacional;
import gerenciamentodefrota.infra.view.Notice;
import gerenciamentodefrota.model.Combustivel;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class CombustivelController {

	private CombustivelDAO combustivelDAO;
	private Validator validator;
	private Result result;
	private Notice notice;

	public CombustivelController(CombustivelDAO combustivelDAO, Validator validator, Result result, Notice notice) {
		this.combustivelDAO = combustivelDAO;
		this.validator = validator;
		this.result = result;
		this.notice = notice;
	}

	@Get
	@Path(value = "/combustivel/novo", priority = Path.HIGHEST)
	public void novo() {
	}
	
	@Transacional
	@Post("/combustivel/salvar")
	public void salva(final Combustivel combustivel) {
		validator.validate(combustivel);
		validator.onErrorRedirectTo(this).novo();
		
		combustivelDAO.adiciona(combustivel);
		notice.success("Combustível cadastrado com sucesso.");
		result.redirectTo(this).lista(1);
	}

	@Get
	@Path(value = "/combustivel/{id}")
	public void editar(Long id) {
		try {
			Combustivel combustivel = combustivelDAO.busca(id);
			result.include("combustivel", combustivel);
		} catch (Exception e) {
			result.notFound();
		}
	}
	
	@Transacional
	@Put
	@Path(value = "/combustivel/{combustivel.id}")
	public void alterar(final Combustivel combustivel) {
		validator.validate(combustivel);
		validator.onErrorUsePageOf(this).editar(combustivel.getId());
		
		combustivelDAO.atualiza(combustivel);
		notice.success("Combustível alterado com sucesso.");
		result.redirectTo(this).lista(1);
	}
	
	@Get("/combustivel")
	public void lista(Integer pagina) {
		result.include("combustiveis", combustivelDAO.lista(pagina));
	}

}
