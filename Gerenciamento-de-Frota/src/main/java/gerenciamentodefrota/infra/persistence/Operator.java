package gerenciamentodefrota.infra.persistence;

public enum Operator {

	EQUALS("#FIELD# = #VALUE1# "),
	NOTEQUALS("#FIELD# <> #VALUE1# "),
	GREATERTHAN("#FIELD# > #VALUE1# "),
	GREATEROREQUALS("#FIELD# >= #VALUE1# "),
	LESSTHAN("#FIELD# < #VALUE1# "),
	LESSOREQUALS("#FIELD# <= #VALUE1# "),
	IN("#FIELD# in #LIST# "),
	NOTIN("#FIELD# not in #LIST# "),
	ISNULL("#FIELD# is null "),
	ISNOTNULL("#FIELD# is not null "),
	ISTRUE("#FIELD# is true "),
	ISFALSE("#FIELD# is false "),
	LIKE("#FIELD# like #VALUE1# "),
	NOTLIKE("#FIELD# not like #VALUE1# "),
	BETWEEN("#FIELD# between #VALUE1# and #VALUE2# "),
	NOTBETWEEN("#FIELD# not between #VALUE1# and #VALUE2# ");
	
	private final String text;

	private Operator(final String text) {
        this.text = text;
    }
	
	@Override
    public String toString() {
        return text;
    }
	
}
