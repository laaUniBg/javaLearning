package app.volo;

import java.util.Date;

import app.Aereo;

public class VoloInternazionale extends Volo {
	private boolean needsPassaporto;
	
	public VoloInternazionale(String codiceVolo, Date data, Aereo aereo, boolean needsPassaporto) {
		super(codiceVolo, data, aereo);
		this.needsPassaporto = needsPassaporto;
	}

	@Override
	protected double calcolaPrezzoBase() {
		return 80.0;
	}
	
	public void controlloDogana() {
		if(!this.needsPassaporto) return;
		System.out.println("CONTROLLO PASSAPORTO");
	}
}
