package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public static void main(String[] args) {
		List<Volo> tabellone = new ArrayList<Volo>();
		GestoreAeroporto.popolaTabellone(tabellone);
		
		

	}

}
