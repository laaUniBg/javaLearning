package app.Volo;

import java.util.Date;

import app.Aereo;

public class VoloInternazionale extends Volo {
	private boolean isPassaportoRichiesto;
	
	public VoloInternazionale(String codice, Date data, Aereo aereo, boolean pssaportoRichisto) {
		super(codice, data, aereo);
	}

	public double calcolaPrezzoBase() {		
		return 100.0; // considerando 2 ore come average e le tasse
	};

	public void effettuaControlloDogana() {
		if(!isPassaportoRichiesto) return;
		// si puo rendere complessa la logica ma per un esame di 2 ore basta e avanza
		// come si crea un metodo nella classe che estende Volo
		System.out.println("-> [Dogana] Controllo passaporto superato");
	}
};
