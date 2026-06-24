package aeroporto.passeggero;

import java.util.HashMap;

import java.util.Map;

// REWRITE TODO: REWRITE WITHOUT USING HASHMAP BUT WITH A CLASS

public class PasseggeroPrioritario extends Passeggero {
	private String choosedDrink;

	// static private Map<Bevande, Integer> magazzinoDrink = new HashMap<>(
	//		Map.of(Bevande.COCACOLA, 10, Bevande.FANTA, 2, Bevande.ACQUA, 2)
	/	);

	public PasseggeroPrioritario(String paramNome, String paramCognome, String paramNumeroPassaporto) {
		super(paramNome, paramCognome, paramNumeroPassaporto);
	};
	
	public void setDrink(Bevande thisBevande) {
	//	magazzinoDrink.put(thisBevande, null)
	}
}


