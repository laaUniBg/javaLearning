package app.enums;

public enum TipoCoupon {
	BRONZO10(10.0),
	ARGENTO20(20.0),
	ORO30(30.0);
	
	final public double percentualeSconto;
	
	private TipoCoupon(double percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}
}
