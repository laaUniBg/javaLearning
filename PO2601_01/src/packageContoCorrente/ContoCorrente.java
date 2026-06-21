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
	
	public void versa(double importo) {
		if(importo <= 0) {
			throw new IllegalArgumentException(
					"importo negativo, versa un importo positivo"
				);
		}
		
		this.saldo += importo;
		
		System.out.println("Transazione andata a buon fine!");
	};
}
