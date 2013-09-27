package gerenciamentodefrota.infra.persistence;

public enum Condition {

	AND(" and "), OR(" or "), NONE("");
	
	private final String text;

	private Condition(final String text) {
        this.text = text;
    }
	
	@Override
    public String toString() {
        return text;
    }
	
}
