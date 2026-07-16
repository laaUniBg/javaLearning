package app;
import app.exceptions.VoloPienoException;

public interface Prenotabile {
	void prenota() throws VoloPienoException;
	void cancella();
};
