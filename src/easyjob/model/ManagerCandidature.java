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
	public final String INSERT_CAND = "INSERT INTO Candidatura(Inoccupato, Annuncio, DataCandidatura) VALUES (?, ?, ?);";
	
	
	
	
	/**
	 * Questo metodo controlla se l'inoccupato passato in input è già candidato all'annuncio passato in input.
	 * 
	 * @param idInoccupato oggetto di tipo <strong>int</strong>
	 * @param idAnnuncio  oggetto di tipo <strong>int</strong>
	 * @return true se l'inoccupato è già candidato. False altrimenti.
	 * @throws SQLException
	 * @precondition idInoccupato >=1 && idAnnuncio >=1.
	 */
	public synchronized boolean isAlreadyCandidate (int idInoccupato,int idAnnuncio) throws SQLException {
		
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
	
	
	/**
	 * Il metodo restituisce, se presente una lista di candidature effettuate dall'inoccupato passato in input.
	 * 
	 * @param inocc oggetto di tipo <strong>Inoccupato</strong>
	 * @return lista, oggetto di tipo <strong>ArrayList</strong>. 
	 * @throws SQLException
	 * @precondition inocc != null  && inoc.getId()>=1
	 */
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
				c.setData(result.getString("DataCandidatura"));
				c.setInoccupato(inocc.getIdUser());
				lista.add(c);
			}
			
			
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
		return lista;
	}
	
/**
 * Il metodo mostra le candidature ricevute per l'annuncio passato in input.
 * 
 * @param idAnn oggetto di tipo <strong>int</strong>
 * @return lista, oggetto di tipo <strong>ArrayList</strong>
 * @throws SQLException
 * @precondition idAnn >=1.
 */
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
				c.setData(result.getString("DataCandidatura"));
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
	
	/**
	 * Il metodo inserisce nel db la candidatura dell'inoccupato passato in input, per l'annuncio passato in input.
	 * 
	 * @param idInocc oggetto di tipo <strong>int</strong>
	 * @param idAnn   oggetto di tipo <strong>int</strong>
	 * @return true se l'inserimento è avvenuto correttamente. False altrimenti.
	 * @throws SQLException
	 * @precondition (inocc != null && inocc.getId()) >=1) && (ann != null && ann.getId()>=1) 
	 * 				  && !(isAlreadyCandidate (inocc.getId(), ann.getId())
	 */
	public synchronized boolean candidate (int idInocc,int idAnn) throws SQLException{
			
			Connection connect = null;
			PreparedStatement insertCandidate = null;
			//Per mettere la data corrente, non so se funziona però
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String localDate = dtf.format(LocalDate.now());
			
			try {
				
				connect = DriverManagerConnectionPool.getConnection();
				insertCandidate = connect.prepareStatement(INSERT_CAND);
				insertCandidate.setInt(1,idInocc);
				insertCandidate.setInt(2,idAnn);
				insertCandidate.setString(3, localDate); //non so se funziona!!
				System.out.println("Sto cercando di aggiunger l'id inocc: " + idInocc+ " e idAnnuncio: " + idAnn);
				int ris = insertCandidate.executeUpdate();
				if(ris==1)
				return true;
				else
					return false;
				
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
	
	/**
	 * Il metodo si occupa di eliminare la candidatura dall'annuncio passato in input.
	 * 
	 * @param idAnnuncio oggetto di tipo <strong>int<strong>
	 * @return true se la rimozione è avvenuta. False altrimenti.
	 * @throws SQLException
	 * @precondition idAnnuncio >=1.
	 */
	public boolean deleteCandidate(int idAnnuncio) throws SQLException {
		
		boolean flag = false;
		Connection connect = null;
		PreparedStatement candidate = null;
		
		try {
			
			connect = DriverManagerConnectionPool.getConnection();
			candidate = connect.prepareStatement(DELETE_PER_ANN);
			candidate.setInt(1, idAnnuncio);
			
			candidate.executeUpdate();
			flag =true;
			
			
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
	return flag;	
	}
}
