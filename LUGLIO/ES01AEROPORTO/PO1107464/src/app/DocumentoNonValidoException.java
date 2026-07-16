package app;

/*
 * RuntimeException è unchecked:
 * non obbliga l'uso di trycatch
 * si usa per errori di logica, situazione dove se il codice è scritto bene non dovrebbe chiamare questa exception
 */
public class DocumentoNonValidoException extends RuntimeException {
	private static final long serialVersionUID = -5920104408460914245L;

	public DocumentoNonValidoException(String message) {
		super(message);
	};
}
