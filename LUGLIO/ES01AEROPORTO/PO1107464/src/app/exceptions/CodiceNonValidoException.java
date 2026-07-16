package app.exceptions;

public class CodiceNonValidoException extends RuntimeException {
	private static final long serialVersionUID = -930334568172581982L;

	public CodiceNonValidoException(String msg) {
		super(msg);
	};
}
