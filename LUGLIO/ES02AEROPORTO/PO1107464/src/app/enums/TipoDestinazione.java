package app.enums;

public enum TipoDestinazione {
	ITALIA("MXP", "BGY"),
	SPAGNA("BAR", "VAL"),
	MAROCCO("RAK", "CMN");
	
	private String[] codiciAeroporti;
	
	private TipoDestinazione(String... codiciAeroporti) {
		this.codiciAeroporti = codiciAeroporti;
	};
	
	public static TipoDestinazione checkCodiceCorrectAndReturnStato(String codiceVoloComplesso) {
		if(codiceVoloComplesso == null) return null;
		
		try {
			String thisCodiceAeroporto = codiceVoloComplesso.substring(0, 0+3);
		} catch(IndexOutOfBoundsException e) {
			return null;
		};
		
		for(TipoDestinazione thisPaese : TipoDestinazione.values()) {
			for(String thisCodiceAeroporto : thisPaese.codiciAeroporti) {
				boolean isEqual = thisCodiceAeroporto.equalsIgnoreCase(codiceVoloComplesso);
				if(!isEqual) continue;
				return thisPaese;
			};
		};
		
		return null;
	}
}
