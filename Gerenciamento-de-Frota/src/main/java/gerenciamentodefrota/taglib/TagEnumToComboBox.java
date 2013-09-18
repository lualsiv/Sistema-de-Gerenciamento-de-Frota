package gerenciamentodefrota.taglib;

import gerenciamentodefrota.util.StringUtil;

import javax.servlet.jsp.JspException;
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
	
	private void print(String string) {
		try {
			pageContext.getOut().print(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int doStartTag() throws JspException {
		print("<select name=\"" + name + "\">");

		if (StringUtil.notNullOrEmpty(selecione)) {
			String sel = valor == null ? " selected " : "";
			print("<option value=\"\" " + sel + " >" + selecione + "</option>");
		}

		for (Enum item : opcoes) {
			String sel = item.equals(valor) ? " selected " : "";
			print("<option value=\"" + item.name() + "\" " + sel + " >" + item.toString() + "</option>");
		}
		print("</select>");

		return SKIP_BODY;
	}	
	
}
