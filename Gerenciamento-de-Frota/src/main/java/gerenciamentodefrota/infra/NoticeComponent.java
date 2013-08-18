package gerenciamentodefrota.infra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class NoticeComponent implements Serializable {

	private static final long serialVersionUID = 196531140096146750L;
	private List<Notice> notices = new ArrayList<Notice>();

	public NoticeComponent() {
		super();
		this.notices = new ArrayList<Notice>();
	}

	public void clearNotices() {
		this.notices.clear();
	}

	public void addNotice(String mensagem, NoticeEnum tipo) {
		Notice n = new Notice(mensagem, tipo);
		this.notices.add(n);
	}

	public void addSuccess(String mensagem) {
		this.addNotice(mensagem, NoticeEnum.SUCCESS);
	}
	
	public List<Notice> getNotices() {
		return notices;
	}

}
