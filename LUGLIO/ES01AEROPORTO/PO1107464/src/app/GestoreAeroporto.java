package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import app.Volo.Volo;
import app.Volo.VoloInternazionale;
import app.Volo.VoloNazionale;
import app.enums.TipoCoupon;
import app.enums.TipoPosto;
import app.exceptions.CodiceNonValidoException;
import app.exceptions.VoloPienoException;

public class GestoreAeroporto {
	private static Volo binarySearchVolo(List<Volo> lista, String codiceCercato) {
		int low = 0;
		int high = lista.size()-1;
		
		while(low<=high) {
			int middleIndex = (high+low)/2;
			Volo middleVolo = lista.get(middleIndex);
			int strcmp = middleVolo.getCodiceVolo().compareTo(codiceCercato);
			
			if(strcmp == 0) return middleVolo;
			
			if(strcmp > 0) {
				high = middleIndex-1;
			}
			
			if(strcmp < 0) {
				low = middleIndex+1;
			}
		}
		
		return null;
	}
	
	private static void popolaDatabase(List<Volo> tabellone) {
		Aereo boeing = new Aereo("Boeing 737", 2); // numero basso di posti per testare facilmente le exception
		
		Date data1 = new Date(2026-1900, 7, 30);
		Date data2 = new Date(2026-1900, 8, 3);
		
		Volo v1 = new VoloNazionale("MXP100", data1, boeing);
		Volo v2 = new VoloNazionale("BGY200", data2, boeing);
		Volo v3 = new VoloInternazionale("BAR100", data1, boeing, false);
		Volo v4 = new VoloInternazionale("RAK100", data2, boeing, true);
		
		tabellone.add(v1);
		tabellone.add(v2);
		tabellone.add(v3);
		tabellone.add(v4);
	}
	
	private static void testVoloPienoException(Volo paramVolo, int knownMax) {
		boolean isCatchRunned = false;
		
		do {
			try {
				for(int i=0; i<knownMax; i++) {
					System.out.println("PRENOTAZIONE PASSEGGERO: " + paramVolo.toString());
					paramVolo.prenota();
				}
			} catch(VoloPienoException e) {
				isCatchRunned = true;
				System.out.println("ECCEZZIONE CATTURATA: " + e.getMessage());
			}
		} while(isCatchRunned == false);
	}
	
	private static void testRuntimeException() {
		try {
			Volo myVolo = new VoloNazionale(" ", new Date(2025-1900, 5, 1), new Aereo("airbus", 60));
		} catch(CodiceNonValidoException e) {
			System.out.println("RUNTIME EXCEPTION: " + e.getMessage());
		}
	}
	
	private static void testInstanceOf(List<Volo> tabellone) {
		for(Volo thisVolo : tabellone) {
			boolean isVoloInternazionale = thisVolo instanceof VoloInternazionale;
			if(!isVoloInternazionale) continue;
			
			VoloInternazionale thisVoloInternazionale = (VoloInternazionale) thisVolo;
			thisVoloInternazionale.effettuaControlloDogana();
		}
	}

	private static void testOrdinamentoNaturale(List<Volo> tabellone) {
		System.out.println("PRIMA DELL'ORDINAMENTO NATURALE");
		
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		}
		
		System.out.println("DOPO ORDINAMENTO NATURALE");
		
		Collections.sort(tabellone);
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		}
	};
	
	static private void testBinarySearch(List<Volo> tabellone, String knownCodiceCercato) {
		Volo risultato = binarySearchVolo(tabellone, knownCodiceCercato);
		if(risultato != null) {
			System.out.println("trovato volo con binary search: " + risultato.toString());
		} else {
			System.out.println("per definizione il nostro codiceCercato: " + knownCodiceCercato + " deve essere presente nel array... se vedi questo messaggio magari non hai ordinato prima i valori");
		};
	}
	
	static private void testOrdinamentoPersonalizzato(List<Volo> tabellone) {
		System.out.println("ORDINAMENTO PERSONALIZZATO (PER DATA)");
		
		Comparator<Volo> comparatorData = new Comparator<Volo>() {
			@Override
			public int compare(Volo o1, Volo o2) {
				return o1.getData().compareTo(o2.getData()); 
			};
		};
		
		Collections.sort(tabellone, comparatorData);
		
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		};
	}

	private static void testCalcoloPrezzi(List<Volo> tabellone) {
		for(Volo thisVolo : tabellone) {
			double thisPrezzo = thisVolo.calcolaPrezzo(TipoCoupon.ORO30.name(), TipoPosto.ECONOMY);
			String strTipoVolo = thisVolo instanceof VoloNazionale ? "VoloNazionale" : "VoloInternazionale";
			System.out.println("TEST CALCOLO PREZZI - il " + strTipoVolo + "n. " + thisVolo.getCodiceVolo() + " costa " + Double.toString(thisPrezzo) + "euro");
		};
	}
	
	public static void main(String[] args) {
		List<Volo> tabellone = new ArrayList<Volo>();
		GestoreAeroporto.popolaDatabase(tabellone);
		
		Volo v1 = tabellone.get(0);
		int knownMax = v1.getAereo().getCapienza();
		GestoreAeroporto.testVoloPienoException(v1, knownMax);
		
		GestoreAeroporto.testRuntimeException();
		
		GestoreAeroporto.testInstanceOf(tabellone);

		GestoreAeroporto.testCalcoloPrezzi(tabellone);
		
		GestoreAeroporto.testOrdinamentoNaturale(tabellone);
		
		String wantedCodiceVolo = tabellone.get(tabellone.size()-2).getCodiceVolo();
		GestoreAeroporto.testBinarySearch(tabellone, wantedCodiceVolo);
		
		GestoreAeroporto.testOrdinamentoPersonalizzato(tabellone);
	}
}
