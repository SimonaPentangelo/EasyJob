package easyjob.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import easyjob.entity.Annuncio;
import easyjob.entity.Candidatura;
import easyjob.entity.Inoccupato;

public class ManagerCandidature {

	public final String FIND_CANDIDATE = "SELECT * FROM Candidatura WHERE Inoccupato = ? AND Annuncio = ?;";
	public final String FIND_PER_INOCC = "SELECT * FROM Candidatura WHERE Inoccupato = ?;";
	public final String FIND_PER_ANN = "SELECT * FROM Candidatura WHERE Annuncio = ?;";
	public final String DELETE_PER_ANN = "DELETE FROM Candidatura WHERE Annuncio = ?;";
	public final String INSERT_CAND = "INSERT INTO Candidatura(Inoccupato, Annuncio, Data) VALUES (?, ?, ?);";
	
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
	
	
	public synchronized List<Candidatura> visualizzaCandidatureEffettuate(Inoccupato inocc) throws SQLException {
		
		Connection connect = null;
		PreparedStatement candidate = null;
		List<Candidatura> lista = new ArrayList<Candidatura>();
		
		try {
			
			connect = DriverManagerConnectionPool.getConnection();
			candidate = connect.prepareStatement(FIND_PER_INOCC);
			candidate.setInt(1, inocc.getIdUser());
			
			ResultSet result = candidate.executeQuery();
			
			while(result.next()) {
				Candidatura c = new Candidatura();
				c.setAnnuncio(result.getInt("Annuncio"));
				c.setData(result.getString("Data"));
				c.setInoccupato(inocc.getIdUser());
				lista.add(c);
			}
			
			return lista;
			
		} finally {
			
			try {
				if (candidate != null) {
					candidate.close();
				}
			}
			finally {
				
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
	}
	
public synchronized List<Candidatura> visualizzaCandidatureRicevute(int idAnn) throws SQLException {
		
		Connection connect = null;
		PreparedStatement candidate = null;
		List<Candidatura> lista = new ArrayList<Candidatura>();
		
		try {
			
			connect = DriverManagerConnectionPool.getConnection();
			candidate = connect.prepareStatement(FIND_PER_ANN);
			candidate.setInt(1, idAnn);
			
			ResultSet result = candidate.executeQuery();
			
			while(result.next()) {
				Candidatura c = new Candidatura();
				c.setInoccupato(result.getInt("Inoccupato"));
				c.setData(result.getString("Data"));
				c.setAnnuncio(idAnn);
				lista.add(c);
			}
			
			return lista;
			
		} finally {
			
			try {
				if (candidate != null) {
					candidate.close();
				}
			}
			finally {
				
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
	}
	
	public synchronized boolean candidate (Inoccupato inocc,Annuncio ann) throws SQLException{
		
		if(!this.isAlreadyCandidate(inocc.getIdUser(), ann.getIdAnnuncio())) {
			return false;
		} else {
			
			Connection connect = null;
			PreparedStatement insertCandidate = null;
			//Per mettere la data corrente, non so se funziona però
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String localDate = dtf.format(LocalDate.now());
			
			try {
				
				connect = DriverManagerConnectionPool.getConnection();
				insertCandidate = connect.prepareStatement(FIND_CANDIDATE);
				insertCandidate.setInt(1, inocc.getIdUser());
				insertCandidate.setInt(2, ann.getIdAnnuncio());
				insertCandidate.setString(3, localDate); //non so se funziona!!
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
	
	public boolean deleteCandidate(int idAnnuncio) throws SQLException {
		
		Connection connect = null;
		PreparedStatement candidate = null;
		
		try {
			
			connect = DriverManagerConnectionPool.getConnection();
			candidate = connect.prepareStatement(DELETE_PER_ANN);
			candidate.setInt(1, idAnnuncio);
			
			candidate.executeUpdate();
			connect.commit();
			return true;
			
		} finally {
			
			try {
				if (candidate != null) {
					candidate.close();
				}
			}
			finally {
				
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
	}
}
