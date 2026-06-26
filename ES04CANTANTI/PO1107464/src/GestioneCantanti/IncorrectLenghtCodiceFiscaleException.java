package GestioneCantanti;

class IncorrectLenghtCodiceFiscaleException extends RuntimeException {
	private static final long serialVersionUID = 1106131771319488743L;

	public IncorrectLenghtCodiceFiscaleException(String paramMessage) {
		super(paramMessage);
	};
}