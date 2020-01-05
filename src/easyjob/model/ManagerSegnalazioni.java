package easyjob.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easyjob.entity.Segnalazione;

public class ManagerSegnalazioni {
	
	public static final String FIND_SEGNALAZIONI ="SELECT * FROM Segnalazione;";
	public static final String INSERT_SEGNALAZIONE = "INSERT INTO Segnalazione(Titolo,Corpo,Azienda,Moderatore)"+
	"VALUES(?,?,?,?);";

	/**
	 * Questo metodo mostra tutte le segnalazioni effettuate, se ci sono.
	 * 
	 * @return segnalazioni, oggetto di tipo <strong>ArrayList</strong>.
	 * @throws SQLException
	 */
	public synchronized List<Segnalazione> visualizzaElencoSegnalazioni () throws SQLException{
		
		/*Select nel db di tutte le segnalazioni*/
		Connection connect = null;
		PreparedStatement searchSegnalazioni = null;
		List<Segnalazione> segnalazioni = new ArrayList<>();
		try{
			connect = DriverManagerConnectionPool.getConnection();
			searchSegnalazioni = connect.prepareStatement(FIND_SEGNALAZIONI);
			ResultSet risultati  = searchSegnalazioni.executeQuery();
			
			while(risultati.next()){
				Segnalazione temp = new Segnalazione();
				temp.setTitolo(risultati.getString("Titolo"));
				temp.setCorpo(risultati.getString("Corpo"));
				temp.setAzienda(risultati.getInt("Azienda"));
				temp.setModeratore(risultati.getInt("Moderatore"));
				segnalazioni.add(temp);
			}
		}
		finally{
			try{
			if(searchSegnalazioni != null)
				searchSegnalazioni.close();
			}
			finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		
		return segnalazioni;
	}
	
	/**
	 * Questo metodo rende persistente nel db la segnalazione passata in input.
	 * 
	 * @param s oggetto di tipo <strong>Segnalazione</strong>
	 * @return true se la persistenza è avvenuta con successo. False altrimenti.
	 * @throws SQLException
	 * @precondition (segnalazione != null) && (s.getAzienda().getId >= 1) 
	 */
	public synchronized boolean segnalaUtente (Segnalazione s) throws SQLException{
		/* Nella segnalazione c'è l'azienda segnalata e il moderatore che l'ha effettuata
		 * quindi la si inserisce per renderla peristente*/
		
		Connection connect = null;
		PreparedStatement segnala = null;
		boolean flag = false;
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			segnala = connect.prepareStatement(INSERT_SEGNALAZIONE);
			segnala.setString (1,s.getTitolo());
			segnala.setString(2,s.getCorpo());
			segnala.setInt(3, s.getAzienda());
			segnala.setInt(4, s.getModeratore());
			
			int ris = segnala.executeUpdate();
			connect.commit();
			if (ris==1){
				flag = true;
				return flag;
			}
		} finally{
			try{
				if (segnala !=null)
					segnala.close();
			}
			finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return flag;
	}
	
	/**
	 * Questo metodo resituisce una segnalazione in base all'id dell'azienda passato come input.
	 * 
	 * @param id oggetto di tipo <strong>int</strong>
	 * @return segnalazione, oggetto di tipo <strong>Segnalazione</strong>
	 * @throws SQLException
	 * @precondition id =>1.
	 */
	public synchronized Segnalazione retrieveSegnByAz(int id) throws SQLException {
		
		Segnalazione segnalazione = new Segnalazione();
		PreparedStatement retrieveSegn = null;
		Connection connect = null;
		String query = "SELECT * FROM Segnalazione WHERE Azienda = ?;";
		try{
			
			connect = DriverManagerConnectionPool.getConnection();
			retrieveSegn = connect.prepareStatement(query);
			retrieveSegn.setInt(1, id);
			ResultSet result = retrieveSegn.executeQuery();
			while(result.next()){
				segnalazione.setTitolo(result.getString("Titolo"));
				segnalazione.setCorpo(result.getString("Corpo"));
				segnalazione.setModeratore(result.getInt("Moderatore"));
				segnalazione.setAzienda(result.getInt("Azienda"));
				
			}
		}finally
		{
			try{
				 if (retrieveSegn!= null)
					 retrieveSegn.close();
			   }
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
			
		}
			
		return segnalazione;
	}
	
	
}
	
