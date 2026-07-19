package app.volo;

import java.util.Date;

import app.Aereo;

public class VoloInternazionale extends Volo {
	boolean needsPassaporto;
	
	public VoloInternazionale(String codiceVolo, Date data, Aereo aereo, boolean needsPassaporto) {
		super(codiceVolo, data, aereo);
		this.needsPassaporto = needsPassaporto;
	}
	
	public VoloInternazionale(Volo voloDaCopiare, boolean needsPassaporto) {
		super(voloDaCopiare);
		this.needsPassaporto = needsPassaporto;
	}
	
	@Override
	protected double calcolaPrezzoBase() {
		return 80.0;
	}
	
	public void controlloDogana() {
		boolean needsPassaporto = this.needsPassaporto;
		if(!needsPassaporto) return;
		
		System.out.println("CONTROLLO DOGANA: PASSAPORTO CONTROLLATO");
	};
}
