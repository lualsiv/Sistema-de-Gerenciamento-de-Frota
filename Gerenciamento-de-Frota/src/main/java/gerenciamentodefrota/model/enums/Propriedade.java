package gerenciamentodefrota.model.enums;

public enum Propriedade {

	PROPRIO("próprio"),
	ARRENDADO("arrendado");
	
	private Propriedade(final String text) {
        this.text = text;
    }

    private final String text;
	
    @Override
    public String toString() {
        return text;
    }
    
}
