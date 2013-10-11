package gerenciamentodefrota.controller.relatorio;

import gerenciamentodefrota.infra.annotation.Modulo;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
@Modulo(nome="relatorio")
public class GastosController {

	@Get("relatorio/gastos/lavagem")
	public void lavagem() {
		
	}
	
}
