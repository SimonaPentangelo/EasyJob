package easyjob.control.gestione_segnalazioni_notifiche_inviti;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.Assert.assertEquals;
import easyjob.test.DatabaseHelper;

public class TestContattaCandidato {
	private ContattaCandidatoServlet servlet;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	final String message="id non valido";
	final String successMessage="rimosso";

	@BeforeEach
	public void setUp()throws Exception{
		servlet = new ContattaCandidatoServlet();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		DatabaseHelper.initializeDatabase();
	}
}
