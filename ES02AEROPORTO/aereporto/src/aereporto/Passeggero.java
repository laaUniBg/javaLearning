package aereporto;

import java.util.ArrayList;
import java.util.List;


public class Passeggero {
	private String nome;
	private String cognome;
	private String numeroPassaporto;

	private int numeroVoliEseguiti;
	private int numeroPuntiAccomulati;

	private static List<Passeggero> listaPasseggeri = new ArrayList<Passeggero>();
	
	public Passeggero(String paramNome, String paramCognome, String paramNumeroPassaporto) {
		this.setNome(paramNome);
		this.setCognome(paramCognome);
		
		try {
			this.setNumeroPassaporto(paramNumeroPassaporto);
		} catch (DuplicatoPassaportoException e) {
			System.out.println("\n" + e.getMessage());
		};
	}
	
	private void setNome(String paramNome) {
		this.nome = paramNome;
	}
	
	private void setCognome(String paramCognome) {
		this.cognome = paramCognome;
	}

	/**
	 * @param paramNumeroPassaporto
	 * @throws DuplicatoPassaportoException
	 */
	void setNumeroPassaporto(String paramNumeroPassaporto) throws DuplicatoPassaportoException {
		if (!listaPasseggeri.isEmpty()) {
			for (Passeggero thisPasseggero : listaPasseggeri) {
				boolean isDuplicate = thisPasseggero.numeroPassaporto.equals(paramNumeroPassaporto);
				if (!isDuplicate)
					continue;

				throw new DuplicatoPassaportoException(
						"non possono esistere due passaporti con lo stesso id: " + paramNumeroPassaporto);
			}
		}
		
		this.numeroPassaporto = paramNumeroPassaporto;
	}
}
