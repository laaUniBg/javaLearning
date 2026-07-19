package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import app.enums.TipoCoupon;
import app.exeptions.CodiceVoloNonValido;
import app.exeptions.VoloPienoException;
import app.volo.Volo;
import app.volo.VoloInternazionale;
import app.volo.VoloNazionale;

public class GestoreAeroporto {

	static private Volo binarySearchVolo(List<Volo> tabellone, String codiceVolo) {
		int min = 0;
		int max = tabellone.size()-1;
		
		while(min<=max) {
			final int middle = (min+max)/2;
			final Volo middleVolo = tabellone.get(middle);
			int strCmp = codiceVolo.compareTo(middleVolo.getCodiceVolo());
			
			if(strCmp == 0) return middleVolo;
			
			if(strCmp<0) {
				max = middle-1;
			} else if(strCmp>0){
				min = middle+1;
			}
		}
		
		return null;
	}
	
	static void popolaTabellone(List<Volo> tabellone) {
		Date data1 = new Date(2026-1900, 7, 30);
		Date data2 = new Date(2026-1900, 8, 1);
		
		Aereo boeing = new Aereo(2, "boeing 737"); // capienza piccola per favorire i test
		
		Volo v1 = new VoloNazionale("MXP100", data2, boeing);
		Volo v2 = new VoloNazionale("BGY200", data1, boeing);
		Volo v3 = new VoloInternazionale("BAR300", data1, boeing, false);
		Volo v4 = new VoloInternazionale("RAK400", data2, boeing, true);
		Volo v5 = new VoloInternazionale(v4, true);
		
		tabellone.addAll(List.of(v1, v2, v3, v4, v5));
	}; 
	
	private static void testRuntimeException() {
		Date dataProva = new Date(2026-1900, 7, 30);
		Aereo aereoProva = new Aereo(10, "nomeAereo");
		
		try {
			Volo voloConCodiceVuoto = new VoloNazionale(" ", dataProva, aereoProva);
		} catch(CodiceVoloNonValido e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Volo voloPaeseSconosciuto = new VoloInternazionale("PAR100", dataProva, aereoProva, true);
		} catch(CodiceVoloNonValido e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void testCheckedException(Volo voloDaTestare) {
		final int knownMax = voloDaTestare.getAereo().getCapienza();
		
		try {
			for(int i=0; i<knownMax; i++) {
				voloDaTestare.prenota();
			};
			voloDaTestare.prenota(); // questo supera il max quindi chiama VoloPienoException		
		} catch(VoloPienoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void testCalcolaPrezzo(List<Volo> tabellone) {
		for(Volo thisVolo : tabellone) {
			double thisPrezzo15percent = thisVolo.calcolaPrezzo(15.0);
			System.out.println("il prezzo del volo n." + thisVolo.getCodiceVolo() + " con 15% sconto è: " + Double.toString(thisPrezzo15percent));
			
			for(TipoCoupon thisCoupon : TipoCoupon.values()) {
				double thisPrezzoConCoupon = thisVolo.calcolaPrezzo(thisCoupon);
				System.out.println("il prezzo del volo n. " + thisVolo.getCodiceVolo() + " con coupon '" + thisCoupon.name() + "' è " + Double.toString(thisPrezzoConCoupon));
			}
		}
	}
	
	private static void testInstanceOfControlloDogana(List<Volo> tabellone) {
		for(Volo thisVolo : tabellone) {
			boolean isVoloInternazionale = thisVolo instanceof VoloInternazionale;
			if(!isVoloInternazionale) return;
			
			VoloInternazionale thisVoloInternazionale = (VoloInternazionale) thisVolo;
			thisVoloInternazionale.controlloDogana();
		}
	}
	
	private static void testOrdinamentoNaturale(List<Volo> tabellone) {
		System.out.println("PRIMA DELL'ORDINAMENTO: ");
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		}
		
		System.out.println("DOPO DELL'ORDINAMENTO: ");
		
		Collections.sort(tabellone);
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		}
	}
	
	private static void testBinarySearch(List<Volo> tabellone, String codiceVolo) {
		Volo thisVoloTrovato = GestoreAeroporto.binarySearchVolo(tabellone, codiceVolo);
		boolean isTrovato = thisVoloTrovato != null;
		if(isTrovato) {
			System.out.println("TEST BINARY SEARCH TROVATO VOLO");
			System.out.println(thisVoloTrovato.toString());
		} else {
			System.out.println("TEST BINARY SEARCH NON TROVATO");
		}
		return;
	}
	
	private static void testOrdinamentoData(List<Volo> tabellone) {
		Comparator<Volo> myCompData = new Comparator<Volo>() {
			@Override
			public int compare(Volo o1, Volo o2) {
				return o1.getData().compareTo(o2.getData());
			}
		};
		System.out.println("ORDINAMENTO PER DATA: ");
		Collections.sort(tabellone, myCompData);
		
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		}
		
	}
	
	public static void main(String[] args) {
		List<Volo> tabellone = new ArrayList<Volo>();
		GestoreAeroporto.popolaTabellone(tabellone);
		
		GestoreAeroporto.testRuntimeException();
		GestoreAeroporto.testCheckedException(tabellone.get(tabellone.size()-2));
		
		GestoreAeroporto.testCalcolaPrezzo(tabellone);
		
		GestoreAeroporto.testInstanceOfControlloDogana(tabellone);
		
		GestoreAeroporto.testOrdinamentoNaturale(tabellone);
		GestoreAeroporto.testBinarySearch(tabellone, "BAR300");
		GestoreAeroporto.testBinarySearch(tabellone, "PAR100");
		GestoreAeroporto.testOrdinamentoData(tabellone);
	}

}
