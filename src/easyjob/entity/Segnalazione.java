package easyjob.entity;

import java.util.Date;

public class Segnalazione {
	
	private String titolo;
	private String corpo;
	private Date data;
	private Azienda azienda;
	private Moderatore moderatore;

	//Costruttore
	public Segnalazione(String titolo, String corpo, Date data, Azienda azienda, Moderatore moderatore) {
		super();
		this.titolo = titolo;
		this.corpo = corpo;
		this.data = data;
		this.azienda = azienda;
		this.moderatore = moderatore;
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
	
	public Azienda getAzienda() {
		return azienda;
	}
	
	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}
	
	public Moderatore getModeratore() {
		return moderatore;
	}
	
	public void setModeratore(Moderatore moderatore) {
		this.moderatore = moderatore;
	}
}
