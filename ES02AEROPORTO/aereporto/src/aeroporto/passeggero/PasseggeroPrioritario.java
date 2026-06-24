package aeroporto.passeggero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aeroporto.magazzino.Bevanda;
import aeroporto.magazzino.TipoBevanda;
import aeroporto.passeggero.exceptions.DuplicatoPassaportoException;

public class PasseggeroPrioritario extends Passeggero {
	class BevandaNonPresenteNelCatalogoException extends RuntimeException {
		public BevandaNonPresenteNelCatalogoException(String message) {
			super(message);
		};
	};
	
	class BevandaEsauritaException extends RuntimeException {
		public BevandaEsauritaException(String message) {
			super(message);
		};
	};
	
	private TipoBevanda choosedDrink;
	
	Map<TipoBevanda, Integer> magazzinoBevande = new HashMap<>(
		Map.of(TipoBevanda.ACQUA, 10, TipoBevanda.COCACOLA,5, TipoBevanda.FANTA, 2)
	);

	public PasseggeroPrioritario(String paramNome, String paramCognome, String paramNumeroPassaporto) throws DuplicatoPassaportoExceptiona {
		super(paramNome, paramCognome, paramNumeroPassaporto);
	};
	
	

	public void setDrink(TipoBevanda paramTipoBevanda) {
		boolean isCorrectParameter = magazzinoBevande.containsKey(paramTipoBevanda);
		
		if(!isCorrectParameter) {
			throw new BevandaNonPresenteNelCatalogoException("la bevanda" + paramTipoBevanda.toString() + "selezionata non è presente nel nostro catalogo");
		};
		
		int numeroBevandaRimasti = this.magazzinoBevande.get(paramTipoBevanda);
		boolean isFinished = numeroBevandaRimasti == 0;
		
		if(isFinished) {
			throw new BevandaEsauritaException("cambia tipo di bibita perchè sono esauriti");
		};
		
		this.choosedDrink = paramTipoBevanda;
	}
}
