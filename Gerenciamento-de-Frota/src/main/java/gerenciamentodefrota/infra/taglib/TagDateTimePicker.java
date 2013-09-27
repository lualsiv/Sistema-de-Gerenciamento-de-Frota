package gerenciamentodefrota.infra.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TagDateTimePicker extends TagSupport {

	private static final long serialVersionUID = -1692338643816293484L;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.append(this.getScript());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	private String getScript() {
		String script;
		
		script = 	"<script type=\"text/javascript\">" + 
					"$(function() {" +
					"$(\"#ID#\").datetimepicker();" + 
					"});" + 
					"</script>";
		
		return script.replace("#ID#", this.id);
	}

}
