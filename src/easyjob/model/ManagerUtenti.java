package easyjob.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import easyjob.entity.Azienda;
import easyjob.entity.Inoccupato;
import easyjob.entity.Utente;

public class ManagerUtenti {

	public static final String TABLE_INOCCUPATO= "Inoccupato";
	public static final String TABLE_AZIENDA= "Azienda";
	public static final String TABLE_MODERATORE= "Moderatore";
	public static final String TABLE_AMMINISTRATORE= "Amministratore";
	
	public synchronized boolean isPresent(Utente u) throws SQLException{
		/*Spacchettamento di username  poi si controlla se è presente
		 * nel db*/
		boolean result = false;
		String usernameToCheck = u.getUsername();
		
		if (checkUser(usernameToCheck,TABLE_INOCCUPATO) || checkUser(usernameToCheck,TABLE_AZIENDA) ||
				checkUser(usernameToCheck,TABLE_MODERATORE) || checkUser(usernameToCheck,TABLE_AMMINISTRATORE))
			result = true;
		return result;
	}
	
	public synchronized Utente logIn (String username,String password){
		
		/*Costruzione dell'utente poi si chiama is present, se da true
		 * viene restituito l'oggetto utente*/
		
		return null;
	}
	
	public synchronized boolean isAlreadyBanned(int idUser){
		/*Controlla il campo banned*/
		return false;
	}
	
	public synchronized boolean deleteUser (Utente u){
		/* Controlla se nel databas è presente un utente poi esegue la remove*/
		return false;
	}
	
	
	public synchronized boolean modificaCurriculum(int idUser,String pathNewCV){
		/*Dato un id modifica il path del curriculum nel db*/
		return false;
	}
	
	public synchronized boolean registerUser (Utente u){
		/*Controlla se esiste già un utente con con l'username passato se non c'è
		 * lo inserisce nel db*/
		return false;
	}
	
	
	private boolean checkUser (String username,String table) throws SQLException{
		
		PreparedStatement checkUserPS = null;
		Connection connect = null;
		boolean flag = false;
		String query = "SELECT Username FROM " + table + "WHERE Username = ?";
		try{
			
		    connect = DriverManagerConnectionPool.getConnection();
			checkUserPS = connect.prepareStatement(query);
			checkUserPS.setString(1,username);
			ResultSet result = checkUserPS.executeQuery();
			
			while(result.next())
			{
				flag = true;
			}
		} 
		finally 
		{
			try{
				if(checkUserPS != null)
					checkUserPS.close();
			    }
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return flag;
	}
}
