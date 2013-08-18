package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.annotation.Transacional;
import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.model.Combustivel;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

@Resource
public class CombustivelController {

	private CombustivelDAO combustivelDAO;
	private Validator validator;
	private Result result;

	public CombustivelController(CombustivelDAO combustivelDAO, Validator validator, Result result) {
		this.combustivelDAO = combustivelDAO;
		this.validator = validator;
		this.result = result;
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
		result.redirectTo(this).lista();
	}
	
	@Get("/combustivel")
	public List<Combustivel> lista() {
		try {
			return combustivelDAO.lista();
		} catch (Exception e) {
			return new ArrayList<Combustivel>();
		}
	}

}
