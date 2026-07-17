package app.enums;

public enum TipoDocumento {
	CARTAIDENTITA, 
	PATENTE,
	VISTO;

	@Override
	public String toString() {
		if(this == CARTAIDENTITA) {
			return "carta d'identita";
		};
		return this.name();
	}
};
