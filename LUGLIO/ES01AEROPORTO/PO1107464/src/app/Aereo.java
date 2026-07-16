package app;

public class Aereo {
	private String modello;
	private int capienza;
	
	public Aereo(String modello, int capienza) {
		this.modello = modello;
		this.capienza = capienza;
	}
	
	public int getCapienza() {
		return capienza;
	};
	
	public String getModello() {
		return modello;
	};
}
