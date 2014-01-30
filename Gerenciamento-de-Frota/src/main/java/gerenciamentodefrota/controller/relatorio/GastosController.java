package gerenciamentodefrota.controller.relatorio;

import gerenciamentodefrota.infra.vraptor.Module;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
@Module("relatorio")
public class GastosController {

	@Get("relatorio/gastos/lavagem")
	public void lavagem() {
		
	}
	
}
