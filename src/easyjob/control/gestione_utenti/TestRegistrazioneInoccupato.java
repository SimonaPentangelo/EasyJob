package easyjob.control.gestione_utenti;

import org.springframework.mock.web.MockHttpServletRequest;
import static org.junit.Assert.assertEquals;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import easyjob.control.gestione_utenti.RegistrazioneInoccupatoServlet;
import easyjob.test.DatabaseHelper;

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
	DatabaseHelper.initializeDatabase();
}

@Test //formato nome errato
public void TC_1_1_0()throws ServletException, IOException{
	request.addParameter("nome","g1us3pp3");
	request.addParameter("cognome","giusti");
	request.addParameter("cittaNascita","Battipaglia");
	request.addParameter("username","giuseppe33");
	request.addParameter("residenza","via cinque maggio");
	request.addParameter("dataNascita","1998-12-02");
	request.addParameter("email","giuseppe33@gmail.com");
	request.addParameter("password","giuseppe123");
	request.addParameter("confermaPassword","giuseppe123");
	request.addParameter("curriculum","c:/path/cv.pdf");
	request.addParameter("trattamentoDati","on");
	
	servlet.doPost(request,response);
	assertEquals(message,response.getContentAsString());
	
}
}
