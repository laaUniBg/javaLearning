package Studente;

class DuplicateStudenteException extends Exception {
	private static final long serialVersionUID = -4409833919988034285L;

	public DuplicateStudenteException(String message) {
		super(message);
	}
}