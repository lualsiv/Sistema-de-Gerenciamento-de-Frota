package gerenciamentodefrota.infra.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagPaginacao extends TagSupport {

	private static final long serialVersionUID = -664764194337343319L;

	private String link;
	private Integer paginaAtual;
	private Integer totalPaginas;
	
	@Override
	public int doStartTag() throws JspException {
		for (Integer i = 1; i <= this.totalPaginas; i++) {
			if (i == paginaAtual) {
				print("<span>" + i + "</span>");
			} else {
				print("<a href='" + link.replaceFirst("#", i.toString()) + "'>" + i + "</a>");
			}
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(Integer paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

	public Integer getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(Integer totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

}
