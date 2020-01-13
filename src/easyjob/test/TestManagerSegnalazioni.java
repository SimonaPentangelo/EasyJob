package easyjob.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import easyjob.entity.Segnalazione;
import easyjob.model.DriverManagerConnectionPool;
import easyjob.model.ManagerSegnalazioni;

public class TestManagerSegnalazioni {
	public static ManagerSegnalazioni manager= new ManagerSegnalazioni();
	
	@BeforeEach
	public void setUp() throws Exception{
		DatabaseHelper.initializeDatabase();
	}

	/*Dopo i test porto il db da quello di test a quello originale altrimenti
	 * non funziona pi˘ nulla */

	@AfterEach
	public void tearDown()throws Exception{
		DriverManagerConnectionPool.setTest(false);
	}
	
	@Test
	public void testVisualizzaSegnalazioni()throws SQLException{
		List<Segnalazione> listaSegnalazioni = new ArrayList<>();
		listaSegnalazioni = manager.visualizzaElencoSegnalazioni();
		assertEquals(listaSegnalazioni.isEmpty(),false);
	}
	
	/*
	@Test 
	public void testRetriveSegnalazione() throws SQLException{
		Segnalazione s = new Segnalazione();
		s = manager.retrieveSegnByAz(1);
		assertNotNull(s.getTitolo());
		
		s= manager.retrieveSegnByAz(5);
		assertNull(s.getTitolo());
	}
	
	@Test
	public void testSegnalaUtente()throws SQLException{
		boolean segnalato = false;
		Segnalazione nuovaSegnalazione = new Segnalazione();
		Segnalazione s = new Segnalazione("titolo","corpo","05/01/2020",1,2);
		segnalato = manager.segnalaUtente(s);
		assertEquals(segnalato,true);
		
		nuovaSegnalazione = manager.retrieveSegnByAz(s.getAzienda());
		assertNotNull(nuovaSegnalazione.getTitolo());
		
		
	}*/
	
	@Test 
	/*1) Caso in cui l'utente Ë stato gi‡ segnalato dallo stesso moderatore
	 *2) Caso in cui l'utente non Ë stato segnalato ancora dal moderatore*/
	public void testAlreadySegnalato() throws SQLException{
		boolean gi‡Segnalato= false;
		int idUtente = 1;
		int idMod = 1;
		gi‡Segnalato = manager.alreadyReported(idMod, idUtente);
		assertEquals(gi‡Segnalato,true);
		
		int idUt2 = 3;
		int idMod2= 1;
		gi‡Segnalato = manager.alreadyReported(idMod2, idUt2);
		assertEquals(gi‡Segnalato,false);
	}
}
