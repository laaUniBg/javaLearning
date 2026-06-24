package aeroporto.boardingPass;

import java.util.ArrayList;
import java.util.List;

public class BoardingPass {
	private Volo thisVolo;
	
	public BoardingPass(Volo paramThisVolo) {
		this.setThisVolo(paramThisVolo);
	}
	
	private void setThisVolo(Volo paramVolo) {
		this.thisVolo = paramVolo;
	}
}
