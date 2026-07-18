package app.exceptions;

public class CodiceIncorrettoException extends RuntimeException {
	private static final long serialVersionUID = -4896796196458405356L;

	public CodiceIncorrettoException(String str) {
		super(str);
	}
}
