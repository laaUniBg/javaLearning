package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import app.enums.EnumCodiceAeroporto;
import app.exceptions.CodiceIncorrettoException;
import app.exceptions.VoloPienoException;
import app.volo.Volo;
import app.volo.VoloInternazionale;
import app.volo.VoloNazionale;

public class GestoreAeroporto {
	private static void popolaTabellone(List<Volo> tabellone) {
		Date data1 = new Date(2026-1900, 7, 30);
		Date data2 = new Date(2026-1900, 8, 01);
		
		Aereo boeing = new Aereo(2, "boeing 737");
		
		Volo v1 = new VoloNazionale("BGY100", data2, boeing);
		Volo v2 = new VoloInternazionale("BRC200", data1, boeing, EnumCodiceAeroporto.SPAGNA.needsPassport);
		Volo v3 = new VoloInternazionale("RAK300", data2, boeing, EnumCodiceAeroporto.MAROCCO.needsPassport);
		Volo v4 = new VoloInternazionale(v3, EnumCodiceAeroporto.MAROCCO.needsPassport);
		
		tabellone.addAll(List.of(v1, v2, v3, v4));
	}
	
	private static void testInstanceOf(List<Volo> tabellone) {
		for(Volo thisVolo : tabellone) {
			boolean isInternazionale = thisVolo instanceof VoloInternazionale;
			if(!isInternazionale) continue;
				
			VoloInternazionale thisVoloInternazionale = (VoloInternazionale) thisVolo;
			thisVoloInternazionale.controllaPassaporto();
		}
	};
	
	private static void testOrdinamentoNaturale(List<Volo> tabellone) {
		// PRIMA DELL'ORDINAMENTO
		System.out.println("PRIMA DELL'ORDINAMENTO");
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		}
		
		System.out.println("DOPO DELL'ORDINAMENTO");
		Collections.sort(tabellone);
		
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		}
	}
	
	
	private static Volo binarySearchVolo(List<Volo> tabellone, String codiceVolo) {
		int min = 0;
		int max = tabellone.size()-1;
		
		while(min<=max) {
			int middle = (min+max)/2;
			Volo middleVolo = tabellone.get(middle);
			int strCmp = middleVolo.getCodiceVolo().compareTo(codiceVolo);
			if(strCmp == 0) return middleVolo;
			
			if(strCmp<0) {
				max = middle-1;
			} else {
				min = middle+1;
			}
		}
		
		return null;
	}
	
	private static void testOrdinamentoPerData(List<Volo> tabellone) {
		Comparator<Volo> compData = new Comparator<Volo>() {
			
			@Override
			public int compare(Volo o1, Volo o2) {
				return o1.getData().compareTo(o2.getData());
			}
		};
		
		Collections.sort(tabellone, compData);
		
		for(Volo thisVolo : tabellone) {
			System.out.println(thisVolo.toString());
		}
	}
	
	private static void testCheckedException(Volo argVolo) {
		final int knownMax = argVolo.getAereo().getCapienza();
		
		try {
			for(int i=0; i<knownMax; i++) {
				argVolo.prenota();
			}
			argVolo.prenota();
		} catch(VoloPienoException e) {
			System.out.println("CHECKED EXCEPTION");
		}
	}
	
	private static void testCodiceIncorrettoException() {
		Date d1 = new Date(2026-1900, 9, 1);
		Aereo a1 = new Aereo(10, "airbus Mini");
		try {			
			Volo v1 = new VoloNazionale("IT", d1, a1);
		} catch(CodiceIncorrettoException e) {
			System.out.println(e.getCause());
		}
		
	}
	

	
	public static void main(String[] args) {
		List<Volo> tabellone = new ArrayList<Volo>();
		GestoreAeroporto.popolaTabellone(tabellone);
		GestoreAeroporto.testInstanceOf(tabellone);
		
		GestoreAeroporto.testOrdinamentoNaturale(tabellone);
		Volo v1=GestoreAeroporto.binarySearchVolo(tabellone, "RAK300");
		System.out.println("BINARYSEARCH: " + v1 == null ? "NON TROVATO VOLO": "ABBIAMO TROVATO IL VOLO");
		
		GestoreAeroporto.testCheckedException(v1);
		// GestoreAeroporto.testCodiceIncorrettoException();
		
		GestoreAeroporto.testOrdinamentoPerData(tabellone);
		
		
		
	}

}
