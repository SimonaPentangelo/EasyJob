package easyjob.entity;

public class Invito {

	//Parametri
	private String titolo;
	private String corpo;
	private int azienda;
	private int inoccupato;
	
	//Costruttore
	public Invito(String titolo, String corpo, int azienda, int inoccupato) {
		super();
		this.titolo = titolo;
		this.corpo = corpo;
		this.azienda = azienda;
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

	public int getAzienda() {
		return azienda;
	}

	public void setAzienda(int azienda) {
		this.azienda = azienda;
	}

	public int getInoccupato() {
		return inoccupato;
	}

	public void setInoccupato(int inoccupato) {
		this.inoccupato = inoccupato;
	}

	
}
