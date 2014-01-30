package gerenciamentodefrota.controller;

import java.io.IOException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import gerenciamentodefrota.dao.ArquivoDAO;
import gerenciamentodefrota.infra.persistence.Transacional;
import gerenciamentodefrota.model.Arquivo;

@Resource
public class ArquivoController {

	private ArquivoDAO arquivoDAO;
	private Result result;

	public ArquivoController(ArquivoDAO arquivoDAO, Result result) {
		this.arquivoDAO = arquivoDAO;
		this.result = result;
	}

	@Get("/arquivo/novo")
	public void novo() {
	}

	@Transacional
	@Post("/arquivo/novo")
	public void novo(UploadedFile uploaded) throws IOException {
		if (uploaded != null) {
			Arquivo arquivo = new Arquivo(uploaded);
			arquivoDAO.adiciona(arquivo);
			result.redirectTo(this).lista();
		}
		
		result.redirectTo(this).novo();
	}
	
	@Get("/arquivo/{id}")
	public Download busca(Long id) {
		return arquivoDAO.download(id);
	}
	
	@Get("/arquivo")
	private void lista() {
	}
	
}
