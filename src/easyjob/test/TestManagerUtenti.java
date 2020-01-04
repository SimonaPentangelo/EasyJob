package easyjob.test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import easyjob.entity.Amministratore;
import easyjob.entity.Annuncio;
import easyjob.entity.Azienda;
import easyjob.entity.Inoccupato;
import easyjob.entity.Moderatore;
import easyjob.entity.Utente;
import easyjob.model.DriverManagerConnectionPool;
import easyjob.model.ManagerUtenti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestManagerUtenti {
private static ManagerUtenti manager = new ManagerUtenti();

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

/*1) Passo un utente esistente, quindi la variabile booleana deve restituire true
 *2) Passo un utente non esistente,quindi la variabile booleana deve restituire false*/

@Test
public void testIsPresent() throws SQLException{
	boolean presente = false;
	Utente u = new Utente ("gabriele1997","password","email@gmail.com",1);
	presente = manager.isPresent(u);
	assertEquals(presente,true);
	
	Utente u2= new Utente ("username2","password2","email@gmail.com",16);
	presente = manager.isPresent(u2);
	assertEquals(presente,false);
}

/*1) Testo il caso in cui do username e pwd di un inoccupato
 *2) Testo il caso in cui do username e pwd di un'azienda
 *3) Testo il caso in cui do username e pwd di un moderatore
 *4) Testo il caso in cui do username e pwd di un amministratore
 *5) Testo il caso in cui do username e pwd non corrispondenti
 Dei primi 4 casi di test verifico anche se l'istanza dell'oggetto restituito è giusta*/

@Test
public void testLogin() throws SQLException{
	boolean isCorrectInstance = false;
	Utente u = new Utente();
	u = manager.logIn("gabriele1997","ciaociao");
	assertNotNull (u.getUsername());
	if (u instanceof Inoccupato) {
		isCorrectInstance = true;
	}
	assertEquals(isCorrectInstance,true);
	
	u = manager.logIn("google2019","google19");
	assertNotNull(u.getUsername());
	if (u instanceof Azienda) {
		isCorrectInstance = true;
	}
	assertEquals(isCorrectInstance,true);
	
	u = manager.logIn("Admin1","admin00");
	assertNotNull(u.getUsername());
	if (u instanceof Amministratore) {
		isCorrectInstance = true;
	}
	assertEquals(isCorrectInstance,true);
	
	u = manager.logIn("Moderatore1","modmod");
	assertNotNull(u.getUsername());
	if (u instanceof Moderatore) {
		isCorrectInstance = true;
	}
	assertEquals(isCorrectInstance,true);
	
	u = manager.logIn("user","pwd");
	assertNull(u);
}

/*1)Passo l'id di un'azienda esistente, quindi mi restituisce il nome dell'azienda
 *2)Passo l'id di un'azienda inesistente,quindi mi restituisce null*/

@Test
public void testGetNomeAzienda()throws SQLException{
	String nomeAz =null;
	nomeAz = manager.getNomeAzienda(1);
	assertNotNull(nomeAz);
	
	nomeAz = manager.getNomeAzienda(5);
	assertNull(nomeAz);

}

/*1) Testo il caso in cui l'id è presente e mi restituisce un oggetto azienda
 *2) Testo il caso in cui l'id non è presente e quindi dovrebbe restituirmi null*/

@Test
public void testFindAziendaByID()throws SQLException{
	Azienda azienda = new Azienda();
	azienda = manager.findAziendaById(1);
	assertNotNull(azienda);
	
	azienda = manager.findAziendaById(50);
	assertNull(azienda);
	
}

/*1) Testo il cambio del campo banned con un utente esistente, restituisce true
 *2) Testo il cambio del campo banned pere un utente non esistente, restituisce false*/


@Test
public void testDeleteUser() throws SQLException{
	
	boolean rimosso = false;
	Utente u = new Utente("gabriele1997","password","email@gmail.com",1);
	rimosso = manager.deleteUser(u);
	assertEquals(rimosso,true);
	
	Azienda azienda = manager.findAziendaById(1);
	assertEquals(azienda.isBanned(),true);
	
	
	
	Utente u2 = new Utente("testing","password","email@gmail.com",4);
	rimosso = manager.deleteUser(u2);
	assertEquals(rimosso,false);
	
}

/*1) Provo a modificare il cv di un inoccupato esistente e verifico se dopo la chiamata del metodo è stato cambiato 
 *2)Provo a modificare il cv di un inoccupato inesistente e verifico che il metodo restituisca false*/

@Test
public void testModificaCv() throws SQLException{
	boolean cvCambiato = false;
	cvCambiato =manager.modificaCurriculum(1,"nuovoPath");
	assertEquals(cvCambiato,true);
	
	Inoccupato inocc = manager.findInoccupato(1);
	assertEquals(inocc.getCurriculum(),"nuovoPath");
	
	cvCambiato = manager.modificaCurriculum(5,"testPath");
	assertEquals(cvCambiato,false);
	
}

/*Provo a registrare un utente e dopo verifico che sia stato effettivamente inserito*/

@Test
public void testRegistraInoccupato() throws SQLException{
	boolean registrato = false;
	Inoccupato daTestare = new Inoccupato();
	Inoccupato inocc = new Inoccupato ("gigi","gigi123","gigi@gmail.com",3,"gigi","delmonaco",
			"via test","00-00-00","pathcv","città");
	registrato = manager.registerUserInoccupato(inocc);
	assertEquals(registrato,true);
	
	daTestare= manager.findInoccupato(3);
	assertNotNull(daTestare.getEmail());
	
	
}

@Test
public void testRegistraAzienda() throws SQLException{
	boolean registrato = false;
	Azienda verifica = new Azienda();
	Azienda azienda = new Azienda("aziendatest","test","test@gmail.com",3,"azienda", 
			"path logo",100,"xxx","00-00-00",
			"desc","indirizz",false);
	registrato = manager.registerUserAzienda(azienda);
	assertEquals(registrato,true);
	
	verifica= manager.findAziendaById(3);
	assertNotNull(verifica.getEmail());
}
}
