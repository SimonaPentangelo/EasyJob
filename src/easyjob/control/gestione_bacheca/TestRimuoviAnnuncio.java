package easyjob.control.gestione_bacheca;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.assertEquals;
import easyjob.test.DatabaseHelper;

public class TestRimuoviAnnuncio {
	private RimuoviAnnuncioServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message="id non valido";
	final String successMessage="rimosso";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new RimuoviAnnuncioServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
	
	@Test // id annuncio illegale
	public void TC_2_6_0() throws ServletException, IOException {
		request.addParameter("idDaRimuovere","-1");
		servlet.doGet(request,response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test //id annuncio legale
	public void TC_2_6_1() throws ServletException, IOException {
		request.addParameter("idDaRimuovere","1");
		servlet.doGet(request, response);
		assertEquals(successMessage,response.getContentAsString());
	}
}
