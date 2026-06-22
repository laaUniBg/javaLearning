package packageContoCorrente;

public class ContoPremium  extends ContoCorrente {
	public ContoPremium(String titolare, double saldoIniziale) {
		super(titolare, saldoIniziale);
	};
	
	@Override
	public void preleva(double importo) {
		boolean isPositive = importo > 0;
		if(!isPositive) {
			throw new IllegalArgumentException("il valore da prelevare " + Double.toString(importo) + " non puoò essere negativo");
		}
		
		double maxEuroDaPrelevare = this.saldo+500;
		boolean isPoor = importo>maxEuroDaPrelevare;
		if(isPoor) {
			throw new IllegalArgumentException("non puoi prelevare più di quello che possiedi");
		};
		
		this.saldo-=importo;
		
		this.printSuccess();
	}
}
