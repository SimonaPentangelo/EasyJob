package easyjob.entity;

public class Invito {

	//Parametri
	private String titolo;
	private String corpo;
	private Azienda azienda;
	private Inoccupato inoccupato;
	
	//Costruttore
	public Invito(String titolo, String corpo, Azienda azienda, Inoccupato inoccupato) {
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

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public Inoccupato getInoccupato() {
		return inoccupato;
	}

	public void setInoccupato(Inoccupato inoccupato) {
		this.inoccupato = inoccupato;
	}

	
}
