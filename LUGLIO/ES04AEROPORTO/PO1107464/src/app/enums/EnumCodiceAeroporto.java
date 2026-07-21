package app.enums;

public enum EnumCodiceAeroporto {
	ITALIA(false, "MXP", "BGY"),
	SPAGNA(false, "BRC", "VLC"),
	MAROCCO(true, "RAK", "CMN");
	
	final public boolean needsPassport;
	final public String[] codiciAeroporto;
	
	private EnumCodiceAeroporto(boolean needsPassport, String... codiciAeroporto) {
		this.needsPassport = needsPassport;
		this.codiciAeroporto = codiciAeroporto;
	}
	
	public static EnumCodiceAeroporto checkCodiceVoloAndReturnStato(String codiceVolo) {
		if(codiceVolo == null) return null;
		if(codiceVolo.isBlank()) return null;
		if(codiceVolo.length() < 3) return null;
		
		for(EnumCodiceAeroporto thisStato : EnumCodiceAeroporto.values()) {
			for(String thisCodiceAeroporto : thisStato.codiciAeroporto) {
				if(thisCodiceAeroporto == null) continue;
				if(thisCodiceAeroporto.isBlank()) continue;
				
				String thisCodiceVolo;
				try {
					thisCodiceVolo = thisCodiceAeroporto.substring(0, 0+3);
				} catch(IndexOutOfBoundsException e) {
					continue;
				}
				
				boolean isEqual = thisCodiceVolo.equalsIgnoreCase(codiceVolo.substring(0,0+3));
				if(!isEqual) continue;
				
				return thisStato;
			}
		}
		
		return null;
	}
}
