package easyjob.entity;

import java.util.Date;

public class Candidatura {

	//Parametri
	private String data;
	private int inoccupato;
	private int annuncio;
	
	//Costruttore
	public Candidatura(String data, int inoccupato, int annuncio) {
		super();
		this.data = data;
		this.inoccupato = inoccupato;
		this.annuncio = annuncio;
	}
	
	public Candidatura() {
		
	}

	//Getters and setters
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getInoccupato() {
		return inoccupato;
	}

	public void setInoccupato(int inoccupato) {
		this.inoccupato = inoccupato;
	}

	public int getAnnuncio() {
		return annuncio;
	}

	public void setAnnuncio(int annuncio) {
		this.annuncio = annuncio;
	}
	
	
}
