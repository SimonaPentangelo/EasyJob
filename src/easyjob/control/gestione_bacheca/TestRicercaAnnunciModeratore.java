package easyjob.control.gestione_bacheca;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import easyjob.test.DatabaseHelper;

public class TestRicercaAnnunciModeratore {

	private RicercaAnnunciModeratore servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String formatError="non è stata selezionata la data";
	final String successMessage="selezione effettuata";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new RicercaAnnunciModeratore ();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
		
	@Test // data non selezionata
	public void TC_2_5_0() throws ServletException, IOException {
		String nulla = null;
		request.addParameter("data", nulla);
		servlet.doPost(request, response);
		assertEquals(formatError ,response.getContentAsString());
	}
	
	@Test // data selezionata
	public void TC_2_5_1() throws ServletException, IOException {
		request.addParameter("data", "2019-10-24");
		servlet.doPost(request, response);
		assertEquals(successMessage ,response.getContentAsString());
	}
}
