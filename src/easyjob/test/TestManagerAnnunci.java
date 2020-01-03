package easyjob.test;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import easyjob.entity.Annuncio;
import easyjob.model.DriverManagerConnectionPool;
import easyjob.model.ManagerAnnunci;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class TestManagerAnnunci {
private static ManagerAnnunci manager = new ManagerAnnunci();
	
	/*Prima di ogni test pulisco e poi riempio il db*/
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
	}
	
	/*Dopo i test porto il db da quello di test a quello originale altrimenti
	 * non funziona più nulla */
	@AfterEach
	public void tearDown()throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
	
	/*1) Testo il caso in cui l'id di un annuncio è presente quindi mi torna l'annuncio non nullo*/
	/*2) Testo il caso in cui l'id non è presente quindi dovrebbe ritornarmi null*/
	@Test
	public void testSearchById() throws SQLException{
		
		Annuncio annuncio = new Annuncio();
		annuncio = manager.searchById(1);
		assertNotNull(annuncio.getIdAnnuncio());
		
		annuncio = manager.searchById(50);
		assertNull(annuncio);
	}
}
