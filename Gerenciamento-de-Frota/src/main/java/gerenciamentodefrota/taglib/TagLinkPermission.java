package gerenciamentodefrota.taglib;

import gerenciamentodefrota.model.Perfil;
import gerenciamentodefrota.model.UsuarioSession;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
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
		getUsuarioSession();
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
		try {
			JspWriter out = pageContext.getOut();
			
			if (usuarioSession != null) {
				if (usuarioSession.isLogado() && hasAccess()) {
					out.println("<a href=\"" + link + "\">" + titulo + "</a>");
				} else {
					if (exibir)
						out.println("<span>" + titulo + "</span>");
				}
			}
			else {
				if (exibir)
					out.println("<span>" + titulo + "</span>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
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
