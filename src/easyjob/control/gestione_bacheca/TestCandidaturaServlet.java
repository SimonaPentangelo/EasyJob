package easyjob.control.gestione_bacheca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import easyjob.test.DatabaseHelper;

public class TestCandidaturaServlet {
	private CandidaturaServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String idInvalido = "id non valido";
	final String candidato = "gi� candidato";
	final String successMessage="candidato";

	
	@BeforeEach
	public void setUp()throws Exception{
		servlet = new CandidaturaServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@Test // L'utente non � in sessione
	public void TC_2_2_0() throws ServletException, IOException {
		request.addParameter("idUt","-1");
		request.addParameter("idAz","1");
		servlet.doGet(request,response);
		assertEquals(idInvalido,response.getContentAsString());
	}
	
	// Manca tc 2-1-1 perch� non ha senso testarlo
	
	@Test // L'utente prova a candidarsi ad un annuncio a cui si � gi� candidato
	public void TC_2_2_2() throws ServletException, IOException {
		request.addParameter("idUt","1");
		request.addParameter("idAz","1");
		servlet.doGet(request, response);
		assertEquals(candidato,response.getContentAsString());
	}
	
	@Test // L'utente si candida ad un annuncio a cui non si � mai candidato e il suo id � valido
	public void TC_2_2_3() throws ServletException, IOException {
		request.addParameter("idUt","3");
		request.addParameter("idAz","1");
		servlet.doGet(request, response);
		assertEquals(successMessage,response.getContentAsString());
	}
	
}
