package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.dao.VeiculoDAO;
import gerenciamentodefrota.infra.persistence.Transacional;
import gerenciamentodefrota.model.Veiculo;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class VeiculoController {

	private Result result;
	private VeiculoDAO veiculoDAO;
	private Validator validator;
	private CombustivelDAO combustivelDAO;

	public VeiculoController(Result result, VeiculoDAO veiculoDAO,
			Validator validator, CombustivelDAO combustivelDAO) {
		this.result = result;
		this.veiculoDAO = veiculoDAO;
		this.validator = validator;
		this.combustivelDAO = combustivelDAO;
	}

	@Get(value = "/veiculo/novo")
	public void novo() {
		result.include("combustiveis", combustivelDAO.lista());
	}

	@Transacional
	@Post("/veiculo/salvar")
	public void salva(final Veiculo veiculo) {
		validaNovoVeiculo(veiculo);

		veiculoDAO.adiciona(veiculo);
		result.redirectTo(this).lista();
	}
	
	private void validaNovoVeiculo(final Veiculo veiculo) {
		validator.validate(veiculo);
		
		if(veiculoDAO.buscaPorPlaca(veiculo.getPlaca()) != null)
			validator.add(new ValidationMessage("Já existe um veiculo cadastrado com esta placa.", "veiculo.placa"));
		
		validator.onErrorRedirectTo(this).novo();
	}

	@Get(value = "/veiculo/{id}")
	public void editar(Long id) {
		try {
			Veiculo veiculo = veiculoDAO.busca(id);
			
			result.include("veiculo", veiculo);
			result.include("combustiveis", combustivelDAO.lista());
		} catch (Exception e) {
			result.notFound();
		}
	}

	@Transacional
	@Put(value = "/veiculo/{veiculo.id}")
	public void alterar(final Veiculo veiculo) {
		validaEditarVeiculo(veiculo);
		
		veiculoDAO.atualiza(veiculo);
		result.redirectTo(this).lista();
	}

	private void validaEditarVeiculo(final Veiculo veiculo) {
		validator.validate(veiculo);
		
		Veiculo veiculoValida = veiculoDAO.buscaPorPlaca(veiculo.getPlaca());
		
		if (!veiculoValida.equals(veiculo)) {
			if(veiculoDAO.buscaPorPlaca(veiculo.getPlaca()) == null)
				validator.add(new ValidationMessage("Já existe um veiculo cadastrado com esta placa.", "veiculo.placa"));
		}
		
		result.include("combustiveis", combustivelDAO.lista());
		validator.onErrorUsePageOf(this).editar(veiculo.getId());
	}

	@Get("/veiculo")
	public List<Veiculo> lista() {
		try {
			return veiculoDAO.lista();
		} catch (Exception e) {
			return new ArrayList<Veiculo>();
		}
	}

}