package easyjob.model;

import java.util.List;

import easyjob.entity.Segnalazione;

public class ManagerSegnalazioni {

	public synchronized List<Segnalazione> visualizzaElencoSegnalazioni (){
		
		/*Select nel db di tutte le segnalazioni*/
		return null;
		
	}
	
	public synchronized boolean segnalaUtente (Segnalazione s){
		/* Nella segnalazione c'è l'azienda segnalata e il moderatore che l'ha effettuata
		 * quindi la si inserisce per renderla peristente*/
		return false;
	}
	
	
}
