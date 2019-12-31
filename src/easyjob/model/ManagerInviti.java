package easyjob.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;
import easyjob.entity.Inoccupato;
import easyjob.entity.Invito;

public class ManagerInviti {
	
	public static final String SEARCH_INVITI = "SELECT * FROM Invito WHERE Inoccupato = ?;";
	public static final String INSERT_INVITO = "INSERT INTO Invito(Titolo,Corpo,Annuncio,Azienda,Inoccupato)"+
	"VALUES (?,?,?,?,?);";
	public static final String ALREADY_INVITED ="SELECT Annuncio,Inoccupato FROM Invito WHERE Annuncio=? AND Inoccupato=?;";
	public static final String SINGLE_INVITE ="SELECT * FROM Invito WHERE Annuncio=? AND Inoccupato=?;";
	
	public synchronized List<Invito> visualizzaInviti(Inoccupato inocc) throws SQLException{
		
		/*Dato un inoccupato viene fornita la lista degli inviti*/
		
		Connection connect = null;
		PreparedStatement searchInviti = null;
		ManagerUtenti mu = new ManagerUtenti();
		List<Invito> listaInviti = new ArrayList<>();
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			searchInviti = connect.prepareStatement(SEARCH_INVITI);
			searchInviti.setInt(1,inocc.getIdUser());
			ResultSet rs = searchInviti.executeQuery();
			
			while (rs.next()){
				Invito temp = new Invito();
				temp.setTitolo(rs.getString("Titolo"));
				temp.setCorpo(rs.getString("Corpo"));
				temp.setInoccupato(rs.getInt("Inoccupato"));
				temp.setAnnuncio(rs.getInt("Annuncio"));
				temp.setAzienda(rs.getInt("Azienda"));
				temp.setNomeAzienda(mu.getNomeAzienda(temp.getAzienda()));
				listaInviti.add(temp);
			}
			
		}finally{
			try{
				if (searchInviti != null)
					searchInviti.close();
				
			}finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		
		return listaInviti;
	}
	
	public synchronized boolean contattaCandidato (Invito i) throws SQLException{
		/* Dato un invito lo rende persistente */
		
		Connection connect = null;
		PreparedStatement contact = null;
		boolean flag = false;
		
		try{
			if (!alreadyInvited(i.getAnnuncio(),i.getInoccupato())){
			connect = DriverManagerConnectionPool.getConnection();
			contact = connect.prepareStatement(INSERT_INVITO);
			contact.setString(1,i.getTitolo());
			contact.setString(2, i.getCorpo());
			contact.setInt(3, i.getAnnuncio());
			contact.setInt(4, i.getAzienda());
			contact.setInt(5, i.getInoccupato());
			
			int risultato = contact.executeUpdate();
			connect.commit();
			if (risultato ==1){
				flag = true;
				return flag;
			}
		}else{
			return false;
		}
		} finally{
			try{
				if (contact != null)
					contact.close();
			}finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return false;
	}
	
	private boolean alreadyInvited(int idAnnuncio,int idInoccupato) throws SQLException {
		Connection conn = null;
		PreparedStatement check = null;
		try{
			conn = DriverManagerConnectionPool.getConnection();
			check = conn.prepareStatement(ALREADY_INVITED);
			check.setInt(1,idAnnuncio);
			check.setInt(2,idInoccupato);
			ResultSet rs = check.executeQuery();
			if(rs.next())
				return true;
		}finally{
			try{
				if (check!=null)
					check.close();
			}finally{
				DriverManagerConnectionPool.releaseConnection(conn);
			}
		}
		return false;
	}
	
	public Invito getInvito(int idAnnuncio, int idInoccupato) throws SQLException {
		Connection connect = null;
		PreparedStatement invito = null;
		ManagerUtenti mu = new ManagerUtenti();
		Invito temp = new Invito();
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			invito = connect.prepareStatement(SINGLE_INVITE);
			invito.setInt(1, idAnnuncio);
			invito.setInt(2, idInoccupato);
			ResultSet rs = invito.executeQuery();
			
			while (rs.next()){
				temp.setTitolo(rs.getString("Titolo"));
				temp.setCorpo(rs.getString("Corpo"));
				temp.setInoccupato(rs.getInt("Inoccupato"));
				temp.setAnnuncio(rs.getInt("Annuncio"));
				temp.setAzienda(rs.getInt("Azienda"));
				temp.setNomeAzienda(mu.getNomeAzienda(temp.getAzienda()));
			}
			
		}finally{
			try{
				if (invito != null)
					invito.close();
				
			}finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		
		return temp;
	}
}
