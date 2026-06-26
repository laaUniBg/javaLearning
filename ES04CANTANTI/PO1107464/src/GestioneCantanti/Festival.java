package GestioneCantanti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Festival {
	private List<Canzone> sequenzaCanzoni = new ArrayList<>();
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private Set<SpettatoreRegular> listaSpettatoriRegular = new HashSet<SpettatoreRegular>();
	private Set<SpettatoreVip> listaSpettatoriVip = new HashSet<SpettatoreVip>();
	
	public void addSpettatore(String paramCodiceFiscale, tipiSpettatoriEnum tipoSpettatore) {
		Spettatore thisAddedSpettatore;
		if(tipoSpettatore.equals(tipoSpettatore.VIP)) {
			thisAddedSpettatore = new SpettatoreVip(paramCodiceFiscale);
			boolean isDuplicato = listaSpettatoriVip.contains(thisAddedSpettatore);
			if(!isDuplicato) {
				listaSpettatoriVip.add((SpettatoreVip) thisAddedSpettatore);
			}
		} 
		if(tipoSpettatore.equals(tipoSpettatore.REGULAR)) {
			thisAddedSpettatore = new SpettatoreRegular(paramCodiceFiscale);
			boolean isDuplicato = listaSpettatoriRegular.contains(thisAddedSpettatore);
			if(!isDuplicato) {
				listaSpettatoriRegular.add((SpettatoreRegular) thisAddedSpettatore);
			}
		}
	}
	
}
