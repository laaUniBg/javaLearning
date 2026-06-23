package bancaPackage;

public class ContoCorrente {
	protected double saldo;
	private String nomeTitolare;
	
	public ContoCorrente(String titolare, double saldoIniziale) {		
		this.nomeTitolare = titolare;
		this.setSaldo(saldoIniziale);
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
	
	private void setSaldo(double newSaldo) {
		boolean isNegative = newSaldo < 0;
		
		if(isNegative) {
			throw new IllegalArgumentException(
					"policy aziendale: non puoi avere debiti da noi allora hai 0€"
				);
		}
		
		this.saldo = newSaldo;
	}
	
	public void printWelcome() {
		System.out.println(
				new String("buongiorno ")
					.concat(this.getOwnerName())
					.concat("! \noggi possiedi nel tuo conto ")
					.concat(String.valueOf(this.getSaldo()))
					.concat(" euro (€)")
			);
	};
	
	protected void printSuccess() {
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
	}
	
	public void versa(double importo) {
		if(importo <= 0) {
			throw new IllegalArgumentException(
					"importo negativo, versa un importo positivo"
				);
		}
		
		this.setSaldo(this.getSaldo() + importo);
		this.printSuccess();
	};
	
	public void preleva(double importo) {
		boolean isNegative = importo < 0;
		boolean isPoor = importo > this.getSaldo();
		
		if(isNegative) {
			throw new IllegalArgumentException("Impossibile prelevare valori negativi");
		}
		
		if(isPoor) {
			throw new IllegalArgumentException(
					new String("non hai abbastanza soldi, ")
						.concat("puoi prelevare al massimo ")
						.concat(String.valueOf(this.getSaldo()))
						.concat(" euro (€)")
				);
		}
		
		this.setSaldo(this.getSaldo() - importo);
		this.printSuccess();
	}
}