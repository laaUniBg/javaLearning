package aeroporto.passeggero;

import aeroporto.passeggero.exceptions.DuplicatoPassaportoException;

public class PasseggeroNonPrioritario extends Passeggero {
	public PasseggeroNonPrioritario(String paramNome, String paramCognome, String paramNumeroPassaporto) throws DuplicatoPassaportoException {
		super(paramNome, paramCognome, paramNumeroPassaporto);
		this.numeriPuntiPerViaggio = 1;
	}
}
