package easyjob.control.gestione_utenti;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import easyjob.entity.Azienda;

/**
 * Servlet implementation class RegistrazioneAziendaServlet
 */
@WebServlet("/RegistrazioneAziendaServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,//10MB
maxFileSize=1024*1024*100000,//100GB
maxRequestSize=1024*1024*100000)//100GB per la dimensione dei file

public class RegistrazioneAziendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrazioneAziendaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Azienda azienda = new Azienda();
		
		String nomeAzienda = request.getParameter("nomeAzienda");
		if(nomeAzienda != null && !nomeAzienda.equals("") && !nomeAzienda.equals(" ")) {
			azienda.setNomeAzienda(nomeAzienda);
		}
		
		Part logoAzienda = request.getPart("logoAzienda");
		if(logoAzienda.getSize() == 0) {
			//non c'è il logo 
		}
		
		String partitaIVA = request.getParameter("partitaIVA");
		if(partitaIVA != null && !partitaIVA.equals("") && !partitaIVA.equals(" ")) {
			azienda.setPartitaIVA(partitaIVA);
		}
		
		String username = request.getParameter("username");
		if(username != null && !username.equals("") && !username.contentEquals(" ")) {
			azienda.setUsername(username);
		}
		
		String indirizzo = request.getParameter("IndirizzoSede");
		if(indirizzo != null && !indirizzo.equals("") && !indirizzo.contentEquals(" ")) {
			azienda.setIndirizzoSede(indirizzo);
		}
		
		String dataFondazioneString = request.getParameter("dataFondazione");
		SimpleDateFormat sdf = new SimpleDateFormat("gg/mm/yyyy");
		try {
			Date dataFondazione = sdf.parse(dataFondazioneString);
			azienda.setDataFondazione(dataFondazione);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String descrizione = request.getParameter("descrizione");
		if(descrizione != null && !descrizione.equals("") && !descrizione.equals(" ")) {
			azienda.setDescrizione(descrizione);
		}
		
		String numeroDipendentiString = request.getParameter("numeroDipendenti");
		if(numeroDipendentiString != null && !numeroDipendentiString.contentEquals("") && !numeroDipendentiString.contentEquals(" ")) {
			azienda.setNumeroDipendenti(Integer.parseInt(numeroDipendentiString));
		}
		
		String email = request.getParameter("email");
		if(email != null && !email.contentEquals("") && !email.contentEquals(" ")) {
			azienda.setEmail(email);
		}
		
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");
	
		if(password != null && !password.contentEquals("") && !password.contentEquals(" ")) {
			if(confermaPassword != null && !confermaPassword.contentEquals("") && !confermaPassword.contentEquals(" "))
			{
				if(password.equals(confermaPassword)) {
					azienda.setPassword(password);
				}
			}
		}
		
		azienda.setBanned(false);
		
		if(request.getParameter("trattamentoDati") == null) {
			//deve fare il check
		}
	}

}
