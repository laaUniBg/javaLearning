package app.Volo;

import java.util.Date;

import app.Aereo;

public class VoloNazionale extends Volo {
	private String documentoRichiesto;
	
	public VoloNazionale(String codiceVolo, Date data, Aereo aereo, String documentoRichiesto) {
		super(codiceVolo, data, aereo);
		this.documentoRichiesto = documentoRichiesto;
	}

	@Override
	public double calcolaPrezzoBase() {
		return 20.0; // meno di 20 euro non puoi trovare nei voli nazionali)
	}
}
