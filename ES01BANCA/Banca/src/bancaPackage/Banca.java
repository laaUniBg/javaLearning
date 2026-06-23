package bancaPackage;

import java.util.ArrayList;
import java.util.List;

public class Banca {
	private String nomeBancaString;
	private List<ContoCorrente> listaConti;

	public Banca(String nomeBancaString) {
		listaConti = new ArrayList<ContoCorrente>();
		this.nomeBancaString = nomeBancaString;
	}

	public String getBancaName() {
		return nomeBancaString;
	};

	public ContoCorrente aggiungiConto(ContoCorrente newConto) throws ContoDuplicatoException {
		String newContoOwnerString = newConto.getOwnerName();
		for (ContoCorrente oldContoCorrente : listaConti) {
			String thisOldOwnerString = oldContoCorrente.getOwnerName();

			boolean isDuplicate = newContoOwnerString.equals(thisOldOwnerString);

			if (isDuplicate) {
				throw new ContoDuplicatoException(
						new String("il conto di ").concat(newContoOwnerString).concat(" esiste già nel sistema!"));
			}
		};
		listaConti.add(newConto);
		return listaConti.getLast();
	}

	public ContoCorrente getContoCorrente(String nomeTitolare) throws ContoNotFoundException {
		for (ContoCorrente thisContoCorrente : listaConti) {
			boolean isContoWanted = thisContoCorrente.getOwnerName().equals(nomeTitolare);
			if (!isContoWanted) continue;
			return thisContoCorrente;
		}
		throw new ContoNotFoundException(
				new String("non esiste un conto attestato al titolare: ").concat(nomeTitolare)
			);
	};
}