package packageContoCorrente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner myScanner = new Scanner(System.in);
		List<Banca> listaBanche = new ArrayList<Banca>(List.of(new Banca("IntesaSanPaolo"), new Banca("Unicredit")));

		do {
			Banca thisSelectedBanca = null;
			ContoCorrente thisSelectedConto = null;
			int indexBancaScelto;

			Boolean isCorrectBancaIndex;
			Boolean isCorrectAzioneIndex;
			Boolean isAlreadyCliente = null;
			Boolean isQuestionAccountAnsweredCorrectly;
			Boolean isDuplicate;
			Boolean canShowAzioniMenu;

			do {
				System.out.println("Scegli in quale banca vuoi creare un conto?:");

				for (int i = 0; i < listaBanche.size(); i++) {
					Banca thisBanca = listaBanche.get(i);
					System.out.println(new String(Integer.toString(i)).concat(") ").concat(thisBanca.getBancaName()));
				}

				int inputIndex = Integer.parseInt(myScanner.nextLine());
				isCorrectBancaIndex = inputIndex < listaBanche.size();
				if (!isCorrectBancaIndex)
					continue;

				indexBancaScelto = inputIndex;
				thisSelectedBanca = listaBanche.get(indexBancaScelto);

				System.out.println(" ");
			} while (!isCorrectBancaIndex);

			do {
				System.out.println("[SI] possiedi già un conto? oppure \n[NO] quindi creiamo uno nuovo?");
				String risposta = myScanner.nextLine();

				isQuestionAccountAnsweredCorrectly = risposta.equals("SI") || risposta.equals("NO");
				if (!isQuestionAccountAnsweredCorrectly)
					continue;

				isAlreadyCliente = risposta.equals("SI");
			} while (!isQuestionAccountAnsweredCorrectly);

			if (!isAlreadyCliente) {
				do {
					try {
						System.out.println("\nPerfetto, creiamo un nuovo conto per te, \n come ti chiami?");
						String nomeNewTitolare = myScanner.nextLine();
						ContoCorrente thisContoCorrente = new ContoCorrente(nomeNewTitolare);
						thisSelectedBanca.aggiungiConto(thisContoCorrente);
						isDuplicate = false;
						try {
							thisSelectedConto = thisSelectedBanca.getContoCorrente(nomeNewTitolare);
						} catch (ContoNotFoundException e) {
							/* non serve perchè abbiamo già la certezza prima di aver creato il conto */
						}
					} catch (ContoDuplicatoException e) {
						System.out.println(e.getMessage());
						System.out.println("utilizza un altro nome perfavore che non esiste nel database:");
						isDuplicate = true;
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

			do {
				thisSelectedConto.printWelcome();
				System.out.println("vuoi prelevare [1] oppure versare [2]?");

				int azioneIndex = Integer.parseInt(myScanner.nextLine());

				isCorrectAzioneIndex = azioneIndex <= 2 && azioneIndex > 0;
				if (!isCorrectAzioneIndex) continue;

				do {
					canShowAzioniMenu = true;
					String nomeAzione = azioneIndex == 1 ? "prelevare" : "versare";
					System.out.println(new String(thisSelectedConto.getOwnerName()).concat(",quanto vuoi ")
							.concat(nomeAzione).concat(" oggi? \n (scrivi X se hai sbagliato azione)"));

					String inputScanner = myScanner.nextLine();
					
					if(inputScanner.equals("X") || inputScanner.equals("x")) {
						canShowAzioniMenu = false;
						continue;
					};
					
					double amount = Double.parseDouble(inputScanner);

					boolean selectedPrelevare = azioneIndex == 1;
					boolean selectedVersare = azioneIndex == 2;

					try {
						if (selectedPrelevare) thisSelectedConto.preleva(amount);
						if (selectedVersare) thisSelectedConto.versa(amount);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
				} while (canShowAzioniMenu);
			} while (!isCorrectAzioneIndex);

			myScanner.close();
		} while (true);

		/*
		 * ContoCorrente marioContoCorrente = new ContoCorrente("mario",500); Scanner
		 * myScanner = new Scanner(System.in);
		 * 
		 * do { marioContoCorrente.printWelcome();
		 * System.out.println("...allora, digita quanto vuoi prelevare oggi:");
		 * 
		 * double choosenImport = Double.parseDouble(myScanner.nextLine());
		 * 
		 * try { // marioContoCorrente.versa(choosenImport);
		 * 
		 * marioContoCorrente.preleva(choosenImport); } catch (Exception e) {
		 * System.out.println(e.getMessage()); } finally { System.out.println(" "); }
		 * 
		 * } while (true);
		 */
	}
}
