package easyjob.control.gestione_utenti;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import easyjob.entity.Azienda;
import easyjob.model.ManagerUtenti;

/**
 * Servlet implementation class RegistrazioneAziendaServlet
 */
@WebServlet("/RegistrazioneAziendaServlet")
@MultipartConfig
public class RegistrazioneAziendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrazioneAziendaServlet() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Validazione delle regexp per i campi della registrazione dell'azienda.
     * 
     * @param nomeAzienda oggetto di tipo <strong>String</strong> che rappresenta il nome dell'azienda.
     * @param logoAzienda oggetto di tipo <strong>Part</strong> corrispondente al logo dell'azienda.
     * @param partitaIVA  oggetto di tipo <strong>String</strong> che rappresenta la partita Iva.
     * @param username    oggetto di tipo <strong>String</strong> che rappresenta l'username.
     * @param indirizzo   oggetto di tipo <strong>String</strong> che rappresenta l'indirizzo dove si trova la sede.
     * @param dataFondazioneString oggetto di tipo <strong>String</strong> che rappresenta la data di fondazione.
     * @param numeroDipendentiString oggetto di tipo <strong>String</strong> che rappresenta il numero dei dipendenti.
     * @param emailoggetto di tipo <strong>String</strong> che rappresenta l'indirizzo email.
     * @param password oggetto di tipo <strong>String</strong> che rappresenta il campo della password.
     * @param confermaPassword  oggetto di tipo <strong>String</strong> il campo di conferma della password.
     * @param check oggetto di tipo <strong>Boolean</strong>
     * @return valido = true se tutte le validazioni sono corrette. False altrimenti.
     */
    private boolean validazione(String nomeAzienda, Part logoAzienda, String partitaIVA, String username, String indirizzo, String dataFondazioneString, String numeroDipendentiString, String email, String password, String confermaPassword, boolean check) {
		boolean valido = true;
		String nomeAzExp = "^[A-Za-zàèìòù0-9-._ ]{5,50}$";
		String userexp = "^[A-Za-z0-9]{5,20}$";
		String passexp = "^[A-Za-z0-9-._]{8,16}$";
		String indexp = "^[A-Za-z ]{3,6}[A-Za-zàèìòù ]{2,35}[,]{1}[0-9 ]{2,5}$";
		String IVAexp = "^[A-Z0-9]{11,11}$";
		String emailexp = "^[A-Za-z0-9_.]+@[a-zA-Z.]{2,}\\.[a-zA-Z]{2,3}$";
		
		if(!Pattern.matches(nomeAzExp, nomeAzienda)) {
			valido = false;
		}
		if(!Pattern.matches(IVAexp, partitaIVA)) {
			valido = false;
		}
		if(!Pattern.matches(userexp, username)){
			valido = false;
		}
		LocalDateTime dateInput = LocalDateTime.parse(dataFondazioneString);
		LocalDateTime currentDate = LocalDateTime.now();
		if(!dateInput.isBefore(currentDate)) {
			valido = false;
		}
		if(!Pattern.matches(passexp, password)) {
			valido = false;
		}
		if(!password.equals(confermaPassword)) {
			valido = false;
		}
		if(!Pattern.matches(indexp, indirizzo)) {
			valido = false;
		}
		if(!Pattern.matches(emailexp, email)) {
			valido = false;
		}
		if(!check) {
			valido = false;
		}
		if(!Paths.get(logoAzienda.getSubmittedFileName()).getFileName().toString().substring(Paths.get(logoAzienda.getSubmittedFileName()).getFileName().toString().length() - 3).equals("pdf")) {
			valido = false;
		}
		long fileSizeInMB = logoAzienda.getSize() / 1024;

		if (fileSizeInMB > 10) {
		  valido = false;
		}
		
		return valido;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * Questo metodo si occupa di prendere i dati di input inseriti dall’azienda e memorizzarli al fine di renderla registrata al sito.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @precondition 
	 * nomeAzienda != null && !nomeAzienda.equals("") && !nomeAzienda.equals(" ") && nome.Azienda.length()>=6 && nomeAzienda.length()<=30 &&
	 * formato nomeAzienda (^[A-Za-z ]{2,50}$).
	 * logoAzienda.getSize()>=0.
	 * partitaIva != null && !partitaIva.equals("") && !partitaIva.equals(" ") && partitaIva.length()==11.
	 * username != null && !username.equals("") && !username.equals(" ") && username.length() >= 5 && username.length() <= 20 &&
	 * formato Username ^[A-Za-z0-9]{5,20}$).
	 * indirizzo != null && !indirizzo.equals("") && !indirizzo.equals(" ") && indirizzo.length() >= 6 && indirizzo.length() <= 30 &&
	 * formato indirizzo ^[A-Za-z ]{3,6}[A-Za-z ]{2,35}[,]{1}[0-9 ]{2,5}$)
	 * descrizione != null && !descrizione.equals("") && !descrizione.equals(" ") && descrizione.length() <= 500)
	 * numeroDipendentiString != null && !numeroDipendentiString.equals("") && !numeroDipendentiString.equals(" ")
	 * email != null && !email.contentEquals("") && !email.contentEquals(" ") 
	 * password != null && !password.equals("") && !password.equals(" ") && password.length() >= 8 && password.length() <= 16
	 * confermaPassword != null && !confermaPassword.equals("") && !confermaPassword.equals(" ") && confermaPassword.length() >= 8 && 
	 * confermaPassword.length() <= 16
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Azienda azienda = new Azienda();
		ManagerUtenti mu = new ManagerUtenti();
		
		String nomeAzienda = request.getParameter("nomeAzienda");
		Part logoAzienda = request.getPart("logoAzienda");
		String partitaIVA = request.getParameter("partitaIVA");
		String username = request.getParameter("username");
		String indirizzo = request.getParameter("indirizzoSede");
		String dataFondazioneString = request.getParameter("dataFondazione");	
		String numeroDipendentiString = request.getParameter("numeroDipendenti");	
		String email = request.getParameter("email");		
		String password = request.getParameter("password");
		String confermaPassword = request.getParameter("confermaPassword");
		boolean check = Boolean.getBoolean(request.getParameter("trattamentoDati"));
		
		String rootFolder = "";
		String rootPath = "";
		String userPath = "";
		
		if(!validazione(nomeAzienda, logoAzienda, partitaIVA, username, indirizzo, dataFondazioneString, numeroDipendentiString, email, password, confermaPassword, check)) {
			
		} else {
			rootFolder = "resources"; //serve a dare il nome della cartella di root per salvare i file se gia c'è non la crea
			rootPath = request.getServletContext().getRealPath("") + rootFolder; //costruisce la stringa contenete il percorso della root dove salviamo i file 
			userPath = rootPath + File.separator + azienda.getUsername(); //serve per definire la cartella dell'utente se gia esiste non viene creata
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			String dataFondazione;
			try {
				dataFondazione = sdf2.format(sdf1.parse(dataFondazioneString));
				azienda.setDataFondazione(dataFondazione);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			azienda.setEmail(email);
			azienda.setPartitaIVA(partitaIVA);
			azienda.setPassword(password);
			azienda.setUsername(username);
			azienda.setIndirizzoSede(indirizzo);
			azienda.setNumeroDipendenti(Integer.parseInt(numeroDipendentiString));
			azienda.setNomeAzienda(nomeAzienda);
			azienda.setLogoAzienda("resources" + File.separator + azienda.getUsername()+ File.separator + logoAzienda.getSubmittedFileName().replaceAll(" ", "_"));
			azienda.setBanned(false);
		}
		
		try {
			if(!mu.isPresent(azienda)) {
				mu.registerUserAzienda(azienda);
				
				File dirRoot = new File(rootPath); //cartella delle resources
				if(!dirRoot.exists())//se la cartella esiste non la crea altrimenti genera la cartella 
				{
					dirRoot.mkdirs();
				}
				
				File userDir = new File(userPath); //cartella propria dell'utente
				if(!userDir.exists())//serve a creare la cartella dell'utente
				{
					userDir.mkdir();
					
				}
				
				String imageFullPath = userPath + File.separator + logoAzienda.getSubmittedFileName().replaceAll(" ", "_");
				InputStream inputStream = logoAzienda.getInputStream();
			
				Files.copy(inputStream, Paths.get(imageFullPath), StandardCopyOption.REPLACE_EXISTING);
				inputStream.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.sendRedirect(/*pagina di registrazione avvenuta con successo*/);
	}

}
