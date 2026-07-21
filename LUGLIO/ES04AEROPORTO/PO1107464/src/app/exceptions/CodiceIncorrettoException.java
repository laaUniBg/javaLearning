package app.exceptions;

public class CodiceIncorrettoException extends RuntimeException {
	private static final long serialVersionUID = 3027686609812751004L;
	
	public CodiceIncorrettoException(String str) {
		super(str);
	}
}
