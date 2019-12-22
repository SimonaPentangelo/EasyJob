package easyjob.entity;

import java.util.Date;

public class Inoccupato extends Utente {

	//Parametri
		private String nome;
		private String cognome;
		private String residenza;
		private String dataNascita;
		private String curriculum;
		private String città;
		
	//Costruttore
	public Inoccupato(String username, String password, String email, int idUser, String nome, String cognome,
			String residenza, String dataNascita, String curriculum,String città) {
		super(username, password, email, idUser);
		this.nome = nome;
		this.cognome = cognome;
		this.residenza = residenza;
		this.dataNascita = dataNascita;
		this.curriculum = curriculum;
		this.città = città;
	}
	
	public Inoccupato() {
		super();
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

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}
	
	public String getCittà (){
		return città;
	}
	
	public void setCittà (String città){
		this.città = città;
	}
}
