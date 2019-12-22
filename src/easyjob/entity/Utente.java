package easyjob.entity;

public class Utente {

	//Parametri
	private String username;
	private String password;
	private String email;
	private int idUser;
	
	//Costruttori
	public Utente(String username, String password, String email, int idUser) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.idUser = idUser;
	}
	
	public Utente() {
		
	}
	
	//Getters and setters
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
