package easyjob.entity;

import java.util.Date;

public class Inoccupato extends Utente {

	//Parametri
		private String nome;
		private String cognome;
		private String residenza;
		private Date dataNascita;
		private String curriculum;
		
	//Costruttore
	public Inoccupato(String username, String password, String email, int idUser, String nome, String cognome,
			String residenza, Date dataNascita, String curriculum) {
		super(username, password, email, idUser);
		this.nome = nome;
		this.cognome = cognome;
		this.residenza = residenza;
		this.dataNascita = dataNascita;
		this.curriculum = curriculum;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	
	
}
