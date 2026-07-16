package app;

/*
 * Interface:
 * Interfaccia è un "contratto formale"
 * definisce i metodi che le classi derivate devono implementare
 * 1. i metodi sono public e abstract by default
 * 2. non puoi inizializzare l'interfaccia ma puoi solo usarla come contratto per le classi derivate
 * 3. permette di realizzare il poliformismo perchè una classe derivata può usare più interfaccie
 * 
 * Per convenzione si usa il suffisso -able per le intefaccie
 * esempio: Runnable, Readable, Callable e cosi via...
 */

public interface Prenotabile {
	void prenotaPosto(Passeggero p) throws PostoNonDisponibileException;
	boolean cancellaPrenotazione(Passeggero p);
};
