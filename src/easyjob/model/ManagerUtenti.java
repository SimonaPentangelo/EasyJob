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
	public static final String INSERT_INOCCUPATO = "INSERT INTO Inoccupato (Username,Password,Email,Nome,Cognome,DataNascita,Residenza,Città,Curriculum)"+
	"VALUES (?,?,?,?,?,?,?,?,?);";
	public static final String INSERT_AZIENDA = "INSERT INTO Azienda (Username,Password,Email,NomeAzienda,LogoAzienda,Dipendenti,DataFondazione,Indirizzo,PIva,Banned)"+
	"VALUES(?,?,?,?,?,?,?,?,?,?);";
	public static final String MODIFICA_CV = "UPDATE Inoccupato SET Curriculum=? WHERE idUser=?;";
	public static final String FIND_AZIENDA_BANNED = "SELECT Banned FROM Azienda WHERE idUser = ?;";
	public static final String BAN_AZIENDA = "UPDATE Azienda SET Banned=? WHERE idUser=?;";
	public static final String GET_NOME_AZIENDA ="SELECT NomeAzienda FROM Azienda WHERE idUser=?;";
	public static final String FIND_INOCCUPATO ="SELECT * FROM Inoccupato WHERE idUser=?;";
	public static final String FIND_AZIENDA ="SELECT * FROM Azienda WHERE idUser=?;";
			
			
	/**
	 * Questo metodo controlla se l'utente passato in input è presente nel db.
	 * 
	 * @param u oggetto di tipo <strong>Utente</strong>
	 * @return true se :
						checkUser(u.getUsername(),”Inoccupato”) OR
						checkUser (u.getUsername(),”Azienda”) OR
						checkUser (u.getUsername(),”Amministratore”)OR
						checkUser(u.getUsername(),”Moderatore”)  => TRUE.
						FALSE altrimenti.
	 * @throws SQLException
	 * @precondition (u != null) && (u.getUsername != “” && u.getUsername != null)
	 */
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
	
	/**
	 * Questo metodo effettua il login dell'utente con username e password passati in input, se l'utente è presente nel db.
	 * 
	 * @param username oggetto di tipo <strong>String</strong>.
	 * @param password oggetto di tipo <strong>String</strong>.
	 * @return inocc di tipo <strong>Inoccupato</strong> se retrieveUserInoccupato != null.
	 * @return azienda di tipo <strong>Azienda</strong> se retrieveUserAzienda != null.
	 * @return mod di tipo <strong>Moderatore</strong> se retrieveUserModeratore != null.
	 * @return admin di tipo <strong>Amministratore</strong> se retrieveUserAmministratore != null.    
	 * @throws SQLException
	 * @precondition (username != “” && username != null) && (password != “” && password != null).
	 */
	public synchronized Utente logIn (String username,String password) throws SQLException{
		
		Inoccupato inocc = retriveUserInoccupato(username,password,TABLE_INOCCUPATO);
		Azienda azienda = retriveUserAzienda (username,password,TABLE_AZIENDA);
		Moderatore mod = retriveUserModeratore(username,password,TABLE_MODERATORE);
		Amministratore admin = retriveUserAmministratore (username,password,TABLE_AMMINISTRATORE);
		
		if (inocc.getUsername()!= null)
			return inocc;
		else if (azienda.getUsername() != null)
			return azienda;
		else if (mod.getUsername()!=null)
			return mod;
		else if(admin.getUsername() !=null)
			return admin;
		else return null;

			
	}
	
	/**
	 * Questo metodo controlla se l'utende passato in input è stato già bannato.
	 * 
	 * @param idUser oggetto di tipo <strong>int</strong>.
	 * @return True se l'utente risulta già bannato. FALSE altrimenti.
	 * @throws SQLException
	 * @precondition idUser >=1.
	 */
	public synchronized boolean isAlreadyBanned(int idUser) throws SQLException{
		Connection connect = null;
		PreparedStatement banned = null;
		boolean flag = false;
		
		
		try{
			
			connect = DriverManagerConnectionPool.getConnection();
			banned = connect.prepareStatement(FIND_AZIENDA_BANNED);
			banned.setInt(1,idUser);
			ResultSet result = banned.executeQuery();
			while(result.next()){
				flag = result.getBoolean("Banned");
				
			}
		}finally
		{
			try{
				 if (banned!= null)
					 banned.close();
			   }
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
			
		}
		
		return flag;
	}
	
	/**
	 * Questo metodo restituisce il nome dell'azienda in base all'id passato in input.
	 * 
	 * @param idUser oggetto di tipo <strong>String</strong>
	 * @return nome, oggetto di tipo <strong>String</strong>
	 * @throws SQLException
	 * @precondition idUser >=1.
	 */
	public synchronized String getNomeAzienda(int idUser) throws SQLException{
		Connection connect = null;
		PreparedStatement azienda = null;
		String nome = null;
		
		try{
			
			connect = DriverManagerConnectionPool.getConnection();
			azienda = connect.prepareStatement(GET_NOME_AZIENDA);
			azienda.setInt(1,idUser);
			ResultSet result = azienda.executeQuery();
			while(result.next()){
				nome = result.getString("NomeAzienda");
				return nome;
			}
		}finally
		{
			try{
				 if (azienda!= null)
					 azienda.close();
			   }
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
			
		}
		
		return nome;
	}
	
	/**
	 * Questo metodo restituisce un'azienda in base all'id passato come input.
	 * 
	 * @param idUser oggetto di tipo <strong>int</strong>
	 * @return azienda, oggetto di tip <strong>Azienda</strong>
	 * @throws SQLException
	 * @precondition idUser >=1.
	 */
	public synchronized Azienda findAziendaById(int idUser) throws SQLException{
		Azienda azienda = new Azienda();
		Connection connect= null;
		PreparedStatement findAzienda = null;
		try{
			connect = DriverManagerConnectionPool.getConnection();
			findAzienda = connect.prepareStatement(FIND_AZIENDA);
			findAzienda.setInt(1,idUser);
			ResultSet rs = findAzienda.executeQuery();
			while(rs.next()){
				azienda.setIdUser(rs.getInt("idUser"));
				azienda.setUsername(rs.getString("Username"));
				azienda.setPassword(rs.getString("Password"));
				azienda.setEmail(rs.getString("Email"));
				azienda.setNomeAzienda(rs.getString("NomeAzienda"));
				azienda.setLogoAzienda(rs.getString("LogoAzienda"));
				azienda.setNumeroDipendenti(rs.getInt("Dipendenti"));
				azienda.setDataFondazione(rs.getString("DataFondazione"));
				azienda.setIndirizzoSede(rs.getString("Indirizzo"));
				azienda.setPartitaIVA(rs.getString("PIva"));
				azienda.setBanned(rs.getBoolean("Banned"));
			}
		}finally{
			try{
				if(findAzienda != null)
					findAzienda.close();
			}finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return azienda;
	}
	
	/**
	 * Questo metodo rimuove dal database, se presente,  l'utente passato in input.
	 * 
	 * @param u oggetto di tipo <strong>Utente</strong>
	 * @return true se la rimozione è avvenuta. False altrimenti.
	 * @throws SQLException
	 * @precondition (u.getId()>=1) && !(alreadyBanned(u.getId())) 
	 */
	public synchronized boolean deleteUser (Utente u) throws SQLException{
		
		int idUser = u.getIdUser();
		Connection connect = null;
		PreparedStatement ban = null;
		boolean flag = false;
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			ban = connect.prepareStatement(BAN_AZIENDA);
			ban.setBoolean(1, true);
			ban.setInt(2, idUser);
			
			int risultato= ban.executeUpdate();
			connect.commit();  /*Se non funziona provare con connect.setAutoCommit(false) quando si prende la connessione*/
			
			if(risultato==1){
				flag = true;
				return flag;
			}
		}
		finally{
			try{
				if (ban != null)
				{
				ban.close();
				}
			 }
			finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return false;
	}
	
	
	/**
	 * Questo metodo permette di modificare il curriculum corrispondente all'utente passato in input.
	 * 
	 * @param idUser oggetto di tipo <strong>int</strong>
	 * @param pathNewCV oggetto di tipo <strong>String</strong>
	 * @return true se la modifica è avvenuta. False altrimenti.
	 * @throws SQLException
	 * @precondition idUtente>=1  && (pathNewCv  != “” && pathNewCv != null)
	 */
	public synchronized boolean modificaCurriculum(int idUser,String pathNewCV) throws SQLException{

		Connection connect = null;
		PreparedStatement modificaCV = null;
		boolean flag = false;
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			modificaCV = connect.prepareStatement(MODIFICA_CV);
			modificaCV.setString(1, pathNewCV);
			modificaCV.setInt(2, idUser);
			
			int risultato = modificaCV.executeUpdate();
			connect.commit();  /*Se non funziona provare con connect.setAutoCommit(false) quando si prende la connessione*/
			
			if(risultato==1){
				flag = true;
				return flag;
			}
		}
		finally{
			try{
				if (modificaCV != null)
				{
				modificaCV.close();
				}
			 }
			finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return flag;
	}
		
	
	
	/**
	 * Questo metodo memorizza nel database, se non è già presente, l'inoccupato passato in input.
	 * 
	 * @param inocc oggetto di tipo <strong>Inoccupato</strong>
	 * @return true se la memorizzazione è avvenuta. False altrimenti.
	 * @throws SQLException
	 * @precondition !(isPresent(inocc))
	 */
	public synchronized boolean registerUserInoccupato (Inoccupato inocc) throws SQLException{
		
		Connection connect = null;
		PreparedStatement insertUser = null;
		Boolean flag = false;
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			insertUser = connect.prepareStatement(INSERT_INOCCUPATO);
			insertUser.setString(1,inocc.getUsername());
			insertUser.setString(2,inocc.getPassword());
			insertUser.setString(3,inocc.getEmail());
			insertUser.setString(4, inocc.getNome());
			insertUser.setString(5,inocc.getCognome());
			insertUser.setString(6, inocc.getDataNascita());
			insertUser.setString(7, inocc.getResidenza());
			insertUser.setString(8,inocc.getCittà());
			insertUser.setString(9,inocc.getCurriculum());
			
			int risultato =  insertUser.executeUpdate();
			connect.commit();
			if (risultato==1){
				flag = true;
				return flag;
			}
		} 
		finally{
			
			try{
				if(insertUser != null)
				{
					insertUser.close();
				}
			}
			finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		
		return flag;
	}
	
	/**
	 * Questo metodo memorizza nel database, se non è già presente, l'azienda passata in input.
	 * 
	 * @param azienda oggetto di tipo <strong>Azienda</strong>
	 * @return true se la memorizzazione è avvenuta. False altrimenti.
	 * @throws SQLException
	 * @precondition !(isPresent(azienda))
	 */
	public synchronized boolean registerUserAzienda (Azienda azienda) throws SQLException{
		
		Connection connect = null;
		PreparedStatement insertUser = null;
		Boolean flag = false;
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			insertUser = connect.prepareStatement(INSERT_AZIENDA);
			insertUser.setString(1,azienda.getUsername());
			insertUser.setString(2,azienda.getPassword());
			insertUser.setString(3,azienda.getEmail());
			insertUser.setString(4, azienda.getNomeAzienda());
			insertUser.setString(5,azienda.getLogoAzienda());
			insertUser.setInt(6, azienda.getNumeroDipendenti());
			insertUser.setString(7,azienda.getDataFondazione());
			insertUser.setString(8,azienda.getIndirizzoSede());
			insertUser.setString(9,azienda.getPartitaIVA());
			insertUser.setBoolean(10,azienda.isBanned());
			
			int risultato =  insertUser.executeUpdate();
			connect.commit();
			if (risultato==1){
				flag = true;
				return flag;
			}
		} 
		finally{
			
			try{
				if(insertUser != null)
				{
					insertUser.close();
				}
			}
			finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		
		return flag;
	}
	
	
	/**
	 * Questo metodo controlla se l'utente passato in input con l'username è presente nella tabella passata in input.
	 * 
	 * @param username oggetto di tipo <strong>String</strong>
	 * @param table oggetto di tipo <strong>String</strong>
	 * @return true se l'utente è nella tabella. False altrimenti.
	 * @throws SQLException
	 * @precondition username != null && !username.equals("") && table != null.
	 */
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
	
	
	/**
	 * Questo metodo restituisce un oggetto inoccupato corrispondente ai parametri dati in input.
	 * 
	 * @param username oggetto di tipo <strong>String</strong>
	 * @param password oggetto di tipo <strong>String</strong>
	 * @param table    oggetto di tipo <strong>String</strong>
	 * @return inoc, oggetto di tipo <strong>Inoccupato</strong>
	 * @throws SQLException
	 * @precondition username != null && !username.equals("") && password != null && !password.equals("") && table != null.
	 */
	private Inoccupato retriveUserInoccupato (String username,String password, String table) throws SQLException{
		
		
		Inoccupato inoc = new Inoccupato();
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
	
	/**
	 * Questo metodo restituisce un oggetto azienda corrispondente ai parametri dati in input.
	 * 
	 * @param username oggetto di tipo <strong>String</strong>
	 * @param password oggetto di tipo <strong>String</strong>
	 * @param table    oggetto di tipo <strong>String</strong>
	 * @return azienda, oggetto di tipo <strong>Azienda</strong>
	 * @throws SQLException
	 * @precondition username != null && !username.equals("") && password != null && !password.equals("") && table != null.
	 */
	private Azienda retriveUserAzienda (String username,String password, String table) throws SQLException{
		
		
		Azienda azienda = new Azienda();
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
	

	/**
	 * Questo metodo restituisce un oggetto moderatore corrispondente ai parametri dati in input.
	 * 
	 * @param username oggetto di tipo <strong>String</strong>
	 * @param password oggetto di tipo <strong>String</strong>
	 * @param table    oggetto di tipo <strong>String</strong>
	 * @return mod, oggetto di tipo <strong>Moderatore</strong>
	 * @throws SQLException
	 * @precondition username != null && !userna.equals("") && password != null && !passowrd.equals("") && table != null.
	 */
	private Moderatore retriveUserModeratore (String username,String password, String table) throws SQLException{
		
		Moderatore mod = new Moderatore();
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

	/**
	 * Questo metodo restituisce un oggetto amministratore corrispondente ai parametri dati in input.
	 * 
	 * @param username oggetto di tipo <strong>String</strong>
	 * @param password oggetto di tipo <strong>String</strong>
	 * @param table    oggetto di tipo <strong>String</strong>
	 * @return admin, oggetto di tipo <strong>Amministratore</strong>
	 * @throws SQLException
	 * @precondition username != null && !userna.equals("") && password != null && !passowrd.equals("") && table != null.
	 */
	private Amministratore retriveUserAmministratore (String username,String password, String table) throws SQLException{
		
		
		Amministratore admin = new Amministratore();
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
	
	/**
	 * Questo metodo restituisce un oggetto inoccupato in base all'id passato in input.
	 * 
	 * @param idUser oggetto di tipo <strong>int</strong>
	 * @return inocc oggetto di tipo <strong>Inoccupato</strong>
	 * @throws SQLException
	 * @precondition idUser >=1.
	 */
	public Inoccupato findInoccupato(int idUser) throws SQLException {
		PreparedStatement retriveUser = null;
		Connection connect = null;
		Inoccupato inocc = new Inoccupato();
		try{
			
			connect = DriverManagerConnectionPool.getConnection();
			retriveUser = connect.prepareStatement(FIND_INOCCUPATO);
			retriveUser.setInt(1, idUser);
			ResultSet result = retriveUser.executeQuery();
			while(result.next()){
				inocc.setIdUser(result.getInt("idUser"));
				inocc.setUsername(result.getString("Username"));
				inocc.setPassword(result.getString("Password"));
				inocc.setEmail(result.getString("Email"));
				inocc.setCittà(result.getString("Città"));
				inocc.setNome(result.getString("Nome"));
				inocc.setCognome(result.getString("Cognome"));
				inocc.setCurriculum(result.getString("Curriculum"));
				inocc.setResidenza(result.getString("Residenza"));
				inocc.setDataNascita(result.getString("DataNascita"));
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
		return inocc;
	}
}


