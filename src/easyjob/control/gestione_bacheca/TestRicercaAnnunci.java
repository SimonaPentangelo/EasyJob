package easyjob.control.gestione_bacheca;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.assertEquals;
import easyjob.test.DatabaseHelper;

public class TestRicercaAnnunci {
	private RicercaAnnunciServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message="formato errato";
	final String successMessage="ok";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new RicercaAnnunciServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@Test //Formato della ricerca non corretto
	public void TC_2_1_0() throws ServletException, IOException {
		request.addParameter("searchTag","   ");
		servlet.doGet(request,response);
		assertEquals(message,response.getContentAsString());
		
	}
	
	@Test //Formato corretto della ricerca
	public void TC_2_1_1() throws ServletException, IOException {
		request.addParameter("searchTag","Informatica");
		servlet.doGet(request, response);
		assertEquals(successMessage,response.getContentAsString());
	}
}
