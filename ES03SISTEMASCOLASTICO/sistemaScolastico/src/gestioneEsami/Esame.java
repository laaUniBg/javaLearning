package gestioneEsami;

import java.time.LocalDate;

public class Esame {
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
