package aeroporto.passeggero;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aeroporto.boardingPass.BoardingPass;
import aeroporto.boardingPass.Volo;
import aeroporto.passeggero.exceptions.DuplicatoPassaportoException;
import aeroporto.passeggero.exceptions.LunghezzaErrataIdPassaportoException;

public class Passeggero {
	private String nome;
	private String cognome;
	private String numeroPassaporto;

	private int numeroVoliEseguiti;
	private int numeroPuntiAccomulati;

	private static List<Passeggero> listaPasseggeri = new ArrayList<Passeggero>();
	private List<BoardingPass> listaBoardingPasses = new ArrayList<BoardingPass>();
	
	public Passeggero(String paramNome, String paramCognome, String paramNumeroPassaporto) throws DuplicatoPassaportoException {
		this.setNome(paramNome);
		this.setCognome(paramCognome);
		this.setNumeroPassaporto(paramNumeroPassaporto);
		
		Passeggero.listaPasseggeri.add(this);
	}
	
	private void setNome(String paramNome) {
		this.nome = paramNome;
	}
	
	private void setCognome(String paramCognome) {
		this.cognome = paramCognome;
	}
	
	public void compraBiglietto(Volo paramVolo) {
		listaBoardingPasses.add(new BoardingPass(paramVolo));
	}

	void setNumeroPassaporto(String paramNumeroPassaporto) throws DuplicatoPassaportoException {
		boolean isCorrectLength = paramNumeroPassaporto != null && paramNumeroPassaporto.length() == 9;
		
		if(!isCorrectLength) {
			throw new LunghezzaErrataIdPassaportoException("l'id del passaporto deve avere esclusivamente 9 caratteri, controlla un'altra volta di aver scritto bene l'id: " + paramNumeroPassaporto);
		}
		
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
