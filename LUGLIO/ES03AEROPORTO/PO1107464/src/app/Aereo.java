package app;

public class Aereo {
	private int capienza;
	private String modello;
	
	public Aereo(int capienza, String modello) {
		this.capienza = capienza;
		this.modello = modello;
	}
	
	public int getCapienza() {
		return this.capienza;
	}
	
	public String getModello() {
		return this.modello;
	}
}
