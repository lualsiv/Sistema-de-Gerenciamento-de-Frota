package gerenciamentodefrota.infra.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagPaginacao extends TagSupport {

	private static final long serialVersionUID = -664764194337343319L;

	private String link;
	private Integer paginaAtual;
	private Integer totalPaginas;

	private int de() {
		if (paginaAtual > 5)
			return paginaAtual - 5;
		else
			return 1;
	}
	
	private int ate() {
		if ( (paginaAtual) < (totalPaginas - 5) )
			return paginaAtual + 5;
		else
			return totalPaginas;
	}
	
	@Override
	public int doStartTag() throws JspException {
		if (de() > 1)
			print("<a href='" + link.replaceFirst("#", "1") + "'>1</a>");
		
		for (Integer i = de(); i <= ate(); i++) {
			if (i.equals(paginaAtual))
				print("<span>" + i + "</span>");
			else
				print("<a href='" + link.replaceFirst("#", i.toString()) + "'>" + i + "</a>");
		}

		if (ate() < totalPaginas)
			print("<a href='" + link.replaceFirst("#", totalPaginas.toString()) + "'>" + totalPaginas.toString() + "</a>");
		
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
