package GestioneCantanti;

import java.util.Objects;

public abstract class Spettatore {
	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spettatore other = (Spettatore) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale);
	}

	private String codiceFiscale;
	
	public Spettatore(String paramCodiceFiscale) {
		this.codiceFiscale = paramCodiceFiscale;
	}
}
