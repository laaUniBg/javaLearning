package app.enums;

public enum TipoPosto {
	ECONOMY(1.0),
	BUSINESS(1.6),
	FIRST(2.5);
	
	private final double moltiplicatore;
	
	private TipoPosto(double moltiplicatore) {
		this.moltiplicatore = moltiplicatore;
	};
	
	public double getMoltiplicatore() {
		return this.moltiplicatore;
	}
}
