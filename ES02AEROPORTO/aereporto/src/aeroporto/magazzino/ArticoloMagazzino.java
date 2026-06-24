package aeroporto.magazzino;

public class ArticoloMagazzino {
	private String nomeProdotto;
	private int quantitaProdotto;
	
	public ArticoloMagazzino(String paramNomeProdotto, int paramQuantitaProdotto) {
		this.setNomeProdotto(paramNomeProdotto);
		this.setQuantitaProdotto(paramQuantitaProdotto);
	}
	
	private void setNomeProdotto(String paramNomeProdotto) {
		this.nomeProdotto = paramNomeProdotto;
	}
	
	private void setQuantitaProdotto(int paramQuantitàProdotto) {
		this.quantitaProdotto = paramQuantitàProdotto;
	}
}
