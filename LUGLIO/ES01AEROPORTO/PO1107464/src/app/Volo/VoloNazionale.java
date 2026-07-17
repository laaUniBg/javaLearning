package app.Volo;

import java.util.Date;

import app.Aereo;

public class VoloNazionale extends Volo {
	public VoloNazionale(String codiceVolo, Date data, Aereo aereo) {
		super(codiceVolo, data, aereo);
	}

	@Override
	public double calcolaPrezzoBase() {
		return 20.0; // meno di 20 euro non puoi trovare nei voli nazionali)
	}
}
