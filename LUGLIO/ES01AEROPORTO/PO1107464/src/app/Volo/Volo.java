package app.Volo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.Aereo;
import app.enums.TipoCoupon;
import app.enums.TipoDestinazione;
import app.enums.TipoPosto;
import app.exceptions.CodiceNonValidoException;
import app.exceptions.VoloPienoException;
import app.interfaces.Prenotabile;

public abstract class Volo implements Prenotabile, Comparable<Volo> {
	protected String codiceVolo;
	protected Date data;
	protected Aereo aereo; // has-a (associazione)
	protected int passeggeriAttuali;
	protected String paeseDestinazione;
	
	private static final Map<String, Double> CODICISCONTOMAP = initCouponMap();
	
	public Volo(String codiceVolo, Date data, Aereo aereo) {
		this.setCodiceVolo(codiceVolo);
		this.data = data;
		this.aereo = aereo;
		this.passeggeriAttuali = 0;
		this.paeseDestinazione = TipoDestinazione.getPaeseDestinazione(codiceVolo).name();
	};
	
	// overloading del costruttore
	public Volo(Volo voloToCopy) {
		this(voloToCopy.getCodiceVolo(), voloToCopy.getData(), voloToCopy.getAereo());
	};
	
	// metodo astratto
	public abstract double calcolaPrezzoBase();
	
	public double calcolaPrezzo(double percentualeSconto, TipoPosto tipoPosto) {
		double base = this.calcolaPrezzoBase() * tipoPosto.getMoltiplicatore();
		double euroRidotti = base * (percentualeSconto / 100.0);
		double result = base - euroRidotti;
		return result;
	};
	
	// overloading
	public double calcolaPrezzo(String couponName, TipoPosto tipoPosto) {
		Double percentualeSconto = CODICISCONTOMAP.get(couponName.toUpperCase());
		boolean hasPercentuale = percentualeSconto != null;
		
		if(!hasPercentuale) {
			percentualeSconto = 0.0;
		};
		
		return this.calcolaPrezzo(percentualeSconto, tipoPosto);
	};
	
	// ecco come si usa interface
	
	@Override
	public void prenota() throws VoloPienoException {
		boolean stillHaveSpace = this.passeggeriAttuali < this.aereo.getCapienza();
		if(!stillHaveSpace) throw new VoloPienoException("il volo n." + this.codiceVolo + " è pieno poichè il max posti è: " + this.aereo.getCapienza());
		passeggeriAttuali++;
	}
	
	@Override
	public void cancella() {
		boolean isEmpty = passeggeriAttuali <= 0;
		if(isEmpty) return;
		passeggeriAttuali--;
	};
	
	// usiamo compareTo di Comparable
	
	@Override
	public int compareTo(Volo altroVolo) {
		return this.codiceVolo.compareTo(altroVolo.codiceVolo);
	}
	
	@Override
	public String toString() {
		final String strCodiceVolo = "codice volo: " + this.codiceVolo;
		final String strData = "data volo: " + this.data;
		final String strAereo = "nome aereo: " + this.aereo.getModello();
		final String strPosti = "posti occupati: " + Integer.toString(this.passeggeriAttuali) + "/" + Integer.toString(this.aereo.getCapienza());
		
		final String[] arrayInfo = {strCodiceVolo, strData, strAereo, strPosti};
		
		String result = "";
		
		for(String thisString : arrayInfo) {
			result += " | " + thisString;
		};
		
		return result;
	}

	private static Map<String, Double> initCouponMap() {
		Map<String, Double> result = new HashMap<String, Double>();
		result.put(TipoCoupon.BRONZO10.name(), 10.0);
		result.put(TipoCoupon.ARGENTO20.name(), 20.0);
		result.put(TipoCoupon.ORO30.name(), 30.0);
		return result;
	};
	
	private void setCodiceVolo(String codice) {
		if (codice == null || codice.isBlank()) {
			throw new CodiceNonValidoException("stringa nulla oppure vuota");
		}
		this.codiceVolo = codice;
	}

	public String getCodiceVolo() {
		return this.codiceVolo;
	}

	public Date getData() {
		return this.data;
	};

	public Aereo getAereo() {
		return this.aereo;
	};
}
