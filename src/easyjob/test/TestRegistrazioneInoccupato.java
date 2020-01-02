package easyjob.test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import easyjob.control.gestione_utenti.RegistrazioneInoccupatoServlet;

public class TestRegistrazioneInoccupato {
private RegistrazioneInoccupatoServlet servlet;
private MockHttpServletRequest request;
private MockHttpServletResponse response;
final String message="Formato dati errati";
final String successMessage="Successo";

@BeforeEach
public void setUp()throws Exception{
	servlet = new RegistrazioneInoccupatoServlet();
	request = new MockHttpServletRequest();
	response = new MockHttpServletResponse();
	//DatabaseHelper.initializeDatabase();
}

}
