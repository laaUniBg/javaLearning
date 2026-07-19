package app.enums;

public enum TipoDestinazione {
	ITALIA("MXP", "BGY"),
	SPAGNA("BAR", "VAL"),
	MAROCCO("CMN", "RAK");
	
	final String[] codiciAeroporti;
	
	private TipoDestinazione(String... codiciAeroporti) {
		this.codiciAeroporti = codiciAeroporti;
	}
	
	public static TipoDestinazione getPaeseFromCodiceVolo(String codiceVolo) {
		if(codiceVolo == null || codiceVolo.isBlank()) return null;
		
		String codiceVoloPrefisso;
		
		try {
			codiceVoloPrefisso = codiceVolo.substring(0, 0+3);
		} catch(IndexOutOfBoundsException e) {
			return null;
		};
		
		for(TipoDestinazione thisPaese : TipoDestinazione.values()) {
			for(String thisCodiceAeroporto : thisPaese.codiciAeroporti) {
				boolean isFound = thisCodiceAeroporto.equalsIgnoreCase(codiceVoloPrefisso);
				if(!isFound) continue;
				return thisPaese;
			}
		};
		
		return null;
	}
}
