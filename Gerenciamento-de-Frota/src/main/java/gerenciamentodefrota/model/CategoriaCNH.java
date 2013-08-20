package gerenciamentodefrota.model;

public enum CategoriaCNH {

	A("A"),
	B("B"),
	AB("AB"),
	C("C"),
	AC("AC"),
	D("D"),
	AD("AD"),
	E("E"),
	AE("AE");
	
	private CategoriaCNH(final String text) {
		this.text = text;
	}
	
	private final String text;
	
    @Override
    public String toString() {
        return text;
    }
    
}
