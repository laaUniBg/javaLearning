package studente;

class IncorrectLengthException extends RuntimeException {
	private static final long serialVersionUID = 3200059766726298874L;

	public IncorrectLengthException(String message) {
		super(message);
	}
}