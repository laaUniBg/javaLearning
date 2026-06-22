package packageContoCorrente;

import java.util.ArrayList;
import java.util.List;

public class Banca {
	private String nomeBancaString;
	private List<ContoCorrente> listaConti;

	public Banca(String nomeBancaString) {
		listaConti = new ArrayList<ContoCorrente>();
		this.nomeBancaString = nomeBancaString;
	}

	public void aggiungiConto(ContoCorrente newConto) throws ContoDuplicatoException {
		String newContoOwnerString = newConto.getOwnerName();
		for (ContoCorrente oldContoCorrente : listaConti) {
			String thisOldOwnerString = oldContoCorrente.getOwnerName();
			
			boolean isDuplicate = newContoOwnerString.equals(thisOldOwnerString);
			
			if(isDuplicate) {
				throw new ContoDuplicatoException(
						new String("il conto di ").concat(newContoOwnerString).concat(" esiste già nel sistema!")
					);
			}
			
			listaConti.add(newConto);
		}
	}
}
