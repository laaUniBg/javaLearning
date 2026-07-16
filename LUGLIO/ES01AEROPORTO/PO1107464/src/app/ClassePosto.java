package app;

public enum ClassePosto {
	ECONOMY(1.0),
	BUSINESS(1.5),
	FIRST(2.5);
	
	private final double moltiplicatore;
	
	private ClassePosto(double moltiplicatore) {
		this.moltiplicatore = moltiplicatore;
	}
	
	public double getMultiplicatore() {
		return moltiplicatore;
	}
}
