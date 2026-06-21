package packageContoCorrente;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ContoCorrente marioContoCorrente = new ContoCorrente("mario",500);
		Scanner myScanner = new Scanner(System.in);
		
		do {
			marioContoCorrente.printWelcome();
			System.out.println("...allora, digita quanto vuoi prelevare oggi:");
			
			double choosenImport = Double.parseDouble(myScanner.nextLine());
			
			try {
				// marioContoCorrente.versa(choosenImport);
				
				marioContoCorrente.preleva(choosenImport);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				System.out.println(" ");
			}
		} while (true);
	}
}
