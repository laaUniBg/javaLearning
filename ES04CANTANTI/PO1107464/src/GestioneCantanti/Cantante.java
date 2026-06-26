package GestioneCantanti;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class Cantante {
	@Override
	public int hashCode() {
		return Objects.hash(codiceFiscale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cantante other = (Cantante) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale);
	}

	final int WANTED_LENGTH_ID = 16;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private int eta;
	private String nomeCanzone;

	static private Set<Cantante> listaCantanti = new HashSet<Cantante>();
	
	public Cantante(String paramCodiceFiscale, String paramNome, String paramCognome, int paramEta, String paramNomeCanzone) throws DuplicateCodiceFiscaleException {
		this.setCodiceFiscale(paramCodiceFiscale);
		this.nome = paramNome;
		this.cognome = paramCognome;
		this.eta = paramEta;
		this.nomeCanzone = paramNomeCanzone;
	}

	private void setCodiceFiscale(String paramCodiceFiscale) throws DuplicateCodiceFiscaleException {
		boolean isEmpty = Cantante.listaCantanti != null && Cantante.listaCantanti.isEmpty();
		boolean isCorrectLength = paramCodiceFiscale.length() == WANTED_LENGTH_ID;

		if (!isCorrectLength) {
			throw new IncorrectLenghtCodiceFiscaleException(
					"la lunghezza del codice dovrebbe essere di " + Integer.toString(WANTED_LENGTH_ID, WANTED_LENGTH_ID)
							+ "però adesso è di " + Integer.toString(paramCodiceFiscale.length())
							+ " allora ricontrolla il tuo codice fiscale: " + paramCodiceFiscale);
		}
		
		if(!isEmpty) {
			boolean isDuplicate = Cantante.listaCantanti.contains(this);
			
			if(isDuplicate) {
				throw new DuplicateCodiceFiscaleException("il codice fiscale: " + paramCodiceFiscale + " esiste già nel sistema");
			}
		}
		
		this.codiceFiscale = paramCodiceFiscale;
		Cantante.listaCantanti.add(this);
	}
}
