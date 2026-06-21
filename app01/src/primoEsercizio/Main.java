package primoEsercizio;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ContoCorrente marioContoCorrente = new ContoCorrente("mario");
		Scanner myScanner = new Scanner(System.in);
		
		do {
			System.out.println("digita quanto vuoi versare?");
			double choosenImport = Double.parseDouble(myScanner.nextLine());
			
			try {
				marioContoCorrente.versa(choosenImport);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				System.out.println(" ");
			}
		} while (true);
	}
}
