package easyjob.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easyjob.entity.Inoccupato;
import easyjob.entity.Invito;

public class ManagerInviti {
	
	public static final String SEARCH_INVITI = "SELECT * FROM Invito WHERE Inoccupato = ?;";
	public static final String INSERT_INVITO = "INSERT INTO Invito(Titolo,Corpo,Annuncio,Inoccupato)"+
	"VALUES (?,?,?,?);";
	public static final String ALREADY_INVITED ="SELECT Azienda,Inoccupato FROM Invito WHERE Annuncio=? AND Inoccupato=?;";
	
	public synchronized List<Invito> visualizzaInviti(Inoccupato inocc) throws SQLException{
		
		/*Dato un inoccupato viene fornita la lista degli inviti*/
		
		Connection connect = null;
		PreparedStatement searchInviti = null;
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
				temp.setAzienda(rs.getInt("Azienda"));
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
			connect = DriverManagerConnectionPool.getConnection();
			contact = connect.prepareStatement(INSERT_INVITO);
			contact.setString(1,i.getTitolo());
			contact.setString(2, i.getCorpo());
			contact.setInt(3, i.getAzienda());
			contact.setInt(4, i.getInoccupato());
			
			int risultato = contact.executeUpdate();
			connect.commit();
			if (risultato ==1){
				flag = true;
				return flag;
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
	
	private boolean alreadyInvited(int idA)
}
