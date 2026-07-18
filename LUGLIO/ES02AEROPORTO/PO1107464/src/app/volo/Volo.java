package app.volo;

import java.util.Date;

import app.Aereo;
import app.enums.TipoDestinazione;
import app.exceptions.CodiceIncorrettoException;
import app.interfaces.Prenotabile;

abstract public class Volo implements Prenotabile {
	protected Date data;
	protected Aereo aereo; // has-a (associazione)
	protected String codice;
	protected int passeggeriAttuali;
	protected String destinazione;
	
	public Volo(String codice, Date data, Aereo aereo) {
		
		
	}
	
	private void setCodice(String codiceVolo) {
		TipoDestinazione paeseDestinazione = TipoDestinazione.checkCodiceCorrectAndReturnStato(codiceVolo);
		if(paeseDestinazione == null) {
			throw new CodiceIncorrettoException("RUNTIME EXCEPTION");
		};
	}

}
