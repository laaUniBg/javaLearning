package sistemaScolastico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gestioneEsami.PossibiliEsami;
import studente.DuplicateStudenteException;
import studente.Studente;
import studente.StudenteLavoratore;
import studente.StudenteNonLavoratore;

public class Main {

	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		List<Studente> listaStudentiGestionaleUni = new ArrayList<Studente>();
		final int maxIdEsame = PossibiliEsami.values().length;

		Studente studente0;
		Studente studente1;
		Studente studente2;
		Studente studente3;
		Studente studente4;
		
		Random random = new Random();
		
		try {
			studente0 = new StudenteLavoratore(123456789, "Nome0", "Cognome0", "Capo0");
			studente1 = new StudenteLavoratore(223456789, "Nome1", "Cognome1", "Capo1");
			studente2 = new StudenteLavoratore(223456789, "Nome2", "Cognome2", "Capo2");
			studente3 = new StudenteNonLavoratore(323456789, "Nome2", "Cognome2");
			studente4 = new StudenteNonLavoratore(423456789, "Nome3", "Cognome3");

			for (Studente thisStudente : List.of(studente0, studente1, studente2, studente3, studente4)) {
				listaStudentiGestionaleUni.add(thisStudente);
				thisStudente.passaEsame(PossibiliEsami.values()[random.nextInt(0,maxIdEsame)], LocalDate.of(2026, random.nextInt(1,12+1), random.nextInt(1,27+1)));
				for(int i=0; i<3; i++) {
					if(random.nextBoolean()) {
						thisStudente.passaEsame(PossibiliEsami.values()[random.nextInt(0,maxIdEsame)], LocalDate.of(2026, random.nextInt(1,12+1), random.nextInt(1,27+1)));
					}
				}
			}
		} catch (DuplicateStudenteException e) {
			System.out.println("\n" + e.getMessage());
		}
		
		// TODO: Ricerca studente tramite matricola
		// TODO: println dello stato corrente del sistema
		

		myScanner.close();
	}

}
