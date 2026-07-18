package app.volo;

import java.util.Date;

import app.Aereo;
import app.enums.TipoCoupon;
import app.enums.TipoDestinazione;
import app.exceptions.CodiceIncorrettoException;
import app.exceptions.VoloPienoException;
import app.interfaces.Prenotabile;

abstract public class Volo implements Prenotabile {
	private Date data;
	private Aereo aereo; // has-a (associazione)
	private 	String codiceVolo;
	private int passeggeriAttuali;
	private String destinazione;
	
	public Volo(String codiceVolo, Date data, Aereo aereo) {
		this.setCodiceVolo(codiceVolo);
		this.data = data;
		this.aereo = aereo;
		this.passeggeriAttuali = 0;
	};
	
	public Volo(Volo copiaVolo) {
		this(copiaVolo.getCodiceVolo(), copiaVolo.getData(), copiaVolo.getAereo());
	};
	
	private void setCodiceVolo(String codiceVolo) {
		TipoDestinazione paeseDestinazione = TipoDestinazione.checkCodiceCorrectAndReturnStato(codiceVolo);
		if(paeseDestinazione == null || (codiceVolo != null && codiceVolo.isBlank())) {
			throw new CodiceIncorrettoException("RUNTIME EXCEPTION");
		};
		
		this.codiceVolo = codiceVolo;
		this.destinazione = paeseDestinazione.name();
	}
	
	@Override
	public void prenota() throws VoloPienoException {
		final int max = this.aereo.getCapienza();
		final boolean isPieno = this.passeggeriAttuali >= max;
		if(isPieno) {
			throw new VoloPienoException("VOLO PIENO CHECKED EXCEPTION");
		}	
		this.passeggeriAttuali++;
	}
	
	protected abstract double calcolaPrezzoBase();
	
	public double calcolaPrezzo(Double percentualeSconto) {
		final double base = calcolaPrezzoBase();
		final double valoreScontato = base * (percentualeSconto/100.0);
		final double result = base-valoreScontato;
		return result;
	}
	
	public double calcolaPrezzo(TipoCoupon tipoCoupon) {
		final double base = calcolaPrezzoBase();
		final double percentualeSconto = tipoCoupon.percentualeSconto;
		return this.calcolaPrezzo(percentualeSconto);
	}

	@Override
	public void cancella() {
		boolean isVuoto = this.passeggeriAttuali <= 0;
		if(isVuoto) return;
		this.passeggeriAttuali--;
	}
	
	private Date getData() {
		return this.data;
	}

	private Aereo getAereo() {
		return this.aereo;
	}

	private String getCodiceVolo() {
		return this.codiceVolo;
	}
}
