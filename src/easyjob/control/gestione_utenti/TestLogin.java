package easyjob.control.gestione_utenti;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.assertEquals;
import easyjob.test.DatabaseHelper;

public class TestLogin {
	private LoginServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message="Username o password non validi";
	final String successMessage="Successo";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new LoginServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}

	@Test // Username non presente
	public void TC_1_2_0() throws ServletException, IOException {
		request.addParameter("username","Giuseppe30");
		request.addParameter("password","123test");
		servlet.doPost(request, response);
		assertEquals(message,response.getContentAsString());
		
	}
	@Test //Username presente, ma password non corrispondente
	public void TC_1_2_1() throws ServletException,IOException{
		request.addParameter("username","gabriele1997");
		request.addParameter("password","test");
		servlet.doPost(request, response);
		assertEquals(message,response.getContentAsString());
	}
	
	@Test // username e password presenti
	public void TC_1_2_2() throws ServletException,IOException{
		request.addParameter("username","gabriele1997");
		request.addParameter("password","ciaociao");
		servlet.doPost(request, response);
		assertEquals(successMessage,response.getContentAsString());
	}
}
