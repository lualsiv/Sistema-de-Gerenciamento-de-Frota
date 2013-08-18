package gerenciamentodefrota.infra;

public enum NoticeEnum {

	ERROR("notice-error"),
	SUCCESS("notice-success"),
	INFO("notice-info"),
	WARNING("notice-warning");
	
	private NoticeEnum(final String text) {
        this.text = text;
    }
	
	private String text;

	public String getText() {
		return text;
	}

	@Override
    public String toString() {
        return text;
    }
    
}
