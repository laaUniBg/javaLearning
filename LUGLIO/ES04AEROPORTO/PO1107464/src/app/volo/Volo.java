package app.volo;

import java.util.Date;

import app.Aereo;
import app.enums.EnumCodiceAeroporto;
import app.enums.EnumCoupon;
import app.exceptions.CodiceIncorrettoException;
import app.exceptions.VoloPienoException;
import app.interfaces.Prenotabile;

public abstract class Volo implements Prenotabile, Comparable<Volo> {
	private Date data;
	private Aereo aereo;
	private String codiceVolo;
	private String statoDestinazione;
	private int numeroPasseggeri;
	
	public Volo(String codiceVolo, Date data, Aereo aereo) {
		this.setCodiceVolo(codiceVolo);
		this.data = data;
		this.aereo = aereo;
		this.numeroPasseggeri = 0;
	}

	public Volo(Volo altro) {
		this(altro.getCodiceVolo(), altro.getData(), altro.getAereo());
	};
	
	abstract protected double calcolaPrezzoBase();
	
	public double calcolaPrezzo(double percentualeSconto) {
		final double prezzoBase = this.calcolaPrezzoBase();
		final double euroScontati = prezzoBase * (percentualeSconto/100.0);
		final double risultato = prezzoBase - euroScontati;
		return risultato;
	}
	
	public double calcolaPrezzo(EnumCoupon coupon) {
		double thisPercentualeSconto = coupon.percentualeSconto;
		return this.calcolaPrezzo(thisPercentualeSconto);
	}
	
	@Override
	public int compareTo(Volo o) {
		return this.codiceVolo.compareToIgnoreCase(o.getCodiceVolo());
	}

	@Override
	public void prenota() throws VoloPienoException {
		if(this.numeroPasseggeri >= this.aereo.getCapienza()) throw new VoloPienoException("PRENOTA: VOLOPIENOEXCEPTION");
		this.numeroPasseggeri++;
	}

	@Override
	public void cancella() {
		if(this.numeroPasseggeri <= 0) return;
		this.numeroPasseggeri--;
	}
	
	private void setCodiceVolo(String codiceVolo) {
		EnumCodiceAeroporto thisStato = EnumCodiceAeroporto.checkCodiceVoloAndReturnStato(codiceVolo);
		if(thisStato == null) throw new CodiceIncorrettoException("SETCODICEVOLO: RUNTIMEEXCEPTION");
		this.codiceVolo = codiceVolo;
		this.statoDestinazione = thisStato.name();
	}
	
	@Override
	public String toString() {
		final String strData = "DATA: " + this.data.toString();
		final String strCodiceVolo = "CODICEVOLO: " + this.codiceVolo;
		final String strCapienza = "CAPIENZA: " + this.aereo.getCapienza();
		
		String risultato = "";
		
		for(String thisString : new String[] {strCodiceVolo, strData, strCapienza}) {
			risultato += " | " + thisString;
		}
		
		return risultato;
	}
	
	public Date getData() {
		return this.data;
	}

	public Aereo getAereo() {
		return this.aereo;
	}

	public String getCodiceVolo() {
		return this.codiceVolo;
	}
}
