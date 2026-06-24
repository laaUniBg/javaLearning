package aeroporto.passeggero;

import java.util.ArrayList;
import java.util.List;

import aeroporto.magazzino.Bevanda;
import aeroporto.magazzino.TipoBevanda;

public class PasseggeroPrioritario extends Passeggero {
	private TipoBevanda choosedDrink;

	List<Bevanda> listaBevande = new ArrayList<Bevanda>(List.of(
			new Bevanda(TipoBevanda.COCACOLA.toString(), 10),
			new Bevanda(TipoBevanda.FANTA.toString(), 5), 
			new Bevanda(TipoBevanda.ACQUA.toString(), 2)));

	public PasseggeroPrioritario(String paramNome, String paramCognome, String paramNumeroPassaporto) {
		super(paramNome, paramCognome, paramNumeroPassaporto);
	};

	public void setDrink(TipoBevanda paramTipoBevanda) {
		lista
		// magazzinoDrink.put(thisBevande, null)
	}
}
