package gerenciamentodefrota.controller;

import java.util.ArrayList;
import java.util.List;

import gerenciamentodefrota.annotation.Transacional;
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
	private VeiculoDAO dao;
	private Validator validator;

	public VeiculoController(Result result, VeiculoDAO dao, Validator validator) {
		this.result = result;
		this.dao = dao;
		this.validator = validator;
	}

	@Get
	@Path(value = "/veiculo/novo", priority = Path.HIGHEST)
	public void novo() {

	}

	@Get
	@Path(value = "/veiculo/{id}", priority = Path.LOWEST)
	public void editar(Long id) {
		Veiculo veiculo = dao.busca(id);

		if (veiculo != null) {
			result.include("veiculo", veiculo);
		} else {
			result.notFound();
		}
	}

	@Get("/veiculo/alterar")
	public void editar(Veiculo veiculo) {
		System.out.println("Voltou pra correção");

		if (veiculo.getId() == null)
			result.redirectTo(this).novo();

		result.include("veiculo", veiculo);
	}

	@Transacional
	@Post("/veiculo/salvar")
	public void salva(final Veiculo veiculo) {
		validator.validate(veiculo);

		validator.checking(new Validations() {
			{
				that(dao.buscaPorPlaca(veiculo.getPlaca()) == null,
						"veiculo.placa",
						"Já existe um veiculo cadastrado com esta placa");
			}
		});

		validator.onErrorRedirectTo(this).novo();

		dao.atualiza(veiculo);
		result.redirectTo(this).lista();
	}

	@Transacional
	@Put
	@Path(value = "/veiculo/alterar", priority = Path.LOWEST)
	public void alterar(final Veiculo veiculo) {
		validator.validate(veiculo);

		Veiculo veiculoValida = dao.buscaPorPlaca(veiculo.getPlaca());

		if (!veiculoValida.getId().equals(veiculo.getId())) {
			validator.checking(new Validations() {
				{
					that(dao.buscaPorPlaca(veiculo.getPlaca()) == null,
							"veiculo.placa",
							"Já existe um veiculo cadastrado com esta placa");
				}
			});
		}

		validator.onErrorRedirectTo(this).editar(veiculo);

		dao.atualiza(veiculo);
		result.redirectTo(this).lista();

		System.out.println("Método alterar");
	}

	@Get("/veiculo")
	public List<Veiculo> lista() {
		try {
			return dao.lista();
		} catch (Exception e) {
			return new ArrayList<Veiculo>();
		}
	}

}