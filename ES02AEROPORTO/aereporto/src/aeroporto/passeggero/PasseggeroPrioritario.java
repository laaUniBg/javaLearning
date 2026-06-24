package aeroporto.passeggero;

import java.util.HashMap;
import java.util.Map;

public class PasseggeroPrioritario extends Passeggero {
	private String choosedDrink;

	static private Map<String, Integer> magazzinoDrink = new HashMap<>(Map.of("Coca", 10, "Fanta", 2, "Acqua", 2));

	public PasseggeroPrioritario(String paramNome, String paramCognome, String paramNumeroPassaporto) {
		super(paramNome, paramCognome, paramNumeroPassaporto);
	};
}
