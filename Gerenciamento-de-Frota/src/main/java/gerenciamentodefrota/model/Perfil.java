package gerenciamentodefrota.model;

public enum Perfil {

	CONSULTA("consulta"),
	USUARIO("usuário"),
	ADMINISTRADOR("administrador");
	
	private Perfil(final String text) {
        this.text = text;
    }

    private final String text;
	
    @Override
    public String toString() {
        return text;
    }
    
}
