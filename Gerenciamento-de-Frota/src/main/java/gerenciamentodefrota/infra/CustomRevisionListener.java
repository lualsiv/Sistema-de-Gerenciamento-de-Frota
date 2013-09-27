package gerenciamentodefrota.infra;

import org.hibernate.envers.RevisionListener;

import br.com.caelum.vraptor.ioc.spring.VRaptorRequestHolder;

public class CustomRevisionListener implements RevisionListener {

	private UsuarioSession usuarioSession;
	private String login = "?";
	
	@Override
	public void newRevision(Object revisionEntity) {
		try {
			usuarioSession = (UsuarioSession) VRaptorRequestHolder.currentRequest().getRequest().getSession().getAttribute("usuarioSession");
			login = usuarioSession.getUsuario().getLogin();
		} catch (Exception e) {
		}
		
		((CustomRevisionEntity) revisionEntity).setLogin(login);
	}

}
