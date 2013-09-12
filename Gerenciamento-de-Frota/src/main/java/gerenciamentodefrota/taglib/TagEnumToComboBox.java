package gerenciamentodefrota.taglib;

import gerenciamentodefrota.util.StringUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TagEnumToComboBox extends TagSupport {
	
	private static final long serialVersionUID = -3477812707352521516L;
	
	@SuppressWarnings({ "rawtypes" })
	private Enum valor;
	
	@SuppressWarnings({ "rawtypes" })
	private Enum[] opcoes;

	private String name;
	private String selecione;
	
	public void setSelecione(String selecione) {
		this.selecione = selecione;
	}

	public void setClasse(Class<?> clazz) {
		this.opcoes = (Enum[]) clazz.getEnumConstants();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("rawtypes")
	public void setValor(Enum valor) {
		this.valor = valor;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			out.println("<select name=\"" + name + "\">");

			if(StringUtil.notNullOrEmpty(selecione)) {
				String sel = valor == null ? " selected " : "";
				out.println("<option value=\"\" " + sel + " >" + selecione + "</option>");
			}
			
			for (Enum item : opcoes) {
				String sel = item.equals(valor) ? " selected " : "";
				out.println("<option value=\"" + item.name() + "\" " + sel + " >" + item.toString() + "</option>");
			}
			out.println("</select>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}	
	
}
