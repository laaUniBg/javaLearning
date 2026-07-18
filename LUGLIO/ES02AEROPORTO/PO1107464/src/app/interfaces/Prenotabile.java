package app.interfaces;

import app.exceptions.VoloPienoException;

public interface Prenotabile {
	public void prenota() throws VoloPienoException;
	public void cancella();
};
