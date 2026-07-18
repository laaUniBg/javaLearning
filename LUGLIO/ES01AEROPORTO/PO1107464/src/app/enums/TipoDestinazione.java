package app.enums;

public enum TipoDestinazione {
	ITALIA("MXP", "BGY"),
    MAROCCO("RAK", "CMN"),
    SPAGNA("BAR", "MAD");
	
	private String[] codiciAeroporti;
	
	private TipoDestinazione(String... codiciAeroporti) {
		this.codiciAeroporti = codiciAeroporti;
	}
	
	public static TipoDestinazione getPaeseDestinazione(String codiceVolo) {
		String codiceAeroporto = codiceVolo.substring(0, 0+3);
		
		for(TipoDestinazione thisPaese : TipoDestinazione.values()) {
			for(String thisCodiceAeroporto : thisPaese.codiciAeroporti) {
				boolean isTrovato = thisCodiceAeroporto.equalsIgnoreCase(codiceAeroporto);
				if(!isTrovato) continue;
				return thisPaese;
			}
		}
		
		return null;
	}
}
