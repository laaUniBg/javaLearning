package app.exeptions;

public class CodiceVoloNonValido extends RuntimeException {
	private static final long serialVersionUID = 6911400670058458953L;

	public CodiceVoloNonValido(String msg) {
		super(msg);
	};
}
