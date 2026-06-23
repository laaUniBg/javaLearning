package bancaPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		List<Banca> listaBanche = new ArrayList<Banca>(List.of(new Banca("IntesaSanPaolo"), new Banca("Unicredit")));

		while (true) {
			Banca thisSelectedBanca = null;
			ContoCorrente thisSelectedConto = null;
			int indexBancaScelto;

			Boolean isCorrectBancaIndex;
			Boolean isQuestionAccountAnsweredCorrectly;
			Boolean isAlreadyCliente = null;
			Boolean isDuplicate;

			// 1. SELEZIONE BANCA
			do {
				System.out.println("Scegli in quale banca vuoi creare un conto?:");

				for (int i = 0; i < listaBanche.size(); i++) {
					Banca thisBanca = listaBanche.get(i);
					System.out.println(i + ") " + thisBanca.getBancaName());
				}

				int inputIndex = Integer.parseInt(myScanner.nextLine());

				isCorrectBancaIndex = inputIndex < listaBanche.size() && inputIndex >= 0;
				if (!isCorrectBancaIndex)
					continue;

				indexBancaScelto = inputIndex;
				thisSelectedBanca = listaBanche.get(indexBancaScelto);

				System.out.println(" ");
			} while (!isCorrectBancaIndex);

			// 2. DIALOGO CLIENTE NUOVO O VECCHIO
			do {
				System.out.println("[SI] possiedi già un conto? oppure \n[NO] quindi creiamo uno nuovo?");
				String risposta = myScanner.nextLine().toUpperCase();

				isQuestionAccountAnsweredCorrectly = risposta.equals("SI") || risposta.equals("NO");
				if (!isQuestionAccountAnsweredCorrectly)
					continue;

				isAlreadyCliente = risposta.equals("SI");
			} while (!isQuestionAccountAnsweredCorrectly);

			// 3. REGISTRAZIONE O ACCESSO
			if (!isAlreadyCliente) {
				do {
					try {
						System.out.println("\nPerfetto, creiamo un nuovo conto per te, \ncome ti chiami?");
						String nomeNewTitolare = myScanner.nextLine();
						ContoCorrente thisContoCorrente = new ContoCorrente(nomeNewTitolare);
						thisSelectedBanca.aggiungiConto(thisContoCorrente);
						isDuplicate = false;
						thisSelectedConto = thisSelectedBanca.getContoCorrente(nomeNewTitolare);
					} catch (ContoDuplicatoException e) {
						System.out.println(e.getMessage());
						System.out.println("utilizza un altro nome per favore che non esiste nel database:");
						isDuplicate = true;
					} catch (ContoNotFoundException e) {
						isDuplicate = false;
					}
				} while (isDuplicate);
			} else {
				do {
					System.out.println("\nPerfetto, come ti chiami cosi accediamo al tuo conto?");
					String nomeTitolare = myScanner.nextLine();
					try {
						thisSelectedConto = thisSelectedBanca.getContoCorrente(nomeTitolare);
						isQuestionAccountAnsweredCorrectly = true;
					} catch (ContoNotFoundException e) {
						System.out.println(e.getMessage());
						System.out.println("scegli un nomeTitolare già esistente visto che sei già cliente");
						isQuestionAccountAnsweredCorrectly = false;
					}
				} while (!isQuestionAccountAnsweredCorrectly);
			}

			// 4. MENU OPERAZIONI
			boolean continuaMenuAzioni = true;
			while (continuaMenuAzioni) {
				thisSelectedConto.printWelcome();
				System.out.println("vuoi prelevare [1], versare [2] oppure uscire/cambiare banca [X]?");

				String inputScelta = myScanner.nextLine();

				if (inputScelta.equalsIgnoreCase("X")) {
					continuaMenuAzioni = false;
					break;
				}

				int azioneIndex;
				try {
					azioneIndex = Integer.parseInt(inputScelta);
				} catch (NumberFormatException e) {
					System.out.println("Scelta non valida!");
					continue;
				}

				if (azioneIndex != 1 && azioneIndex != 2) {
					System.out.println("Azione non valida! Scegli 1 o 2.");
					continue;
				}

				String nomeAzione = (azioneIndex == 1) ? "prelevare" : "versare";
				System.out.println(thisSelectedConto.getOwnerName() + ", quanto vuoi " + nomeAzione
						+ " oggi?\n(scrivi X per annullare l'azione)");

				String inputImporto = myScanner.nextLine();

				if (inputImporto.equalsIgnoreCase("X")) {
					System.out.println("Azione annullata.\n");
					continue;
				}

				try {
					double amount = Double.parseDouble(inputImporto);
					if (azioneIndex == 1) {
						thisSelectedConto.preleva(amount);
					} else {
						thisSelectedConto.versa(amount);
					}
				} catch (NumberFormatException e) {
					System.out.println("Errore: Inserisci un numero valido.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				System.out.println();
			}
		}

		// myScanner.close();
	}
}