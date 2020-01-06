package easyjob.control.gestione_utenti;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import easyjob.test.DatabaseHelper;

public class TestRimuoviUtente {
	private RimozioneUtenteServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message="L'utente risulta già bannato";
	final String successMessage="Rimosso";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new RimozioneUtenteServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@Test // Utente già bannato
	public void TC_1_4_0() throws ServletException, IOException {
		request.addParameter("az","3");
		servlet.doPost(request,response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test
	public void TC_1_4_1() throws ServletException, IOException {
		request.addParameter("az","1");
		servlet.doPost(request, response);
		assertEquals(successMessage,response.getContentAsString());
	}

}
