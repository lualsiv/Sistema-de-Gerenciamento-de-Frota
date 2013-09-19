package gerenciamentodefrota.taglib;

import gerenciamentodefrota.infra.Notice;
import gerenciamentodefrota.infra.NoticeItem;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagNotice extends TagSupport {

	private static final long serialVersionUID = -8255381105907994435L;

	private Notice notices;

	public Notice getNotices() {
		return notices;
	}

	public void getNoticesFromSession() {
		try {
			HttpSession session = pageContext.getSession();
			notices = (Notice) session.getAttribute("notice");
		} catch (Exception e) {
			notices = null;
		}
	}

	private void print(String string) {
		try {
			pageContext.getOut().print(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int doStartTag() throws JspException {
		getNoticesFromSession();

		if (notices != null) {
			for (NoticeItem notice : notices.getNotices()) {
				print(String.format("<div class=\"%s\"> %s </div>", notice.getTipo().toString(), notice.getMensagem()));
			}
		}

		notices.clear();

		return SKIP_BODY;
	}

}
