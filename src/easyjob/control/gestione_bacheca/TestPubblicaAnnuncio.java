package easyjob.control.gestione_bacheca;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import easyjob.entity.Azienda;
import easyjob.test.DatabaseHelper;

public class TestPubblicaAnnuncio {
	private PubblicaAnnuncioServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message="Errore nel formato";
	final String successMessage="Pubblicato";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new PubblicaAnnuncioServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@Test // formato titolo errato
	public void TC_2_4_0() throws ServletException, IOException{
		Azienda azienda = new Azienda(); 
		azienda.setIdUser(1);
		request.getSession().setAttribute("utenteAzienda",azienda);
		request.addParameter("titolo","f");
		request.addParameter("descrizione","descrizione ok");
		request.addParameter("requisiti","req1,req2,req3");
		request.addParameter("tcontratto","stagista");
		request.addParameter("city","Salerno");
		request.addParameter("tags","Informatica");
		servlet.doGet(request,response);
		assertEquals(message,response.getContentAsString());
		
	}
	@Test //tag errato
	public void TC_2_4_1() throws ServletException, IOException {
		Azienda azienda = new Azienda(); 
		azienda.setIdUser(1);
		request.getSession().setAttribute("utenteAzienda",azienda);
		request.addParameter("titolo","press f");
		request.addParameter("descrizione","descrizione ok");
		request.addParameter("requisiti","req1,req2,req3");
		request.addParameter("tcontratto","stagista");
		request.addParameter("city","Salerno");
		request.addParameter("tags","I");
		servlet.doGet(request,response);
		assertEquals(message,response.getContentAsString());
	}
	
	
	@Test //descrizione errata
	public void TC_2_4_2() throws ServletException, IOException {
		Azienda azienda = new Azienda(); 
		azienda.setIdUser(1);
		request.getSession().setAttribute("utenteAzienda",azienda);
		request.addParameter("titolo","press f");
		request.addParameter("descrizione","d");
		request.addParameter("requisiti","req1,req2,req3");
		request.addParameter("tcontratto","stagista");
		request.addParameter("city","Salerno");
		request.addParameter("tags","Informatica");
		servlet.doGet(request,response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test //requisiti errati
	public void TC_2_4_3() throws ServletException, IOException {
		Azienda azienda = new Azienda(); 
		azienda.setIdUser(1);
		request.getSession().setAttribute("utenteAzienda",azienda);
		request.addParameter("titolo","press f");
		request.addParameter("descrizione","descrizione ok");
		request.addParameter("requisiti","r");
		request.addParameter("tcontratto","stagista");
		request.addParameter("city","Salerno");
		request.addParameter("tags","Informatica");
		servlet.doGet(request,response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test //formato città errato
	public void TC_2_4_4() throws ServletException, IOException {
		Azienda azienda = new Azienda(); 
		azienda.setIdUser(1);
		request.getSession().setAttribute("utenteAzienda",azienda);
		request.addParameter("titolo","press f");
		request.addParameter("descrizione","descrizione ok");
		request.addParameter("requisiti","req1,req2,req3");
		request.addParameter("tcontratto","stagista");
		request.addParameter("city","S");
		request.addParameter("tags","Informatica");
		servlet.doGet(request,response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test //tipo contratto non selezionato
	public void TC_2_4_5() throws ServletException, IOException {
		Azienda azienda = new Azienda(); 
		azienda.setIdUser(1);
		request.getSession().setAttribute("utenteAzienda",azienda);
		request.addParameter("titolo","press f");
		request.addParameter("descrizione","descrizione ok");
		request.addParameter("requisiti","req1,req2,req3");
		request.addParameter("tcontratto","");
		request.addParameter("city","Salerno");
		request.addParameter("tags","Informatica");
		servlet.doGet(request,response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test //successo
	public void TC_2_4_6() throws ServletException, IOException {
		Azienda azienda = new Azienda(); 
		azienda.setIdUser(1);
		request.getSession().setAttribute("utenteAzienda",azienda);
		request.addParameter("titolo","press fff");
		request.addParameter("descrizione","descrizione okay");
		request.addParameter("requisiti","req1,req2,req3");
		request.addParameter("tcontratto","stagista");
		request.addParameter("city","Salerno");
		request.addParameter("tags","Informatica");
		servlet.doGet(request,response);
		assertEquals(successMessage,response.getContentAsString());
	}
	
	
}
