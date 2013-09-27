package gerenciamentodefrota.infra.taglib;

import gerenciamentodefrota.infra.UsuarioSession;
import gerenciamentodefrota.model.enums.Perfil;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagLinkPermission extends TagSupport {

	private static final long serialVersionUID = 7427251828658402481L;
	private List<Perfil> perfis = new ArrayList<Perfil>();
	private String link;
	private String titulo;
	private Boolean exibir = false;
	private UsuarioSession usuarioSession;
	
	public TagLinkPermission() {
		super();
	}

	private void getUsuarioSession() {
		try {
			HttpSession session = pageContext.getSession();
			usuarioSession = (UsuarioSession) session.getAttribute("usuarioSession");
		} catch (Exception e) {
			usuarioSession = null;
		}
	}
	
	@Override
	public int doStartTag() throws JspException {
		getUsuarioSession();
		
		if (usuarioSession != null) {
			if (usuarioSession.isLogado() && hasAccess()) {
				print("<a href=\"" + link + "\">" + titulo + "</a>");
			} else {
				if (exibir)
					print("<span>" + titulo + "</span>");
			}
		} else {
			if (exibir)
				print("<span>" + titulo + "</span>");
		}
			
		return SKIP_BODY;
	}

	private void print(String string) {
		try {
			pageContext.getOut().print(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPerfis(String perfis) {
		String[] lista = perfis.split(",");
		for (String string : lista) {
			try {
				this.perfis.add(createEnumPerfil(string));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private Perfil createEnumPerfil(String perfil) {
		return EnumType.valueOf(Perfil.class, perfil.trim());
	}
	
	public void setLink(String link) {
		this.link = link;
	}

	public void setExibir(Boolean exibir) {
		this.exibir = exibir;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private Boolean hasAccess() {
		return perfis.contains(usuarioSession.getUsuario().getPerfil());
	}
}
