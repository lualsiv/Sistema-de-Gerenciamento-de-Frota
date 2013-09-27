package gerenciamentodefrota.infra.persistence;

public class Select {
	
	private Object value;
	private Object text;
	
	public Select(Object value, Object text) {
		this.value = value;
		this.text = text;
	}

	public Object getValue() {
		return value;
	}

	public Object getText() {
		return text;
	}
	
}
