package gerenciamentodefrota.infra;

public enum NoticeEnum {

	ERROR("error"),
	SUCCESS("success"),
	INFO("info"),
	WARNING("warning");
	
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
