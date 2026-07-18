package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.volo.Volo;
import app.volo.VoloInternazionale;
import app.volo.VoloNazionale;

public class GestoreAeroporto {
	static private Volo binarySearchVolo(List<Volo> tabellone, String codiceVolo) {
		int minIndex=0;
		int maxIndex=tabellone.size()-1;
		
		while(minIndex<=maxIndex) {
			int middleIndex=(minIndex+maxIndex)/2;
			Volo middleVolo = tabellone.get(middleIndex);
			String middleVoloCodice = middleVolo.getCodiceVolo();
			
			int strCmp = middleVoloCodice.compareToIgnoreCase(codiceVolo);
			if(strCmp==0) return middleVolo;
			
			if(strCmp>1) {
				minIndex = middleIndex+1;
			} else {
				maxIndex = middleIndex-1;
			}
		}
		return null;
	}
	
	static void popolaTabellone(List<Volo> tabellone) {
		Date data1 = new Date(2026-1900, 7, 30);
		Date data2 = new Date(2026-1900, 8, 01);
		
		Aereo boeing = new Aereo(2, "boeing 737");
		Aereo airbus = new Aereo(2, "airbus A320");
		
		Volo v1 = new VoloNazionale("MXP100", data1, boeing);
		Volo v2 = new VoloNazionale("BGY200", data2, airbus);
		Volo v3 = new VoloInternazionale("BAR100", data1, boeing, false);
		Volo v4 = new VoloInternazionale("RAK200", data2, airbus, true);
		Volo v5 = new VoloInternazionale(v4, true);
		
		tabellone.addAll(List.of(v1,v2,v3,v4, v5));
	}
	
	// TODO: Exceptions
	
	
	public static void main(String[] args) {
		List<Volo> tabellone = new ArrayList<Volo>();
		GestoreAeroporto.popolaTabellone(tabellone);

	}

}
