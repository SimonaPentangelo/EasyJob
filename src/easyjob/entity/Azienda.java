package easyjob.entity;

import java.util.Date;

public class Azienda extends Utente {

	//Parametri
	private String nomeAzienda;
	private String logoAzienda;
	private int numeroDipendenti;
	private String partitaIVA;
	private String dataFondazione;
	private String descrizione;
	private String indirizzoSede;
	private boolean banned;
	
	//Costruttori
	public Azienda(String username, String password, String email, int idUser, String nomeAzienda, 
			String logoAzienda, int numeroDipendenti, String partitaIVA, String dataFondazione,
			String descrizione, String indirizzoSede, boolean banned) {
		super(username, password, email, idUser);
		this.nomeAzienda = nomeAzienda;
		this.logoAzienda = logoAzienda;
		this.numeroDipendenti = numeroDipendenti;
		this.partitaIVA = partitaIVA;
		this.dataFondazione = dataFondazione;
		this.descrizione = descrizione;
		this.indirizzoSede = indirizzoSede;
		this.banned = banned;
	}
	
	public Azienda() {
		super();
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

	public String getDataFondazione() {
		return dataFondazione;
	}

	public void setDataFondazione(String dataFondazione) {
		this.dataFondazione = dataFondazione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
