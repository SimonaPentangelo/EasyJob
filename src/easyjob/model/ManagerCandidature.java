package easyjob.model;

import java.util.List;

import easyjob.entity.Annuncio;
import easyjob.entity.Candidatura;
import easyjob.entity.Inoccupato;

public class ManagerCandidature {

	
	private synchronized boolean isAlreadyCandidate (int idInoccupato,int idAnnuncio){
		
		/*Controlla se il candidato con l'id passata � gi� candidato per l'annuncio
		 * con id passato*/
		
		return false;
	}
	
	
	public synchronized List<Candidatura> visualizzaCandidature (Inoccupato inocc){
		
		/*Dato un inoccupato, si restituiscono le candidature effettuate*/
		
		return null;
	}
	
	public synchronized boolean candidate (Inoccupato inocc,Annuncio ann){
		
		//Si controlla se l'utente si � gia candidato, se non lo � (prossimo commento) 
		/*Dato un inoccupato e un annuncio memorizza una candidatura*/
		
		return false;
	}
	
	
}
