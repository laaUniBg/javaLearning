package app.volo;

import java.util.Date;

import app.Aereo;

public class VoloInternazionale extends Volo {
	public VoloInternazionale(String codiceVolo, Date data, Aereo aereo, boolean needsPassport) {
		super(codiceVolo, data, aereo);
	}

	@Override
	protected double calcolaPrezzoBase() {
		return 80.0;
	}
}
