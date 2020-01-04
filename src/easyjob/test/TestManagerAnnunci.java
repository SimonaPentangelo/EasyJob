package easyjob.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;
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
		assertNotNull(annuncio);
		
		annuncio = manager.searchById(50);
		assertNull(annuncio);
	}
	
	/*1) Testo il caso in cui il tag è presente e ritorna un annuncio
	 *2) Testo il caso in cui il tag è presente e ritorna più di un annuncio
	 *3) Testo il caso in cui il tag non è presente e quindi la lista ha 0 elementi*/
	
	
	@Test
	public void testSearchAd()throws SQLException{
		List<Annuncio> listaAnnunci = new ArrayList<>();
		listaAnnunci = manager.searchAd("Biologia");
		assertEquals(listaAnnunci.size(),1);
		
		listaAnnunci = manager.searchAd("Informatica");
		assertEquals(listaAnnunci.size(),2);
		
		listaAnnunci = manager.searchAd("Scienze");
		assertEquals(listaAnnunci.size(),0);
	}
	
	/*1) Testo il caso in cui il tag esiste e ed esiste la città */
	/*2) Testo il caso in cui il tag esiste ma non esiste la città */
	
	@Test
	public void testSearchAdvanced() throws SQLException{
		List<Annuncio> listaAnnunci = new ArrayList<>();
		listaAnnunci = manager.searchAdAdvanced("Informatica","Salerno");
		assertEquals(listaAnnunci.size(),1);
		
		listaAnnunci = manager.searchAdAdvanced("Informatica","Pontecagnano");
		assertEquals(listaAnnunci.size(),0);
	}

	/*1) Testo il caso in cui passo un'azienda e restituisce due annunci*/
	/*2) Testo il caso in cui passo un id non esistente e quindi la lista dovrebbe essere vuota
	 *   il caso di test numero 2 è equivalente a passare un id esistente di un'azienda che non ha pubblicato annunci*/

	@Test
	public void testVisualizzaElencoAnnunci () throws SQLException{
		List<Annuncio> listaAnnunci = new ArrayList<>();
		Azienda azienda = new Azienda ("testazienda","testazienda","testazienda@gmail.com",1,"googletest", 
			"path",100,"xxxx","00-00-00","testing","via dei test",false);
		listaAnnunci = manager.visualizzaElencoAnnunci(azienda);
		assertEquals(listaAnnunci.size(),2);
		
		Azienda azienda2= new Azienda ("testaz0ann","0annunci","test0el@gmail.com",5,"aziendafake", 
			"path",200,"XXXXX","00-00-00","test 0 annunci","via dei test",false);
		listaAnnunci = manager.visualizzaElencoAnnunci(azienda2);
		assertEquals(listaAnnunci.size(),0);
	}
	
	/*1) Pubblico un annuncio con id azienda esistente, quindi la variabile booleana restiuisce TRUE
	 *2) Pubblico un annuncio con id azienda uguale a quello del caso precedente, quindi la variabile booleana restituisce TRUE
	 */
	
	@Test
	public void testPubblicaAnnuncio() throws SQLException{
		boolean pubblicato = false;
		ArrayList<String> tag = new ArrayList<>();
		tag.add("informatica");
		tag.add("ingegneria informatica");
		Annuncio annuncio = new Annuncio(20,"test annuncio","testpubblicaAnn","test,test",tag,
				"tipocontratto","00-00-00",1,"cittàtest");
		pubblicato = manager.pubblicaAnnuncio(annuncio);
		assertEquals(pubblicato,true);
		
		Annuncio annuncio2 = new Annuncio(21,"test annuncio","testpubblicaAnn","test,test",tag,
				"tipocontratto","00-00-00",1,"cittàtest");
		pubblicato = manager.pubblicaAnnuncio(annuncio2);
		assertEquals(pubblicato,true);
		
	}
	/*1) Testo il caso in cui passo una data ed esiste un annuncio pubblicato in quella data,la lista contiene un elemento
	 *2) Testo il caso in cui passo una data per cui non esiste un annuncio pubblicato, la lista è vuota*/
	
	@Test 
	public void testFilterSearch() throws SQLException{
		List<Annuncio> listaAnnunci = new ArrayList<>();
		listaAnnunci = manager.filterSearch("23/12/2019");
		assertEquals(listaAnnunci.size(),1);
		
		listaAnnunci = manager.filterSearch("11/11/2012");
		assertEquals(listaAnnunci.size(),0);
	}
	
	/*1)Passo un id di un annuncio esistente, la variabile booleana deve essere TRUE
	 *2) Passo un id di un annuncio inesistente, la variabile booleana deve essere FALSE
	 *3) Passo l'id del test 1, per vedere se ripete la rimozione, la variabile booleana deve essere FALSE*/
	
	
	@Test
	public void testRemoveAd() throws SQLException{
		boolean rimosso = false;
		rimosso = manager.removeAd(1);
		assertEquals(rimosso,true);
		
		rimosso = manager.removeAd(5);
		assertEquals(rimosso,false);
		
		rimosso = manager.removeAd(1);
		assertEquals(rimosso,false);
	}
	
}
