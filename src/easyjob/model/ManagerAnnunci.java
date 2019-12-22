package easyjob.model;

import java.util.List;

import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;

public class ManagerAnnunci {

	
	public synchronized List<Annuncio> searchAd(String ricerca){
		
		/*Ricerca gli annunci con il tag inserito*/
		return null;
	}
	
	public synchronized List<Annuncio> searchAdAdvanced (String ricercaAvanzata){
		
		/*Ricerca gli annunci in base alla città passata come parametro*/
		return null;
	}
	
	public synchronized List<Annuncio> visualizzaElencoAnnunci(Azienda azienda){
		
		/*Data un'azienda restituisce l'elenco degli annunci pubblicati*/
		return null;
	}
	
	public synchronized boolean pubblicaAnnuncio (Annuncio ann){
		/*Viene reso peristente un annuncio nel db*/
		return false;
	}
	
	public synchronized List<Annuncio> filterSearch (String data){
		
		/* Data una data vengono cercati gli annunci in base alla data selezionata*/
		return null;
	}
	
	public synchronized boolean removeAd (int idAnnuncio){
		
		/*Rimuove un annuncio in base all'id passato come parametro*/
		return false;
	}
	
	
	
}
