package gerenciamentodefrota.infra.audit;

import javax.servlet.http.HttpSession;

import gerenciamentodefrota.infra.UsuarioSession;

import org.hibernate.envers.RevisionListener;

import br.com.caelum.vraptor.ioc.spring.VRaptorRequestHolder;

public class CustomRevisionListener implements RevisionListener {

	private String login;
	private UsuarioSession usuarioSession;

	@Override
	public void newRevision(Object revisionEntity) {
		usuarioSession = getUsuarioSession();
		if (usuarioSession != null) {
			login = usuarioSession.getUsuario().getLogin();
			CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
			customRevisionEntity.setLogin(login);
		}
	}

	private UsuarioSession getUsuarioSession() {
		try {
			return (UsuarioSession) getSession().getAttribute("usuarioSession");
		} catch (Exception e) {
			return null;
		}
	}

	private HttpSession getSession() {
		return VRaptorRequestHolder.currentRequest().getRequest().getSession();
	}

}
