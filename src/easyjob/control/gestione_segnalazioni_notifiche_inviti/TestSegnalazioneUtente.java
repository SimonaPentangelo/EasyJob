package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import easyjob.test.DatabaseHelper;

public class TestSegnalazioneUtente {
	private SegnalazioneUtenteServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String formatError="formato non valido";
	final String alreadyReported="è già stato segnalato";
	final String successMessage="segnalato";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new SegnalazioneUtenteServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@Test // titolo non valido
	public void TC_3_2_0() throws ServletException, IOException {
		request.addParameter("azienda","1");
		request.addParameter("moderatore","2");
		request.addParameter("titolo", "ann");
		request.addParameter("corpo", "L’annuncio non rispetta la netiquette del sito");
		servlet.doPost(request, response);
		assertEquals(formatError ,response.getContentAsString());
	}
	
	@Test // corpo non valido
	public void TC_3_2_1() throws ServletException, IOException {
		request.addParameter("azienda","1");
		request.addParameter("moderatore","2");
		request.addParameter("titolo", "Annuncio Illegale");
		request.addParameter("corpo", "L’ann");
		servlet.doPost(request, response);
		assertEquals(formatError ,response.getContentAsString());
	}
	
	@Test // azienda già segnalata dal moderatore corrente
	public void TC_3_2_2() throws ServletException, IOException {
		request.addParameter("azienda","1");
		request.addParameter("moderatore","1");
		request.addParameter("titolo", "Annuncio Illegale");
		request.addParameter("corpo", "Lannuncio non rispetta la netiquette del sito");
		servlet.doPost(request, response);
		assertEquals(alreadyReported ,response.getContentAsString());
	}
	
	@Test // successo
	public void TC_3_2_3() throws ServletException, IOException {
		request.addParameter("azienda","1");
		request.addParameter("moderatore","2");
		request.addParameter("titolo", "Annuncio Illegale");
		request.addParameter("corpo", "Lannuncio non rispetta la netiquette del sito");
		servlet.doPost(request, response);
		assertEquals(successMessage ,response.getContentAsString());
	}
}
