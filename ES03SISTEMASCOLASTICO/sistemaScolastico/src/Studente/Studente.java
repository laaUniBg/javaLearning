package Studente;

import java.util.LinkedList;
import java.util.List;

public class Studente {
	private int matricola;
	private String nome;
	private String cognome;
	private int numeroEsamiPassati;
	private int numeroPuntiAccumulati;
	protected int numeroPuntiPerEsame;

	List<Studente> listaStudenti = new LinkedList<Studente>();

	public Studente(int paramMatricola, String paramNome, String paramCognome) throws DuplicateStudenteException {
		this.nome = paramNome;
		this.cognome = paramCognome;
		this.setMatricola(paramMatricola);
	}

	private void setMatricola(int numeroMatricola) throws DuplicateStudenteException {
		boolean isFirstStudent = listaStudenti.isEmpty();
		String numeroMatricolaString = Integer.toString(numeroMatricola);
		boolean isLengthCorrect = numeroMatricolaString.length() == 9;

		if (!isLengthCorrect) {
			throw new IncorrectLengthException("la lunghezza della matricola non è 9 caratteri, tu hai scritto "
					+ Integer.toString(numeroMatricola).length() + " caratteri, ricontrolla se hai scritto bene "
					+ Integer.toString(numeroMatricola));
		}

		if (!isFirstStudent) {
			for (Studente thisStudente : listaStudenti) {
				boolean isDuplicate = Integer.toString(thisStudente.matricola).equals(numeroMatricolaString);
				if (!isDuplicate) continue;

				throw new DuplicateStudenteException("non possono esistere due studenti con lo stessa matricola");
			}
		}
		
		this.matricola = numeroMatricola;
	}
}
