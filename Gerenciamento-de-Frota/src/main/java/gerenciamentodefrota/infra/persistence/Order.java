package gerenciamentodefrota.infra.persistence;

public enum Order {

	ASC(" asc "), DESC(" desc ");

	private final String text;

	private Order(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
