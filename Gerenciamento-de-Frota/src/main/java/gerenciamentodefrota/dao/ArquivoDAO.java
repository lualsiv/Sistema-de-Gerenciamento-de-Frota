package gerenciamentodefrota.dao;

import java.util.List;

import gerenciamentodefrota.model.Arquivo;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.interceptor.download.ByteArrayDownload;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ArquivoDAO {
	
	private DAO<Arquivo, Long> dao;
	
	public ArquivoDAO(EntityManager em) {
		this.dao = new DAO<Arquivo, Long>(em, Arquivo.class);
	}
	
	public void adiciona(Arquivo arquivo) {
		dao.create(arquivo);
	}
	
	public void atualiza(Arquivo arquivo) {
		dao.update(arquivo);
	}
	
	public Arquivo busca(Long id) {
		return dao.find(id);
	}
	
	public List<Arquivo> lista() {
		return dao.list();
	}
	
	public Download download(Long id) {
		return download(busca(id));
	}
	
	public Download download(Arquivo arquivo) {
		return new ByteArrayDownload(arquivo.getConteudo(),
									 arquivo.getContentType(), 
									 arquivo.getNome());
	}
	
}
