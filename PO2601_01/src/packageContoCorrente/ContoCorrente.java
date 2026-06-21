package packageContoCorrente;

public class ContoCorrente {
	private double saldo;
	private String nomeTitolare;
	
	public ContoCorrente(String titolare, double saldoIniziale) {
		if(saldoIniziale < 0) {
			this.saldo = 0;
			System.out.println(
					"policy aziendale: non puoi avere debiti da noi allora hai 0€"
				);
		};
		
		this.nomeTitolare = titolare;
		this.saldo = saldoIniziale;
	};
	
	public ContoCorrente(String titolare) {
		this(titolare, 0);
	}
	
	public String getOwnerName() {
		return this.nomeTitolare;
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public void versa(double importo) {
		if(importo <= 0) {
			throw new IllegalArgumentException(
					"importo negativo, versa un importo positivo"
				);
		}
		
		this.saldo += importo;

		System.out.println(
				new String("\nnuovo saldo: ")
					.concat(String.valueOf(this.saldo)
					.concat(" euro (€)")
			));
		
		System.out.println(
				new String("------ ")
					.concat("Transazione andata a buon fine!")
					.concat(" ------")
			);
	};
	
	public void preleva(double importo) {
		boolean isNegative = importo < 0;
		boolean isPoor = importo > this.getSaldo();
		
		if(isNegative && isPoor) {
			throw 
		}
		
	}
}
