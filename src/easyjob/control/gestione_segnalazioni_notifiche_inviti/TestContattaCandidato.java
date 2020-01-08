package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import easyjob.entity.Azienda;
import easyjob.test.DatabaseHelper;

public class TestContattaCandidato {
	private ContattaCandidatoServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message="formato non valido";
	final String successMessage="invitato";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new ContattaCandidatoServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@Test // titolo non valido
	public void TC_3_1_0() throws ServletException, IOException {
		request.addParameter("idAnnuncio","4");
		request.addParameter("idUtente","1");
		Azienda a = new Azienda();
		a.setIdUser(2);
		request.addParameter("titolo","Sal");
		request.getSession().setAttribute("utenteAzienda",a);
		request.addParameter("messaggio","cerchiamo persone per il nostro organico");
		servlet.doPost(request, response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test //messaggio non valido
	public void TC_3_1_1() throws ServletException, IOException {
		request.addParameter("idAnnuncio","4");
		request.addParameter("idUtente","1");
		Azienda a = new Azienda();
		a.setIdUser(2);
		request.addParameter("titolo","Salve vorremo proporre...");
		request.getSession().setAttribute("utenteAzienda",a);
		request.addParameter("messaggio","cer");
		servlet.doPost(request, response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test //successo
	public void TC_3_1_2() throws ServletException, IOException {
		request.addParameter("idAnnuncio","4");
		request.addParameter("idUtente","1");
		Azienda a = new Azienda();
		a.setIdUser(2);
		request.addParameter("titolo","Salve vorremo proporre...");
		request.getSession().setAttribute("utenteAzienda",a);
		request.addParameter("messaggio","cerasi sviluppatore java");
		servlet.doPost(request, response);
		assertEquals(successMessage,response.getContentAsString());
	}
}
