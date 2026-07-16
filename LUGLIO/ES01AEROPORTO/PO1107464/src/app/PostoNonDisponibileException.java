package app;

/*
 * EXCEPTION è checked:
 * se è prevedibile come scenario... 
 * obbliga gestirlo con try-catch
 * oppure fare la propagazione dei throws
 */

public class PostoNonDisponibileException extends Exception {
	private static final long serialVersionUID = -6820353631786604170L;

	public PostoNonDisponibileException(String message) {
		super(message);
	};
}
