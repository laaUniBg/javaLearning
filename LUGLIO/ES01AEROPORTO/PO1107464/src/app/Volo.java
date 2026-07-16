package app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.exceptions.CodiceNonValidoException;

public abstract class Volo implements Prenotabile, Comparable<Volo> {
	private String codice;
	private Date data;
	private Aereo aereo; // has-a (associazione)
	
	private static final Map<String, Double> CODICISCONTOMAP = initCouponMap();
	

	public Volo(String codice, Date data, Aereo aereo) {
		this.setCodice(codice);
		this.data = data;
		this.aereo = aereo;
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
