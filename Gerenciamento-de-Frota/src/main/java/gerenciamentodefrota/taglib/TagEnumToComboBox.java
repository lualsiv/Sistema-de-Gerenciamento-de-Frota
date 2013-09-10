package gerenciamentodefrota.taglib;

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
			this.opcoes = valor.getClass().getEnumConstants();
			
			out.println("<select name=\"" + name + "\">");
			for (Enum item : opcoes) {
				if (item.equals(valor))
					out.println("<option value=\"" + item.name() + "\" selected >" + item.toString() + "</option>");
				else
					out.println("<option value=\"" + item.name() + "\" >" + item.toString() + "</option>");
			}
			out.println("</select>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}	
	
}
