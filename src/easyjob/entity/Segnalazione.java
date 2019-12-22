package easyjob.entity;

import java.util.Date;

public class Segnalazione {
	
	private String titolo;
	private String corpo;
	private Date data;
	private int azienda;
	private int moderatore;

	//Costruttore
	public Segnalazione(String titolo, String corpo, Date data, int azienda, int moderatore) {
		super();
		this.titolo = titolo;
		this.corpo = corpo;
		this.data = data;
		this.azienda = azienda;
		this.moderatore = moderatore;
	}
	
	public Segnalazione() {
		
	}

	//Getters and setters
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getCorpo() {
		return corpo;
	}
	
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public int getAzienda() {
		return azienda;
	}
	
	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}
	
	public int getModeratore() {
		return moderatore;
	}
	
	public void setModeratore(int moderatore) {
		this.moderatore = moderatore;
	}
}
