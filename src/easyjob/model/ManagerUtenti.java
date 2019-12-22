package easyjob.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import easyjob.entity.Amministratore;
import easyjob.entity.Azienda;
import easyjob.entity.Inoccupato;
import easyjob.entity.Moderatore;
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
	
	public synchronized Utente logIn (String username,String password) throws SQLException{
		
		Inoccupato inocc = retriveUserInoccupato(username,password,TABLE_INOCCUPATO);
		Azienda azienda = retriveUserAzienda (username,password,TABLE_AZIENDA);
		Moderatore mod = retriveUserModeratore(username,password,TABLE_MODERATORE);
		Amministratore admin = retriveUserAmministratore (username,password,TABLE_AMMINISTRATORE);
		
		if (inocc != null)
			return inocc;
		else if (azienda != null)
			return azienda;
		else if (mod!=null)
			return mod;
		else if(admin !=null)
			return admin;
		else return null;

			
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
		String query = "SELECT Username FROM " + table + " WHERE Username = ?;";
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
	
	
	private Inoccupato retriveUserInoccupato (String username,String password, String table) throws SQLException{
		
		
		Inoccupato inoc = null;
		PreparedStatement retriveUser = null;
		Connection connect = null;
		String query = "SELECT * FROM "+table+" WHERE Username = ? AND Password = ?;";
		try{
			
			connect = DriverManagerConnectionPool.getConnection();
			retriveUser = connect.prepareStatement(query);
			retriveUser.setString(1, username);
			retriveUser.setString(2, password);
			ResultSet result = retriveUser.executeQuery();
			while(result.next()){
				inoc.setIdUser(result.getInt("idUser"));
				inoc.setUsername(result.getString("Username"));
				inoc.setPassword(result.getString("Password"));
				inoc.setEmail(result.getString("Email"));
				inoc.setNome(result.getString("Nome"));
				inoc.setCognome(result.getString("Cognome"));
				inoc.setDataNascita(result.getString("DataNascita"));
				inoc.setResidenza(result.getString("Residenza"));
				inoc.setCittà(result.getString("Città"));
				inoc.setCurriculum(result.getString("Curriculum"));
			}
		}finally
		{
			try{
				 if (retriveUser!= null)
					 retriveUser.close();
			   }
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
			
		}
		return inoc;
	}
	
	private Azienda retriveUserAzienda (String username,String password, String table) throws SQLException{
		
		
		Azienda azienda = null;
		PreparedStatement retriveUser = null;
		Connection connect = null;
		String query = "SELECT * FROM "+table+" WHERE Username = ? AND Password = ?;";
		try{
			
			connect = DriverManagerConnectionPool.getConnection();
			retriveUser = connect.prepareStatement(query);
			retriveUser.setString(1, username);
			retriveUser.setString(2, password);
			ResultSet result = retriveUser.executeQuery();
			while(result.next()){
				azienda.setIdUser(result.getInt("idUser"));
				azienda.setUsername(result.getString("Username"));
				azienda.setPassword(result.getString("Password"));
				azienda.setEmail(result.getString("Email"));
				azienda.setNomeAzienda(result.getString("NomeAzienda"));
				azienda.setLogoAzienda(result.getString("LogoAzienda"));
				azienda.setNumeroDipendenti(result.getInt("Dipendenti"));
				azienda.setDataFondazione(result.getString("DataFondazione"));
				azienda.setIndirizzoSede(result.getString("Indirizzo"));
				azienda.setPartitaIVA(result.getString("PIva"));
				azienda.setBanned(result.getBoolean("Banned"));
			}
		}finally
		{
			try{
				 if (retriveUser!= null)
					 retriveUser.close();
			   }
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
			
		}
		return azienda;
	}
	

	private Moderatore retriveUserModeratore (String username,String password, String table) throws SQLException{
		
		
		Moderatore mod = null;
		PreparedStatement retriveUser = null;
		Connection connect = null;
		String query = "SELECT * FROM "+table+" WHERE Username = ? AND Password = ?;";
		try{
			
			connect = DriverManagerConnectionPool.getConnection();
			retriveUser = connect.prepareStatement(query);
			retriveUser.setString(1, username);
			retriveUser.setString(2, password);
			ResultSet result = retriveUser.executeQuery();
			while(result.next()){
				mod.setIdUser(result.getInt("idUser"));
				mod.setUsername(result.getString("Username"));
				mod.setPassword(result.getString("Password"));
				mod.setEmail(result.getString("Email"));
				
			}
		}finally
		{
			try{
				 if (retriveUser!= null)
					 retriveUser.close();
			   }
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
			
		}
		return mod;
	}

	private Amministratore retriveUserAmministratore (String username,String password, String table) throws SQLException{
		
		
		Amministratore admin = null;
		PreparedStatement retriveUser = null;
		Connection connect = null;
		String query = "SELECT * FROM "+table+" WHERE Username = ? AND Password = ?;";
		try{
			
			connect = DriverManagerConnectionPool.getConnection();
			retriveUser = connect.prepareStatement(query);
			retriveUser.setString(1, username);
			retriveUser.setString(2, password);
			ResultSet result = retriveUser.executeQuery();
			while(result.next()){
				admin.setIdUser(result.getInt("idUser"));
				admin.setUsername(result.getString("Username"));
				admin.setPassword(result.getString("Password"));
				admin.setEmail(result.getString("Email"));
				
			}
		}finally
		{
			try{
				 if (retriveUser!= null)
					 retriveUser.close();
			   }
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
			
		}
		return admin;
	}
	
}


