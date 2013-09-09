package gerenciamentodefrota.model;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class UsuarioSession implements Serializable {

	private static final long serialVersionUID = 906625873661089523L;

	private Usuario usuario = null;
	
	public void login(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isLogado() {
		return usuario != null;
	}
	
	public void logoff() {
		this.usuario = null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
}
