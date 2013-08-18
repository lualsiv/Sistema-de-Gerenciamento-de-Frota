package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.annotation.Transacional;
import gerenciamentodefrota.dao.CombustivelDAO;
import gerenciamentodefrota.dao.VeiculoDAO;
import gerenciamentodefrota.model.Veiculo;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class VeiculoController {

	private Result result;
	private VeiculoDAO veiculoDAO;
	private Validator validator;
	private CombustivelDAO combustivelDAO;

	public VeiculoController(Result result, VeiculoDAO veiculoDAO, Validator validator, CombustivelDAO combustivelDAO) {
		this.result = result;
		this.veiculoDAO = veiculoDAO;
		this.validator = validator;
		this.combustivelDAO = combustivelDAO;
	}
	
	@Get
	@Path(value = "/veiculo/novo", priority = Path.HIGHEST)
	public void novo() {
		result.include("combustiveis", combustivelDAO.lista());
	}
	
	@Transacional
	@Post("/veiculo/salvar")
	public void salva(final Veiculo veiculo) {
		validator.validate(veiculo);
		
		validator.checking(new Validations() {
			{
				that(veiculoDAO.buscaPorPlaca(veiculo.getPlaca()) == null,
						"veiculo.placa",
						"Já existe um veiculo cadastrado com esta placa");
			}
		});

		validator.onErrorRedirectTo(this).novo();

		veiculoDAO.adiciona(veiculo);
		result.redirectTo(this).lista();
	}

	@Get
	@Path(value = "/veiculo/{id}", priority = Path.DEFAULT)
	public void editar(Long id) {
		Veiculo veiculo = veiculoDAO.busca(id);
		
		if (veiculo != null) {
			result.include("veiculo", veiculo);
		} else {
			result.notFound();
		}
		
		result.include("combustiveis", combustivelDAO.lista());
	}
	
	@Transacional
	@Put
	@Path(value = "/veiculo/{veiculo.id}", priority = Path.LOWEST)
	public void alterar(final Veiculo veiculo) {
		validator.validate(veiculo);
		
		Veiculo veiculoValida = veiculoDAO.buscaPorPlaca(veiculo.getPlaca());
		
		if (!veiculoValida.equals(veiculo)) {
			validator.checking(new Validations() {
				{
					that(veiculoDAO.buscaPorPlaca(veiculo.getPlaca()) == null,
							"veiculo.placa",
							"Já existe um veiculo cadastrado com esta placa");
				}
			});
		}
		
		result.include("combustiveis", combustivelDAO.lista());
		validator.onErrorUsePageOf(this).editar(veiculo.getId());
		
		veiculoDAO.atualiza(veiculo);
		result.redirectTo(this).lista();
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