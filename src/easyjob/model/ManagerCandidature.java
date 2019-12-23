package easyjob.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easyjob.entity.Annuncio;
import easyjob.entity.Candidatura;
import easyjob.entity.Inoccupato;

public class ManagerCandidature {

	public final String FIND_CANDIDATE = "SELECT * FROM Candidatura WHERE Inoccupato = ? AND Annuncio = ?;";
	public final String FIND_PER_INOCC = "SELECT * FROM Candidatura WHERE Inoccupato = ?;";
	public final String INSERT_CAND = "INSERT INTO Candidatura(Inoccupato, Annuncio) VALUES (?, ?);";
	
	private synchronized boolean isAlreadyCandidate (int idInoccupato,int idAnnuncio) throws SQLException {
		
		Connection connect = null;
		PreparedStatement searchCandidate = null;
		
		try {
			
			connect = DriverManagerConnectionPool.getConnection();
			searchCandidate = connect.prepareStatement(FIND_CANDIDATE);
			searchCandidate.setInt(1, idInoccupato);
			searchCandidate.setInt(2, idAnnuncio);
			
			ResultSet result = searchCandidate.executeQuery();
			
			while(result.next()) {
				return true;
			}
			
		} finally {
			
			try {
				if (searchCandidate != null) {
					searchCandidate.close();
				}
			}
			finally {
				
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return false;
	}
	
	
	public synchronized List<Candidatura> visualizzaCandidature (Inoccupato inocc){
		
		/*Dato un inoccupato, si restituiscono le candidature effettuate*/
		
		return null;
	}
	
	public synchronized boolean candidate (Inoccupato inocc,Annuncio ann) throws SQLException{
		
		if(!this.isAlreadyCandidate(inocc.getIdUser(), ann.getIdAnnuncio())) {
			return false;
		} else {
			
			Connection connect = null;
			PreparedStatement insertCandidate = null;
			
			try {
				
				connect = DriverManagerConnectionPool.getConnection();
				insertCandidate = connect.prepareStatement(FIND_CANDIDATE);
				insertCandidate.setInt(1, inocc.getIdUser());
				insertCandidate.setInt(2, ann.getIdAnnuncio());
				insertCandidate.executeUpdate();
				connect.commit();
				return true;
				
			} finally {
				
				try {
					if (insertCandidate != null) {
						insertCandidate.close();
					}
				}
				finally {
					
					DriverManagerConnectionPool.releaseConnection(connect);
				}
			}
		}
	}	
}
