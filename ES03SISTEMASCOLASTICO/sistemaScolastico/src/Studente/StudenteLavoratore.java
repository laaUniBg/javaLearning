package Studente;

public class StudenteLavoratore extends Studente {
	private String nomeDatoreLavoro;
	
	public StudenteLavoratore(int paramMatricola, String paramNome, String paramCognome, String paramNomeDatoreLavoro) throws DuplicateStudenteException {
		super(paramMatricola, paramNome, paramCognome);
		this.nomeDatoreLavoro = paramNomeDatoreLavoro;
	}
	
	@Override
	protected void aggiungiPunti() {
		this.numeroPuntiAccumulati += 2;
	}
}
