package gerenciamentodefrota.taglib;

import gerenciamentodefrota.infra.Notice;
import gerenciamentodefrota.infra.NoticeItem;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TagNotice extends TagSupport {

	private static final long serialVersionUID = -8255381105907994435L;

	private Notice notices;

	public Notice getNotices() {
		return notices;
	}

	public void setNotices(Notice notices) {
		this.notices = notices;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			
			for (NoticeItem notice : notices.getNotices()) {
				out.println(String.format("<div class=\"%s\"> %s </div>", notice.getTipo().toString(), notice.getMensagem()));
			}
			
			notices.clearNotices();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

}
