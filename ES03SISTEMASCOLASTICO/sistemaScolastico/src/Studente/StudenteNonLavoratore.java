package Studente;

public class StudenteNonLavoratore extends Studente {
	public StudenteNonLavoratore(int paramMatricola, String paramNome, String paramCognome) throws DuplicateStudenteException {
		super(paramMatricola, paramNome, paramCognome);
	}
	
	@Override
	protected void aggiungiPunti() {
		this.numeroPuntiAccumulati += 1;
	}
}
