package easyjob.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import easyjob.entity.Candidatura;
import easyjob.entity.Inoccupato;
import easyjob.model.DriverManagerConnectionPool;
import easyjob.model.ManagerCandidature;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestManagerCandidature {
	
private static ManagerCandidature manager = new ManagerCandidature();

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

/*1) testo il caso in cui passo idInoccupato e idAnnuncio esistenti, quindi restituisce true
 *2) Testo il caso in cui il candidato non si è mai iscritto alla candidatura dell'annuncio quindi restituisce false*/
@Test
public void testIsAlreadyCandidate()throws SQLException{
	boolean isCandidate=false;
	isCandidate= manager.isAlreadyCandidate(1,1);
	assertEquals(isCandidate,true);
	
	isCandidate = manager.isAlreadyCandidate(1,4);
	assertEquals(isCandidate,false);
}
/*1) Testo il caso in cui passo idInoccupato con due candidature effettuate, quindi la lista contiene 2 elementi
 *2) Passo un inoccupato che non è candidato a nessun annuncio quindi la lista contiene 0 elementi*/
@Test
public void testVisualizzaCandEffettuate() throws SQLException{
	List<Candidatura> listaCandidature = new ArrayList<>();
	Inoccupato inocc = new Inoccupato ("gig","gigi123","gigi@gmail.com",1,"gigi","delmonaco",
			"via test","00-00-00","pathcv","città");
	listaCandidature = manager.visualizzaCandidatureEffettuate(inocc);
	assertEquals(listaCandidature.size(),2);
	
	Inoccupato inocc2 = new Inoccupato ("gigi","gigi123","gigi@gmail.com",3,"gigi","delmonaco",
			"via test","00-00-00","pathcv","città");
	listaCandidature = manager.visualizzaCandidatureEffettuate(inocc2);
	assertEquals(listaCandidature.size(),0);
}

@Test
/*1)Testo il caso in cui passo un id di un annuncio a cui si sono candidati 2 inoccupati, quindi la lista contiene 2 elem
 *2)Testo il caso in cui passo un id di un annuncio a cui non si è candidato nessuno quindi la lista contiene 0 elem*/
public void testVisualizzaCandRicevute() throws SQLException{
	List<Candidatura> listaCandidature = new ArrayList<>();
	listaCandidature = manager.visualizzaCandidatureRicevute(1);
	assertEquals(listaCandidature.size(),2);
	
	listaCandidature= manager.visualizzaCandidatureRicevute(3);
	assertEquals(listaCandidature.size(),0);

}
}
