package gerenciamentodefrota.infra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class Notice implements Serializable {
	
	private static final long serialVersionUID = 196531140096146750L;
	private List<NoticeItem> notices = new ArrayList<NoticeItem>();
	
	public Notice() {
		this.notices = new ArrayList<NoticeItem>();
	}
	
	public void clearNotices() {
		this.notices.clear();
	}
	
	public void addNotice(String mensagem, NoticeEnum tipo) {
		NoticeItem n = new NoticeItem(mensagem, tipo);
		this.notices.add(n);
	}
	
	public void addSuccess(String mensagem) {
		this.addNotice(mensagem, NoticeEnum.SUCCESS);
	}
	
	public void addError(String mensagem) {
		this.addNotice(mensagem, NoticeEnum.ERROR);
	}
	
	public void addInfo(String mensagem) {
		this.addNotice(mensagem, NoticeEnum.INFO);
	}
	
	public void addWarning(String mensagem) {
		this.addNotice(mensagem, NoticeEnum.WARNING);
	}
	
	public List<NoticeItem> getNotices() {
		return notices;
	}
	
}
