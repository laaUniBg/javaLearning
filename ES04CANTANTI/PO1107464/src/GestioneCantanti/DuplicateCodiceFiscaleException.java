package GestioneCantanti;

class DuplicateCodiceFiscaleException extends Exception {
	private static final long serialVersionUID = 4602214872874565042L;

	public DuplicateCodiceFiscaleException(String paramMessage) {
		super(paramMessage);
	}
}