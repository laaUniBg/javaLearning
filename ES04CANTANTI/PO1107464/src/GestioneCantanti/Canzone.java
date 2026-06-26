package GestioneCantanti;

import java.util.Objects;
import java.util.Set;

public class Canzone {
	private String titolo;
	private int durataMinuti;
	private String testo;
	private int punteggio;
	
	static private Set<Canzone> listaCanzoni = new HashSet<>();
	
	public Canzone(String paramTitolo, int paramDurataMinuti, String paramTesto, int paramPunteggio) {
		this.durataMinuti = paramDurataMinuti;
		this.testo = paramTesto;
		this.punteggio = paramPunteggio;	
	}

	@Override
	public int hashCode() {
		return Objects.hash(titolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Canzone other = (Canzone) obj;
		return Objects.equals(titolo, other.titolo);
	}
	
	private void setTitolo(String paramString) {
		boolean isDuplicate = Canzone.listaCanzoni.contains(paramString);
		
		if(isDuplicate) {
			throw new DuplicateTitoloCanzoneException("non possono coesistere due canzoni con lo stesso titolo: " + paramString);
		}
		
		this.titolo = paramString;
	}

}
