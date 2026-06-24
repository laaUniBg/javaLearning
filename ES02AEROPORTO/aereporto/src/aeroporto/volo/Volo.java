package aeroporto.volo;

import java.util.Date;

public class Volo {
	private Date dataVolo;
	private String partenza;
	private String destinazione;
	
	public Volo(Date paramDataVolo, String paramPartenza, String paramDestinazione) {
		this.dataVolo = paramDataVolo;
		this.partenza = paramPartenza;
		this.destinazione = paramDestinazione;
	}
};
