package app;

/*
 * Enum:
 * 
 */

public enum TipoPosto {
	ECONOMY(1.0),
	BUSINESS(1.5),
	FIRSTCLASS(2.0);
	
	private final int multiplicatore;
	
	private TipoPosto(double multiplicatore) {
		this.multiplicatore = multiplicatore;
	}
	
	public double getMultiplicatore() {
		return multiplicatore;
	}
}
