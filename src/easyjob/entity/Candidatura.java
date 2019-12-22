package easyjob.entity;

import java.util.Date;

public class Candidatura {

	//Parametri
	private Date data;
	private Inoccupato inoccupato;
	private Annuncio annuncio;
	
	//Costruttore
	public Candidatura(Date data, Inoccupato inoccupato, Annuncio annuncio) {
		super();
		this.data = data;
		this.inoccupato = inoccupato;
		this.annuncio = annuncio;
	}
	
	public Candidatura() {
		
	}

	//Getters and setters
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Inoccupato getInoccupato() {
		return inoccupato;
	}

	public void setInoccupato(Inoccupato inoccupato) {
		this.inoccupato = inoccupato;
	}

	public Annuncio getAnnuncio() {
		return annuncio;
	}

	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}
	
	
}
