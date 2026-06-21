package packageContoCorrente;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		ContoCorrente marioContoCorrente = new ContoCorrente("mario");
		Scanner myScanner = new Scanner(System.in);
		
		do {
			System.out.println(
					new String("buongiorno ")
						.concat(marioContoCorrente.getOwnerName())
						.concat("! \noggi possiedi nel tuo conto ")
						.concat(String.valueOf(marioContoCorrente.getSaldo()))
						.concat(" euro (€) \n...allora, digita quanto vuoi versare oggi?")
				);
			
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
