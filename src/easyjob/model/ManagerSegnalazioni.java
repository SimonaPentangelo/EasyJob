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
	
	
}
