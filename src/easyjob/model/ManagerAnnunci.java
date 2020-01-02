package easyjob.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;

public class ManagerAnnunci {
	
	public static final String FIND_BY_TAG = "SELECT * FROM Annuncio JOIN Tag ON idAnnuncio = Annuncio WHERE NomeTag =?;";
	public static final String FIND_TAGS = "SELECT NomeTag FROM tag WHERE Annuncio = ?;";
	public static final String FIND_ALL_ADS = "SELECT * FROM Annuncio WHERE Azienda = ?;";
	public static final String INSERT_AD = "INSERT INTO Annuncio(Azienda,Titolo,Descrizione,Requisiti,TipoContratto,DataPubblicazione,Città)"+
	"VALUES (?,?,?,?,?,?,?);";
	public static final String SEARCH_BY_ID= "SELECT * FROM Annuncio WHERE idAnnuncio=?;";
	public static final String INSERT_TAG = "INSERT INTO TAG (NomeTag,Annuncio)"+
	"VALUES (?,?);";
	public static final String DELETE_TAG = "DELETE FROM Tag WHERE Annuncio = ?;";
	public static final String DELETE_AD = "DELETE FROM Annuncio WHERE idAnnuncio = ?;";
	public static final String ADVANCED_SEARCH = "SELECT * FROM Annuncio JOIN Tag ON idAnnuncio = Annuncio WHERE Tag.NomeTag =? AND Annuncio.Città=?;";
	public static final String SEARCH_BY_DATE ="SELECT * FROM Annuncio WHERE DataPubblicazione=?;";
	
	
	public synchronized Annuncio searchById(int idAnnuncio)throws SQLException{
		Connection connect = null;
		PreparedStatement searchById = null;
		Annuncio annuncio = new Annuncio();
		ManagerUtenti mu = new ManagerUtenti();
		try{
			connect = DriverManagerConnectionPool.getConnection();
			searchById = connect.prepareStatement(SEARCH_BY_ID);
			searchById.setInt(1, idAnnuncio);
			ResultSet result = searchById.executeQuery();
			while(result.next()){
				int id = result.getInt("idAnnuncio");
				annuncio.setIdAnnuncio(id);
				annuncio.setAzienda(result.getInt("Azienda"));
				annuncio.setTitolo(result.getString("Titolo"));
				annuncio.setDescrizione (result.getString("Descrizione"));
				annuncio.setRequisiti(result.getString("Requisiti"));
				annuncio.setTipoContratto(result.getString("TipoContratto"));
				annuncio.setCittà(result.getString("Città"));
				annuncio.setData(result.getString("DataPubblicazione"));
				annuncio.setTags(findTags(id));
				annuncio.setNomeAzienda(mu.getNomeAzienda(annuncio.getAzienda()));
			}
		}finally{
			try{
				if (searchById!=null){
					searchById.close();
				}
			}finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return annuncio;
	}
	
	
	public synchronized List<Annuncio> searchAd(String ricerca) throws SQLException{
		
		Connection connect = null;
		PreparedStatement searchByTag = null;
		List<Annuncio> listaAnnunci = new ArrayList<>();
		ManagerUtenti mu = new ManagerUtenti();
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			searchByTag = connect.prepareStatement(FIND_BY_TAG);
			searchByTag.setString(1, ricerca);
			
			ResultSet result = searchByTag.executeQuery();
			while(result.next()){
				Annuncio temp = new Annuncio();
				int id = result.getInt("idAnnuncio");
				temp.setIdAnnuncio(id);
				temp.setAzienda(result.getInt("Azienda"));
				temp.setTitolo(result.getString("Titolo"));
				temp.setDescrizione (result.getString("Descrizione"));
				temp.setRequisiti(result.getString("Requisiti"));
				temp.setTipoContratto(result.getString("TipoContratto"));
				temp.setCittà(result.getString("Città"));
				temp.setData(result.getString("DataPubblicazione"));
				temp.setTags(findTags(id));
				temp.setNomeAzienda(mu.getNomeAzienda(temp.getAzienda()));
				listaAnnunci.add(temp);
			}
		}finally
		{
			try{
				if (searchByTag != null)
					searchByTag.close();
			}
			finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return listaAnnunci;
	}
	
	public synchronized List<Annuncio> searchAdAdvanced (String tag,String città) throws SQLException{ 
	
		/*Ricerca per tag e per città insieme*/
	
		Connection connect = null;
		PreparedStatement searchAdv = null;
		List<Annuncio> listaAnnunci = new ArrayList<>();
		ManagerUtenti mu = new ManagerUtenti();
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			searchAdv = connect.prepareStatement(ADVANCED_SEARCH);
			searchAdv.setString(1, tag);
			searchAdv.setString(2, città);
			
			ResultSet result = searchAdv.executeQuery();
			while(result.next()){
				Annuncio temp = new Annuncio();
				int id = result.getInt("idAnnuncio");
				temp.setIdAnnuncio(id);
				temp.setAzienda(result.getInt("Azienda"));
				temp.setTitolo(result.getString("Titolo"));
				temp.setDescrizione (result.getString("Descrizione"));
				temp.setRequisiti(result.getString("Requisiti"));
				temp.setTipoContratto(result.getString("TipoContratto"));
				temp.setCittà(result.getString("Città"));
				temp.setData(result.getString("DataPubblicazione"));
				temp.setTags(findTags(id));
				temp.setNomeAzienda(mu.getNomeAzienda(temp.getAzienda()));
				listaAnnunci.add(temp);
			}
		}finally{
			try{
				if (searchAdv != null)
					searchAdv.close();
				
				
			}finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		
		return listaAnnunci;
	}
	
	public synchronized List<Annuncio> visualizzaElencoAnnunci(Azienda azienda) throws SQLException{
		
		Connection connect = null;
		PreparedStatement findAllAds = null;
		List<Annuncio> listaAnnunci = new ArrayList<>();
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			findAllAds = connect.prepareStatement(FIND_ALL_ADS);
			findAllAds.setInt(1, azienda.getIdUser());
			ResultSet result = findAllAds.executeQuery();
			
			while(result.next()){
				Annuncio temp = new Annuncio();
				int id = result.getInt("idAnnuncio");
				temp.setIdAnnuncio(id);
				temp.setAzienda(result.getInt("Azienda"));
				temp.setTitolo(result.getString("Titolo"));
				temp.setDescrizione (result.getString("Descrizione"));
				temp.setRequisiti(result.getString("Requisiti"));
				temp.setTipoContratto(result.getString("TipoContratto"));
				temp.setCittà(result.getString("Città"));
				temp.setData(result.getString("DataPubblicazione"));
				temp.setTags(findTags(id));
				temp.setNomeAzienda(azienda.getNomeAzienda());
				listaAnnunci.add(temp);
				
			}
		}
		finally{
			try{
				if (findAllAds != null)
					findAllAds.close();
			}
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		
		return listaAnnunci;
	}
	
	public synchronized boolean pubblicaAnnuncio (Annuncio ann) throws SQLException{
		/*Viene reso peristente un annuncio nel db*/
		
		Connection connect = null;
		PreparedStatement insertAd = null;
		boolean flag = false;
		boolean flagPerTag = false;
		try{
			connect = DriverManagerConnectionPool.getConnection();
			insertAd = connect.prepareStatement(INSERT_AD,Statement.RETURN_GENERATED_KEYS);
			insertAd.setInt(1, ann.getAzienda());
			insertAd.setString(2,ann.getTitolo());
			insertAd.setString(3,ann.getDescrizione());
			insertAd.setString(4, ann.getRequisiti());
			insertAd.setString(5, ann.getTipoContratto());
			insertAd.setString(6, ann.getData());
			insertAd.setString(7, ann.getCittà());
			
			int risultato = insertAd.executeUpdate();
			connect.commit();
			
				ResultSet generatedKeys = insertAd.getGeneratedKeys();
				if (generatedKeys.next()){
				
					int idUser = generatedKeys.getInt(1);
					ann.setIdAnnuncio(idUser);
					flag = true;
				
				
				for (int i= 0; i<ann.getTags().size();i++){
					int id = ann.getIdAnnuncio();
					String tag = ann.getTags().get(i);
					flagPerTag = inserimentoTag(id,tag);
				}
				return flag && flagPerTag;      /*Se è false una delle due operazioni è fallita*/
				}
		}
		finally{
			try{
				if(insertAd != null)
				{
					insertAd.close();
				}
			}finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		
		return flag && flagPerTag;
	}
	
	public synchronized List<Annuncio> filterSearch (String data) throws SQLException{
		
		/* Data una data vengono cercati gli annunci in base alla data selezionata*/
		Connection connect = null;
		PreparedStatement searchByDate = null;
		List<Annuncio> listaAnnunciFiltrati = new ArrayList<>();
		
		try{
			connect = DriverManagerConnectionPool.getConnection();
			searchByDate = connect.prepareStatement(SEARCH_BY_DATE);
			searchByDate.setString(1, data);
			ResultSet rs = searchByDate.executeQuery();
			while(rs.next()){
				Annuncio temp = new Annuncio();
				int id = rs.getInt("idAnnuncio");
				temp.setIdAnnuncio(rs.getInt(id));
				temp.setAzienda(rs.getInt("Azienda"));
				temp.setTitolo(rs.getString("Titolo"));
				temp.setDescrizione(rs.getString("Descrizione"));
				temp.setRequisiti(rs.getString("Requisiti"));
				temp.setTipoContratto(rs.getString("TipoContratto"));
				temp.setData(rs.getString("DataPubblicazione"));
				temp.setCittà(rs.getString("Città"));
				temp.setTags(findTags(id));
				listaAnnunciFiltrati.add(temp);
			}
		}finally{
			try{
				if(searchByDate != null){
					searchByDate.close();
				}
			}finally{
				DriverManagerConnectionPool.releaseConnection(connect);
			}
		}
		return listaAnnunciFiltrati;
	}
	
	public synchronized boolean removeAd (int idAnnuncio) throws SQLException{
		
		/*Rimuove un annuncio in base all'id passato come parametro*/
		
		 Connection connect = null;
		 PreparedStatement deleteAd = null;
		 boolean flag = false;
		 
		
			 try{
				 connect = DriverManagerConnectionPool.getConnection();
				 deleteAd = connect.prepareStatement(DELETE_AD);
				 deleteAd.setInt(1,idAnnuncio);
				 int risultato = deleteAd.executeUpdate();
				 connect.commit();
				 System.out.println("Risultato= "+ risultato);
				 if (risultato ==1)
				 {
					 flag = true;
					 System.out.println("Flag nel metodo deleteAd: " + flag);
					 return flag;
					 
				 }
				 
			 }finally{
				 try{
					 
				 if(deleteAd != null)
					 deleteAd.close();
				 }
				 finally{
					 DriverManagerConnectionPool.releaseConnection(connect);
				 }
			 }
		return flag;
	}
	
	
	
	private ArrayList<String> findTags(int idAnnuncio) throws SQLException{
		
		/*Metodo per prendere la lista di tag di un annuncio*/
		
		Connection connection = null;
		PreparedStatement findTags = null;
		ArrayList<String> tagAnnunci = new ArrayList<>();
		try{
			connection = DriverManagerConnectionPool.getConnection();
			findTags = connection.prepareStatement(FIND_TAGS);
			findTags.setInt(1,idAnnuncio);
			ResultSet rs = findTags.executeQuery();
			while (rs.next()){
				tagAnnunci.add(rs.getString("NomeTag"));
			}
		} finally
		{
			try{
				if(findTags!=null)
					findTags.close();
			}
			finally
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return tagAnnunci;
	}
	
	
	private boolean inserimentoTag(int idAnnuncio, String tag) throws SQLException{
		
		Connection connection = null;
		PreparedStatement insertTag = null;
		boolean flag = false;
		try{
			connection = DriverManagerConnectionPool.getConnection();
			insertTag = connection.prepareStatement(INSERT_TAG);
			insertTag.setString(1,tag);
			insertTag.setInt(2, idAnnuncio);
			int ris = insertTag.executeUpdate();
			connection.commit();
			if (ris == 1){
				flag = true;
				return flag;
			}
		}finally
		{
			try{
				if(insertTag != null)
					insertTag.close();
			}
			finally{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return flag;
	}
	
	private boolean deleteTag(int idAnnuncio) throws SQLException{
		
		Connection connection = null;
		PreparedStatement deleteTags = null;
		boolean flag = false;
		
		try{
			connection = DriverManagerConnectionPool.getConnection();
			deleteTags = connection.prepareStatement(DELETE_TAG);
			deleteTags.setInt(1,idAnnuncio);
			int ris = deleteTags.executeUpdate();
			connection.commit();
			if (ris ==1 ){
				flag = true;
				return flag;
			}
		}finally{
			try{
				if(deleteTags != null)
					deleteTags.close();
			}
			finally{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return flag;
	}
}
