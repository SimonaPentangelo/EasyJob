package easyjob.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import easyjob.entity.Inoccupato;
import easyjob.entity.Invito;
import easyjob.model.DriverManagerConnectionPool;
import easyjob.model.ManagerInviti;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class TestManagerInviti {

	public static ManagerInviti manager = new ManagerInviti();
	
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
	/*1)Passo un id di un inoccupato che possiede due inviti, la lista quindi deve contenere due elementi
	 *2)Passo un id di un inoccupato che possiede 0 inviti, la lista quindi deve contenere 0 elementi*/
	
	@Test
	public void testElencoInviti()throws SQLException{
		List<Invito> listaInviti = new ArrayList<>();
		Inoccupato inocc = new Inoccupato();
		inocc.setIdUser(1);
		listaInviti = manager.visualizzaInviti(inocc);
		assertEquals(listaInviti.size(),2);
		
		Inoccupato inoccSenzaInviti = new Inoccupato();
		inocc.setIdUser(3);
		listaInviti = manager.visualizzaInviti(inoccSenzaInviti);
		assertEquals(listaInviti.size(),0);
		}
	
	@Test
	/*1) Testo il caso in cui il candidato non è stato ancora invitato per un annuncio
	 *2) Testo il caso in cui il candidato è già stato contattato quindi il metodo restituisce false*/
	public void testContattaCandidato() throws SQLException{
		boolean contattato = false;
		Invito invito = new Invito("titolo","corpo",4,1,"bho",1);
		contattato= manager.contattaCandidato(invito);
		assertEquals(contattato,true);
		
		Invito invito2 = new Invito("titolo","corpo",1,1,"bho",1);
		contattato = manager.contattaCandidato(invito2);
		assertEquals(contattato,false);
		
	}
}
