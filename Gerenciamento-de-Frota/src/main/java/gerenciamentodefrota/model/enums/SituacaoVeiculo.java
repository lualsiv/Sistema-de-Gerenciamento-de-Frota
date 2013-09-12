package gerenciamentodefrota.model.enums;

public enum SituacaoVeiculo {

	EM_USO("em uso"),
	VENDIDO("vendido"),
	LEILOADO("leiloado"),
	DOADO("doado");
	
	private SituacaoVeiculo(final String text) {
        this.text = text;
    }
	
	private final String text;
    
    @Override
    public String toString() {
        return text;
    }
    
}
