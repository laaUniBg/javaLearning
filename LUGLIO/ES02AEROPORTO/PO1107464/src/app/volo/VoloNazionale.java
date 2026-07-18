package app.volo;

import java.util.Date;

import app.Aereo;

public class VoloNazionale extends Volo {
	public VoloNazionale(String codiceVolo, Date data, Aereo aereo) {
		super(codiceVolo, data, aereo);
	}
	
	public VoloNazionale(Volo copiaVolo) {
		super(copiaVolo);
	}

	@Override
	protected double calcolaPrezzoBase() {
		return 20.0;
	}

}
