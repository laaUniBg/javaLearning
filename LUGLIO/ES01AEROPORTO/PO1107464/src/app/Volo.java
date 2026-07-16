package app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.enums.TipoCoupon;
import app.exceptions.CodiceNonValidoException;
import app.exceptions.VoloPienoException;
import app.interfaces.Prenotabile;

public abstract class Volo implements Prenotabile, Comparable<Volo> {
	protected String codice;
	protected Date data;
	protected Aereo aereo; // has-a (associazione)
	protected int passeggeriAttuali;
	
	private static final Map<String, Double> CODICISCONTOMAP = initCouponMap();
	
	public Volo(String codice, Date data, Aereo aereo) {
		this.setCodice(codice);
		this.data = data;
		this.aereo = aereo;
		this.passeggeriAttuali = 0;
	};
	
	// overloading del costruttore
	public Volo(Volo voloToCopy) {
		this(voloToCopy.getCodice(), voloToCopy.getData(), voloToCopy.getAereo());
	};
	
	// metodo astratto
	public abstract double calcolaPrezzoBase();
	
	public double calcolaPrezzo(double percentualeSconto) {
		double base = this.calcolaPrezzoBase();
		double euroRidotti = base * (percentualeSconto / 100.0);
		double result = base - euroRidotti;
		return result;
	};
	
	// overloading
	public double calcolaPrezzo(String couponName) {
		Double percentualeSconto = CODICISCONTOMAP.get(couponName.toUpperCase());
		boolean hasPercentuale = percentualeSconto != null;
		
		if(!hasPercentuale) {
			percentualeSconto = 0.0;
		};
		
		return this.calcolaPrezzo(percentualeSconto);
	};
	
	// ecco come si usa interface
	
	@Override
	public void prenota() throws VoloPienoException {
		boolean stillHaveSpace = this.passeggeriAttuali < this.aereo.getCapienza();
		if(!stillHaveSpace) throw new VoloPienoException("il volo n." + this.codice + " è pieno");
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
		return this.codice.compareTo(altroVolo.codice);
	}
	
	@Override
	public String toString() {
		final String strCodice = "codice volo: " + this.codice;
		final String strData = "data volo: " + this.data;
		final String strAereo = "nome aereo: " + this.aereo.getModello();
		final String strPosti = "posti occupati: " + Integer.toString(this.passeggeriAttuali) + "/" + Integer.toString(this.aereo.getCapienza());
		
		final String[] arrayInfo = {strCodice, strData, strAereo, strPosti};
		
		String result = "";
		
		for(String thisString : arrayInfo) {
			result.concat(" | " + thisString);
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
	
	private void setCodice(String codice) {
		if (codice == null || codice.isBlank()) {
			throw new CodiceNonValidoException("stringa nulla oppure vuota");
		}
		this.codice = codice;
	}

	public String getCodice() {
		return this.codice;
	}

	public Date getData() {
		return this.data;
	};

	public Aereo getAereo() {
		return this.aereo;
	};
}
