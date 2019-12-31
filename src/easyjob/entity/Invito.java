package easyjob.entity;

public class Invito {

	//Parametri
	private String titolo;
	private String corpo;
	private int annuncio;
	private int inoccupato;
	
	//Costruttore
	public Invito(String titolo, String corpo, int annuncio, int inoccupato) {
		super();
		this.titolo = titolo;
		this.corpo = corpo;
		this.annuncio = annuncio;
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

	public int getInoccupato() {
		return inoccupato;
	}

	public void setInoccupato(int inoccupato) {
		this.inoccupato = inoccupato;
	}

	
}
