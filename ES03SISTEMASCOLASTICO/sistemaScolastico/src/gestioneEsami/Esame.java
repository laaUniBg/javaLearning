package gestioneEsami;

import java.time.LocalDate;
import java.util.Objects;

public class Esame {
	@Override
	public int hashCode() {
		return Objects.hash(nomeEsame);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Esame other = (Esame) obj;
		return nomeEsame == other.nomeEsame;
	}

	PossibiliEsami nomeEsame;
	LocalDate dataEsame;
	
	public Esame(PossibiliEsami paramNomeEsame, LocalDate paramDataEsame) {
		this.nomeEsame = paramNomeEsame;
		this.dataEsame = paramDataEsame;
	}
	
	public PossibiliEsami getNomeEsame() {
		return this.nomeEsame;
	}
}
