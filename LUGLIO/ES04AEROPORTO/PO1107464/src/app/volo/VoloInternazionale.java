package app.volo;

import java.util.Date;

import app.Aereo;

public class VoloInternazionale extends Volo {
	private boolean needsPassport;
	
	public VoloInternazionale(String codiceVolo, Date data, Aereo aereo, boolean needsPassport) {
		super(codiceVolo, data, aereo);
		this.needsPassport = needsPassport;
	}

	public VoloInternazionale(Volo altro, boolean needsPassport) {
		super(altro);
		this.needsPassport = needsPassport;
	}
	
	public void controllaPassaporto() {
		if(!this.needsPassport) return;
		
		System.out.println("CONTROLLO DOGANA: CONTROLLO PASSAPORTO CON SUCCESSO");
	}

	@Override
	protected double calcolaPrezzoBase() {
		return 80.0;
	}
	
}
