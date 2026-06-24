package Studente;

public class StudenteLavoratore extends Studente {
	public StudenteLavoratore(int paramMatricola, String paramNome, String paramCognome) throws DuplicateStudenteException {
		super(paramMatricola, paramNome, paramCognome);
		this.numeroPuntiPerEsame = 2;
	}
}
