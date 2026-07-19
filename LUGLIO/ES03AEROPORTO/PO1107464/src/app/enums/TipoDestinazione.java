package app.enums;

import app.exeptions.CodiceVoloNonValido;

public enum TipoDestinazione {
	ITALIA("MXP", "BGY"),
	SPAGNA("BAR", "VAL"),
	MAROCCO("CMN", "RAK");
	
	final String[] codiciAeroporti;
	
	private TipoDestinazione(String... codiciAeroporti) {
		this.codiciAeroporti = codiciAeroporti;
	}
	
	public static TipoDestinazione getPaeseFromCodiceVolo(String codiceVolo) {
		if(codiceVolo == null || codiceVolo.isBlank()) {
			throw new CodiceVoloNonValido("SETCODICEVOLO: RUNTIME EXCEPTION");
		}
		
		try {
			final String thisCodiceAeroporto = codiceVolo.substring(0, 0+3);
		} catch(IndexOutOfBoundsException e) {
			throw new CodiceVoloNonValido("SETCODICEVOLO: RUNTIME EXCEPTION");
		};
		
		for(TipoDestinazione thisPaese : TipoDestinazione.values()) {
			for(String thisCodiceAeroporto : thisPaese.codiciAeroporti) {
				boolean isFound = thisCodiceAeroporto.equalsIgnoreCase(codiceVolo);
				if(!isFound) continue;
				return thisPaese;
			}
		};
		
		return null;
	}
}
