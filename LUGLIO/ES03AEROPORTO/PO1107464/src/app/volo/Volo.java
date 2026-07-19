package app.volo;

import java.util.Date;

import app.Aereo;
import app.enums.TipoCoupon;
import app.enums.TipoDestinazione;
import app.exeptions.CodiceVoloNonValido;
import app.exeptions.VoloPienoException;
import app.interfaces.Prenotabile;

public abstract class Volo implements Prenotabile, Comparable<Volo> {
	private Date data;
	private String codiceVolo;
	private Aereo aereo;
	private int passeggeriAttuali;
	private String destinazione;
	
	public Volo(String codiceVolo, Date data, Aereo aereo) {
		this.setCodiceVolo(codiceVolo);
		this.data = data;
		this.aereo = aereo;
		this.passeggeriAttuali = 0;
	}
	
	public Volo(Volo voloDaCopiare) {
		this(voloDaCopiare.getCodiceVolo(), voloDaCopiare.getData(), voloDaCopiare.getAereo());
	}
	
	private void setCodiceVolo(String codiceVolo) {
		TipoDestinazione thisPaese = TipoDestinazione.getPaeseFromCodiceVolo(codiceVolo);
		if(thisPaese == null) throw new CodiceVoloNonValido("SET CODICE VOLO: RUNTIME EXCEPTION CODICE VOLO NON VALIDO");
		this.codiceVolo = codiceVolo;
		this.destinazione = thisPaese.name();
	}
	
	protected abstract double calcolaPrezzoBase();
	
	public double calcolaPrezzo(double percentualeSconto) {
		final double base = this.calcolaPrezzoBase();
		final double euroScontati = base * (percentualeSconto/100.0);
		final double risultato = base - euroScontati;
		return risultato;
	};
	
	public double calcolaPrezzo(TipoCoupon tipoCoupon)  {
		final double percentualeSconto = tipoCoupon.percentualeSconto;
		return this.calcolaPrezzo(percentualeSconto);
	}
	
	@Override
	public void prenota() throws VoloPienoException {
		final boolean isPieno = this.passeggeriAttuali >= this.aereo.getCapienza();
		if(isPieno) {
			throw new VoloPienoException("PRENOTA: VOLOPIENOEXCEPTION");
		}
		this.passeggeriAttuali++;
	}

	@Override
	public void cancella() {
		final boolean isVuoto = this.passeggeriAttuali <= 0;
		if(isVuoto) return;
		this.passeggeriAttuali--;
	}
	
	public Date getData() {
		return data;
	}

	public String getCodiceVolo() {
		return codiceVolo;
	}

	public Aereo getAereo() {
		return aereo;
	}

	// normale ordinamento
	@Override
	public int compareTo(Volo o) {
		return this.codiceVolo.compareTo(o.getCodiceVolo());
	}
	
	@Override
	public String toString() {
		final String strData = "DATA: " + this.data.toString();
		final String strCodiceVolo = "CodiceVolo: " + this.codiceVolo;
		final String strCapienza = "CapienzaAereo: " + this.aereo.getCapienza();
		final String strModello = "ModelloAereo: " + this.aereo.getModello();
		
		final String[] strings = {strData, strCodiceVolo, strCapienza, strModello};
		
		String result = "";
		
		for(String thisStr : strings) {
			result += thisStr + " | ";
		};
		
		return result;	
	}
}
