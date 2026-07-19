package app.interfaces;

import app.exeptions.VoloPienoException;

public interface Prenotabile {
	void prenota() throws VoloPienoException;
	void cancella();
};
