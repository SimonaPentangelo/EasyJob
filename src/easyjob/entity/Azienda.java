package easyjob.entity;

import java.util.Date;

public class Azienda extends Utente {

	//Parametri
	private String nomeAzienda;
	private String logoAzienda;
	private int numeroDipendenti;
	private String partitaIVA;
	private Date dataFondazione;
	private String indirizzoSede;
	private boolean banned;
	
	//Costruttore
	public Azienda(String username, String password, String email, int idUser, String nomeAzienda, 
			String logoAzienda, int numeroDipendenti, String partitaIVA, Date dataFondazione,
			String indirizzoSede, boolean banned) {
		super(username, password, email, idUser);
		this.nomeAzienda = nomeAzienda;
		this.logoAzienda = logoAzienda;
		this.numeroDipendenti = numeroDipendenti;
		this.partitaIVA = partitaIVA;
		this.dataFondazione = dataFondazione;
		this.indirizzoSede = indirizzoSede;
		this.banned = banned;
	}

	//Getters and setters
	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public String getLogoAzienda() {
		return logoAzienda;
	}

	public void setLogoAzienda(String logoAzienda) {
		this.logoAzienda = logoAzienda;
	}

	public int getNumeroDipendenti() {
		return numeroDipendenti;
	}

	public void setNumeroDipendenti(int numeroDipendenti) {
		this.numeroDipendenti = numeroDipendenti;
	}

	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	public Date getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(Date dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	public String getIndirizzoSede() {
		return indirizzoSede;
	}

	public void setIndirizzoSede(String indirizzoSede) {
		this.indirizzoSede = indirizzoSede;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	
	
}
