package easyjob.entity;

public class Invito {

	//Parametri
	private String titolo;
	private String corpo;
	private int annuncio;
	private int azienda;
	private String nomeAzienda;
	private int inoccupato;
	
	//Costruttore
	public Invito(String titolo, String corpo, int annuncio, int azienda, String nomeAzienda, int inoccupato) {
		super();
		this.titolo = titolo;
		this.corpo = corpo;
		this.annuncio = annuncio;
		this.azienda = azienda;
		this.nomeAzienda = nomeAzienda;
		this.inoccupato = inoccupato;
	}

	public Invito() {
		
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

	public int getAnnuncio() {
		return annuncio;
	}

	public void setAnnuncio(int annuncio) {
		this.annuncio = annuncio;
	}
	
	public int getAzienda() {
		return azienda;
	}
	
	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}
	
	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}
	
	public int getInoccupato() {
		return inoccupato;
	}

	public void setInoccupato(int inoccupato) {
		this.inoccupato = inoccupato;
	}

	
}
