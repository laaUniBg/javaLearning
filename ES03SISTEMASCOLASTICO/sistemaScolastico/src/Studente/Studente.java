package studente;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import gestioneEsami.Esame;
import gestioneEsami.PossibiliEsami;

public class Studente {
	private int matricola;
	private String nome;
	private String cognome;
	private int numeroEsamiSostenuti;
	protected int numeroPuntiAccumulati;
	final int DEFAULT_NUMERO_PUNTI_PER_ESAME = 1;

	static List<Studente> listaStudenti = new LinkedList<Studente>();
	private Set<Esame> listaEsami = new HashSet<Esame>();
	
	public Studente(int paramMatricola, String paramNome, String paramCognome) throws DuplicateStudenteException {
		this.nome = paramNome;
		this.cognome = paramCognome;
		this.setMatricola(paramMatricola);
		this.numeroEsamiSostenuti = 0;
		this.numeroPuntiAccumulati = 0;
	}

	public void passaEsame(PossibiliEsami paramNomeEsame, LocalDate paramDataEsame) {
		boolean isFirstEsame = listaEsami != null && listaEsami.isEmpty();
		
		if(!isFirstEsame) {
			for(Esame thisEsame : listaEsami) {
				boolean isDuplicate = thisEsame.getNomeEsame().equals(paramNomeEsame);
				if(!isDuplicate) continue;
				throw new DoppioEsameException("Ogni esame può essere passato una sola volta, non puoi inserire un esame due volte");
			}
		}
		
		this.listaEsami.add(new Esame(paramNomeEsame, paramDataEsame));
		this.numeroEsamiSostenuti++;
		this.aggiungiPunti();
	};
	
	public int getMatricola() {
		return this.matricola;
	}
	
	protected void aggiungiPunti() {
		this.numeroPuntiAccumulati += DEFAULT_NUMERO_PUNTI_PER_ESAME;
	};

	private void setMatricola(int paramNumeroMatricola) throws DuplicateStudenteException {
		boolean isFirstStudent = listaStudenti != null && listaStudenti.isEmpty();
		String numeroMatricolaString = Integer.toString(paramNumeroMatricola);
		boolean isLengthCorrect = numeroMatricolaString.length() == 9;

		if (!isLengthCorrect) {
			throw new IncorrectLengthException(
					"la lunghezza della matricola non è 9 caratteri, tu hai scritto " + numeroMatricolaString.length()
							+ " caratteri, ricontrolla se hai scritto bene " + numeroMatricolaString);
		}

		if (!isFirstStudent) {
			for (Studente thisStudente : listaStudenti) {
				boolean isDuplicate = Integer.toString(thisStudente.getMatricola()).equals(numeroMatricolaString);
				if (!isDuplicate)
					continue;

				throw new DuplicateStudenteException("non possono esistere due studenti con lo stessa matricola");
			}
		}

		this.matricola = paramNumeroMatricola;
	}
}
